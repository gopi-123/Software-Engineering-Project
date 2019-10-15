package controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;
import java.time.LocalDateTime;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import models.User;
import models.HirePeople;
import models.UserProfile;
import play.Routes;
import play.data.Form;
import play.mvc.*;
import play.mvc.Http.Session;
import play.mvc.Result;
import providers.MyUsernamePasswordAuthProvider;
import providers.MyUsernamePasswordAuthProvider.MyLogin;
import providers.MyUsernamePasswordAuthProvider.MySignup;
import views.html.account.user_profile;

import providers.MyUsernamePasswordAuthUser;
import views.html.*;
import be.objectify.deadbolt.java.actions.Group;
import be.objectify.deadbolt.java.actions.Restrict;

import com.feth.play.module.pa.PlayAuthenticate;
import com.feth.play.module.pa.providers.password.UsernamePasswordAuthProvider;
import com.feth.play.module.pa.user.AuthUser;

import controllers.routes;

import static play.data.Form.form;
import static providers.MyUsernamePasswordAuthProvider.RECRUITER_ADD_FORM;

public class Application extends Controller {

    public static final String FLASH_MESSAGE_KEY = "message";
    public static final String FLASH_ERROR_KEY = "error";
    public static final String USER_ROLE = "user";

    private static final Form<HirePeople> HIREPEOPLE_FORM = form(HirePeople.class);
    private static final Form<User> ADDRECRUITER_FORM = form(User.class);

    public static Result index() {
        try {
            final User user = Application.getLocalUser(session());
            if (user == null) {
                return ok(login.render(MyUsernamePasswordAuthProvider.LOGIN_FORM));
            }
            User users = User.findByEmail(user.email);
            if (users.role.equals("Jobseeker")) {
                return redirect(routes.Account.profile());
            }
            if (users.role.equals("Admin")) {
                return redirect(routes.Application.admin_ViewJobseekerUsers());
            }
            if (users.role.equals("Recruiter")) {
                return redirect(routes.Account.viewProfiles());
            }
            return ok(login.render(MyUsernamePasswordAuthProvider.LOGIN_FORM));
        } catch (Exception e) {
            return ok(message.render(e.getMessage()));
        }
    }

    public static User getLocalUser(final Session session) {
        final AuthUser currentAuthUser = PlayAuthenticate.getUser(session);
        final User localUser = User.findByAuthUserIdentity(currentAuthUser);
        return localUser;
    }

    @Restrict(@Group(Application.USER_ROLE))
    public static Result restricted() {
        final User localUser = getLocalUser(session());
        return ok(restricted.render(localUser));
    }

    @Restrict(@Group(Application.USER_ROLE))
    public static Result profile() {
	try{
            final User localUser = getLocalUser(session());
	    UserProfile userProfile = UserProfile.getUserProfilebyName(localUser.name);

	    if (userProfile.getEnabled()) {
                return ok(user_profile.render(userProfile, form(UserProfile.class)));
            } else {
                return ok(message.render("Benutzerkonto ist deaktiviert. Bitte fordern Sie Admin an, es zu aktivieren"));
            }
	} catch (Exception e) {
            return ok(message.render(e.getMessage()));
        }
    }

    public static Result admin_ViewJobseekerUsers() {
        try {
            com.feth.play.module.pa.controllers.Authenticate.noCache(response());

            final User user = Application.getLocalUser(session());
            User users = User.findByEmail(user.email);
            if (!user.role.equals("Admin"))
                return ok(message.render("Sie sind nicht berechtigt"));
            List<UserProfile> userProfileList = UserProfile.getAllJobseekerUsers();

            return ok(admin_ViewJobseekerUsers.render(userProfileList));
        } catch (Exception e) {
            return ok(message.render(e.getMessage()));
        }
    }

    public static Result admin_ViewRecruiterUsers() {
        try {
            com.feth.play.module.pa.controllers.Authenticate.noCache(response());

            final User user = Application.getLocalUser(session());
            User users = User.findByEmail(user.email);
            if (!user.role.equals("Admin"))
                return ok(message.render("Sie sind nicht berechtigt"));
            List<User> userList = users.getRecruitersList();

            return ok(admin_ViewRecruiterUsers.render(userList));
        } catch (Exception e) {
            return ok(message.render(e.getMessage()));
        }
    }


    public static Result login() {
        return ok(login.render(MyUsernamePasswordAuthProvider.LOGIN_FORM));
    }

    public static Result doLogin() {
        com.feth.play.module.pa.controllers.Authenticate.noCache(response());
        final Form<MyLogin> filledForm = MyUsernamePasswordAuthProvider.LOGIN_FORM
                .bindFromRequest();
        if (filledForm.hasErrors()) {
            // User did not fill everything properly
            return badRequest(login.render(filledForm));
        } else {
            // Everything was filled
            return UsernamePasswordAuthProvider.handleLogin(ctx());
        }
    }


    public static Result signup() {
        return ok(signup.render(MyUsernamePasswordAuthProvider.SIGNUP_FORM));
    }

    public static Result jsRoutes() {
        return ok(
                Routes.javascriptRouter("jsRoutes",
                        routes.javascript.Signup.forgotPassword()))
                .as("text/javascript");
    }

    public static Result doSignup() {
        com.feth.play.module.pa.controllers.Authenticate.noCache(response());
        final Form<MySignup> filledForm = MyUsernamePasswordAuthProvider.SIGNUP_FORM
                .bindFromRequest();
        if (filledForm.hasErrors()) {
            // User did not fill everything properly
            return badRequest(signup.render(filledForm));
        } else {
            return UsernamePasswordAuthProvider.handleSignup(ctx());
        }
    }


    public static Result hirePeople() {
        com.feth.play.module.pa.controllers.Authenticate.noCache(response());
        Form<HirePeople> hirePeopleForm = form(HirePeople.class);
        return ok(hirePeople.render(hirePeopleForm));
    }


    public static Result doHirePeople() {
        com.feth.play.module.pa.controllers.Authenticate.noCache(response());
        final Form<HirePeople> hirePeopleForm = HIREPEOPLE_FORM
                .bindFromRequest();

        if (hirePeopleForm.hasErrors()) {
            // User did not fill everything properly
            return badRequest(hirePeople.render(hirePeopleForm));
        } else {
            // Everything was filled
            // Send a mail to admin requesting for access
            hirePeopleForm.get().sendRequestToAdmin();
            return ok(message.render("Anforderung wurde an admin für den Zugang zur Kategorie gesendet" + hirePeopleForm.get().category + "zum " + hirePeopleForm.get().fullName));
        }
    }

    public static Result AddAccount() {
        com.feth.play.module.pa.controllers.Authenticate.noCache(response());
        Form<User> addRecruiterform = form(User.class);
        return ok(adminAddAccount.render(addRecruiterform));
    }

    public static Result doAddAccount() {
        com.feth.play.module.pa.controllers.Authenticate.noCache(response());
        final Form<User> addRecruiterform = ADDRECRUITER_FORM
                .bindFromRequest();
        if (addRecruiterform.hasErrors()) {
            // User did not fill everything properly
            return badRequest(adminAddAccount.render(addRecruiterform));
        } else {
            // Everything was filled
            // do something with your part of the form before handling the user
            // signup
            final User user = new User();
            user.category_acccess = addRecruiterform.get().category_acccess;
            user.department = addRecruiterform.get().department;
            user.email = addRecruiterform.get().email;
            user.role = "Recruiter";
            user.emailValidated = true;
            user.active = true;
            user.empID = addRecruiterform.get().empID;
            user.name = addRecruiterform.get().name;
            user.lastLogin = new Date();
            Date currentDate = new Date();
            LocalDateTime localDateTime = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            localDateTime = localDateTime.plusDays(7);
            Date access = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
            user.access_until = access;
            final MySignup signupObj = new MySignup();
            signupObj.name = addRecruiterform.get().name;
            signupObj.email = addRecruiterform.get().email;
            MyUsernamePasswordAuthProvider.AddRecruiterToUsers(user, signupObj, ctx());
            return ok(message.render("Konto wurde erfolgreich erstellt"));
        }
    }


    public static String formatTimestamp(final long t) {
        return new SimpleDateFormat("yyyy-dd-MM HH:mm:ss").format(new Date(t));
    }

}
