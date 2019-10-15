package controllers;

import be.objectify.deadbolt.java.actions.Group;
import be.objectify.deadbolt.java.actions.Restrict;
import be.objectify.deadbolt.java.actions.SubjectPresent;
import com.feth.play.module.pa.PlayAuthenticate;
import com.feth.play.module.pa.user.AuthUser;
import models.User;
import models.UserProfile;
import play.data.Form;
import play.data.format.Formats.NonEmpty;
//import play.data.FormFactory;
import play.data.validation.Constraints.MinLength;
import play.data.validation.Constraints.Required;
import play.i18n.Messages;
import play.mvc.Controller;
import play.mvc.Result;
import providers.MyUsernamePasswordAuthProvider;
import providers.MyUsernamePasswordAuthUser;
import views.html.account.link;
import views.html.account.unverified;
import views.html.account.password_change;
import views.html.account.ask_link;
import views.html.account.ask_merge;
import views.html.account.user_profile;
import views.html.account.recruiter;
import views.html.account.interested_view;
import views.html.account.recruiters_list;
import views.html.message;

import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.LocalDateTime;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;


import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


//===================
import helper.datasources.MorphiaObject;
import models.TokenAction.Type;
import org.bson.types.ObjectId;
import org.mongodb.morphia.*;
import com.mongodb.DB;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.query.Query;
import play.data.format.Formats;
import play.data.validation.Constraints;
import be.objectify.deadbolt.core.models.Subject;
import org.mongodb.morphia.query.UpdateResults;
import org.mongodb.morphia.query.*;

import java.io.File;

import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
//import org.apache.http.client.methods.multipart.FilePart;
//import org.apache.commons.httpclient.methods.multipart.FilePart;
//==================


import controllers.routes;

import static play.data.Form.form;

/**
 * @author Subhashini Jantwal
 */
public class Account extends Controller {

    public static class Accept {

        @Required
        @NonEmpty
        public Boolean accept;

        public Boolean getAccept() {
            return accept;
        }

        public void setAccept(Boolean accept) {
            this.accept = accept;
        }

    }

    static HashMap <String, List<UserProfile>> mapper = new HashMap<String, List<UserProfile>>();
    public static class PasswordChange {
        @MinLength(5)
        @Required
        public String password;

        @MinLength(5)
        @Required
        public String repeatPassword;

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getRepeatPassword() {
            return repeatPassword;
        }

        public void setRepeatPassword(String repeatPassword) {
            this.repeatPassword = repeatPassword;
        }

        public String validate() {
            if (password == null || !password.equals(repeatPassword)) {
                return Messages
                        .get("playauthenticate.change_password.error.passwords_not_same");
            }
            return null;
        }
    }

    public static class Filter {

        public String experience;
        public String experience1;
        public String experience2;
        public String experience3;
        public String experience4;
        public String langGA1;
        public String langGA2;
        public String langGB1;
        public String langGB2;
        public String langGC1;
        public String langGC2;
        public String frenchA1;
        public String frenchA2;
        public String frenchB1;
        public String frenchB2;
        public String frenchC1;
        public String frenchC2;
        public String spanishA1;
        public String spanishA2;
        public String spanishB1;
        public String spanishB2;
        public String spanishC1;
        public String spanishC2;
        public String italienA1;
        public String italienA2;
        public String italienB1;
        public String italienB2;
        public String italienC1;
        public String italienC2;
        public String english;
        public String word;
        public String excel;
        public String powerPoint;
        public String access;
        public String sAPHR;
        public String sAPFI;
        public String sAPMM;
        public String computerSkills;
        public String academicAssistant;
        public String technician;
        public String secretary;
        public String librarian;
        public String craftsman;
        public String animalKeeeper;
        public String studentAssistant;
        public String mittlerer;
        public String hoHerer;
        public String gehobener;
    }


    private static final Form<Accept> ACCEPT_FORM = form(Accept.class);
    private static final Form<PasswordChange> PASSWORD_CHANGE_FORM = form(PasswordChange.class);
    private static final Form<UserProfile> USER_PROFILE = form(UserProfile.class);

    @SubjectPresent
    public static Result link() {
        com.feth.play.module.pa.controllers.Authenticate.noCache(response());
        return ok(link.render());
    }

    @Restrict(@Group(Application.USER_ROLE))
    public static Result verifyEmail() {
        com.feth.play.module.pa.controllers.Authenticate.noCache(response());
        final User user = Application.getLocalUser(session());
        if (user.emailValidated) {
            // E-Mail has been validated already
            flash(Application.FLASH_MESSAGE_KEY,
                    Messages.get("playauthenticate.verify_email.error.already_validated"));
        } else if (user.email != null && !user.email.trim().isEmpty()) {
            flash(Application.FLASH_MESSAGE_KEY, Messages.get(
                    "playauthenticate.verify_email.message.instructions_sent",
                    user.email));
            MyUsernamePasswordAuthProvider.getProvider()
                    .sendVerifyEmailMailingAfterSignup(user, ctx());
        } else {
            flash(Application.FLASH_MESSAGE_KEY, Messages.get(
                    "playauthenticate.verify_email.error.set_email_first",
                    user.email));
        }
        return redirect(routes.Application.profile());
    }

    @Restrict(@Group(Application.USER_ROLE))
    public static Result changePassword() {
        com.feth.play.module.pa.controllers.Authenticate.noCache(response());
        final User u = Application.getLocalUser(session());

        if (!u.emailValidated) {
            return ok(unverified.render());
        } else {
            return ok(password_change.render(PASSWORD_CHANGE_FORM));
        }
    }

    @Restrict(@Group(Application.USER_ROLE))
    public static Result doChangePassword() {
        com.feth.play.module.pa.controllers.Authenticate.noCache(response());
        final Form<PasswordChange> filledForm = PASSWORD_CHANGE_FORM
                .bindFromRequest();
        if (filledForm.hasErrors()) {
            // User did not select whether to link or not link
            return badRequest(password_change.render(filledForm));
        } else {
            final User user = Application.getLocalUser(session());
            final String newPassword = filledForm.get().password;
            user.changePassword(new MyUsernamePasswordAuthUser(newPassword),
                    true);
            flash(Application.FLASH_MESSAGE_KEY,
                    Messages.get("playauthenticate.change_password.success"));
            return redirect(routes.Application.profile());
        }
    }

    @Restrict(@Group(Application.USER_ROLE))
    public static Result disableJSAccount() {
        try {
            com.feth.play.module.pa.controllers.Authenticate.noCache(response());

            final User user = Application.getLocalUser(session());
            UserProfile userProfile = UserProfile.getUserProfilebyName(user.name);

            if (userProfile.disableJSAccount()) {
                return redirect(routes.Account.profile());
            } else {
                return redirect(routes.Account.profile());//ok(message.render("User Account has already been disabled"));
            }
        } catch (Exception e) {
            return ok(message.render(e.getMessage()));
        }
    }

    public static Result deleteJSAccount() {
        try {
            com.feth.play.module.pa.controllers.Authenticate.noCache(response());

            final User user = Application.getLocalUser(session());
            UserProfile userProfile = UserProfile.getUserProfilebyName(user.name);

            if (userProfile.deleteJSAccount()) {
                final Query<User> deleteUser = MorphiaObject.datastore.createQuery(User.class)
                        .filter("name ", user.name);
                MorphiaObject.datastore.delete(deleteUser);

                com.feth.play.module.pa.controllers.Authenticate.logout();

                return ok(message.render("Ihr Konto wurde erfolgreich gelöscht"));
            } else {
                return ok(message.render("Delete Account Betrieb nicht erfolgreich"));
            }
        } catch (Exception e) {
            return ok(message.render(e.getMessage()));
        }
    }

    public static Result profile() {
        try {
            com.feth.play.module.pa.controllers.Authenticate.noCache(response());

            final User user = Application.getLocalUser(session());
            UserProfile userProfile = UserProfile.getUserProfilebyName(user.name);

            Form<UserProfile> userForm = form(UserProfile.class);


            if (userProfile.getEnabled()) {
                return ok(user_profile.render(userProfile, userForm));
            } else {
                return ok(user_profile.render(userProfile, userForm));//return ok(message.render("User Account is disabled. Please request Admin to enable it"));
            }
        } catch (Exception e) {
            return ok(message.render(e.getMessage()));
        }
    }


    @Restrict(@Group(Application.USER_ROLE))
    public static Result doUserProfile() {
        try {
            com.feth.play.module.pa.controllers.Authenticate.noCache(response());
            final Form<UserProfile> filledForm = USER_PROFILE
                    .bindFromRequest();

            if (filledForm.hasErrors()) {
                return ok(message.render("Error"));
            } else {
                final User user = Application.getLocalUser(session());

                try {
                    MultipartFormData body = request().body().asMultipartFormData();
                    FilePart resumePart = body.getFile("resume");
                    File resume = resumePart.getFile();

                    UserProfile.updateUserProfile(user.name, filledForm.get(), resume, user.email);
                } catch (Exception e) {
                    UserProfile.updateUserProfile(user.name, filledForm.get(), null, user.email);
                }

                return redirect(routes.Account.profile());
            }
        } catch (Exception e) {
            return ok(message.render(e.getMessage()));
        }

    }


    public static Result viewProfiles() {
        try {
            com.feth.play.module.pa.controllers.Authenticate.noCache(response());

            final User user = Application.getLocalUser(session());
            System.out.println("User role is" + user.role);
            if (!user.role.equals("Recruiter") && !user.role.equals("Admin"))
                return ok(message.render("Sie sind nicht berechtigt"));

            if (user.role.equals("Recruiter"))
            {
                Date currentDate = new Date();
                LocalDateTime localDateTime = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                Date access = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
                if(access.after(user.access_until)){
                    return ok(message.render("Sie haben keinen Zugang mehr. Bitte füllen Sie die Mieter wieder aus, um den Zugang zu beantragen"));
                }
            }

            Form<Filter> filter = form(Filter.class);

            Boolean enabledOnly = false;
            if (user.role.equals("Recruiter"))
                enabledOnly = true;

            List<UserProfile> userProfileList = UserProfile.getAllUserProfiles(enabledOnly, user.category_acccess);

            try {
                mapper.remove(user.name);
            } catch (Exception e) {
                System.out.println("Map remove exception");
            }

            return ok(recruiter.render(userProfileList, filter, user.role));
        } catch (Exception e) {
            return ok(message.render(e.getMessage()));
        }
    }


    public static Result doViewProfiles() {
        try {
            com.feth.play.module.pa.controllers.Authenticate.noCache(response());

            final User user = Application.getLocalUser(session());
            if (!user.role.equals("Recruiter") && !user.role.equals("Admin"))
                return ok(message.render("Sie sind nicht berechtigt"));

            final Form<Filter> FILTER_FORM = form(Filter.class);

            final Form<Filter> filledForm = FILTER_FORM
                    .bindFromRequest();

            if (filledForm.hasErrors()) {
                return ok(message.render("Error"));
            } else {
                Filter filter = filledForm.get();
                System.out.println(filter + "Filter in Filter");

                List<String> category = new ArrayList<String>();
                List<String> experience = new ArrayList<String>();
                List<String> language = new ArrayList<String>();
                List<String> computerSkills = new ArrayList<String>();

                if (user.role.equals("Admin")) {

                    if (filter.academicAssistant != null)
                        category.add("Academic Assistant");
                    if (filter.technician != null)
                        category.add("Technician");
                    if (filter.secretary != null)
                        category.add("Secretary");
                    if (filter.librarian != null)
                        category.add("Librarian");
                    if (filter.craftsman != null)
                        category.add("Craftsman");
                    if (filter.animalKeeeper != null)
                        category.add("Animal Keeeper");
                    if (filter.studentAssistant != null)
                        category.add("Student Assistant");
                    if (filter.mittlerer != null)
                        category.add("Mittlerer Dienst");
                    if (filter.hoHerer != null)
                        category.add("HoHerer Dienst");
                    if (filter.gehobener != null)
                        category.add("Gehobener Dienst");
                } else {
                    category.add(user.category_acccess);
                }

                if (filter.experience1 != null) {
                    System.out.println("Exp");
                    experience.add("1");
                }
                if (filter.experience2 != null) {
                    System.out.println("Exp2");
                    experience.add("2");
                }
                if (filter.experience3 != null) {
                    System.out.println("Exp3");
                    experience.add("3");
                }
                if (filter.experience4 != null) {
                    System.out.println("Exp4");
                    experience.add("more");
                }

                if (filter.langGA1 != null) {
                    System.out.println("GA1");
                    language.add("German-A1");
                }
                if (filter.langGA2 != null)
                    language.add("German-A2");
                if (filter.langGB1 != null)
                    language.add("German-B1");
                if (filter.langGB2 != null)
                    language.add("German-B2");
                if (filter.langGC1 != null)
                    language.add("German-C1");
                if (filter.langGC2 != null)
                    language.add("German-C2");

                if (filter.frenchA1 != null)
                    language.add("French-A1");
                if (filter.frenchA2 != null)
                    language.add("French-A2");
                if (filter.frenchB1 != null)
                    language.add("French-B1");
                if (filter.frenchB2 != null)
                    language.add("French-B2");
                if (filter.frenchC1 != null)
                    language.add("French-C1");
                if (filter.frenchC2 != null)
                    language.add("French-C2");

                if (filter.spanishA1 != null)
                    language.add("Spanish-A1");
                if (filter.spanishA2 != null)
                    language.add("Spanish-A2");
                if (filter.spanishB1 != null)
                    language.add("Spanish-B1");
                if (filter.spanishB2 != null)
                    language.add("Spanish-B2");
                if (filter.spanishC1 != null)
                    language.add("Spanish-C1");
                if (filter.spanishC2 != null)
                    language.add("Spanish-C2");

                if (filter.italienA1 != null)
                    language.add("Italien-A1");
                if (filter.italienA2 != null)
                    language.add("Italien-A2");
                if (filter.italienB1 != null)
                    language.add("Italien-B1");
                if (filter.italienB2 != null)
                    language.add("Italien-B2");
                if (filter.italienC1 != null)
                    language.add("Italien-C1");
                if (filter.italienC2 != null)
                    language.add("Italien-C2");

                if (filter.english != null)
                    language.add("English");

                if (filter.word != null)
                    computerSkills.add("MS-Office Word");
                if (filter.excel != null)
                    computerSkills.add("MS-Office Excel");
                if (filter.powerPoint != null)
                    computerSkills.add("MS-Office Powerpoint");
                if (filter.access != null)
                    computerSkills.add("MS-Office Access");
                if (filter.sAPHR != null)
                    computerSkills.add("SAP-HR");
                if (filter.sAPFI != null)
                    computerSkills.add("SAP-FI");
                if (filter.sAPMM != null)
                    computerSkills.add("SAP-MM");


                for(int i=0;i<language.size();i++){
                    System.out.println(language.get(i));
                }

                for(int i=0;i<category.size();i++){
                    System.out.println(category.get(i));
                }

                for(int i=0;i<computerSkills.size();i++){
                    System.out.println(computerSkills.get(i));
                }

                for(int i=0;i<experience.size();i++){
                    System.out.println(experience.get(i));
                }


                Boolean enabledOnly = false;
                if (user.role.equals("Recruiter"))
                    enabledOnly = true;
                Boolean isAdmin = false;
                if (user.role.equals("Admin"))
                    isAdmin = true;
                List<UserProfile> userProfileList = UserProfile.getUserProfilesForFilter(enabledOnly, category, experience, language, computerSkills, true);
                Form<Filter> emptyFilter = form(Filter.class);
                mapper.put(user.name, userProfileList);

                return ok(recruiter.render(userProfileList, filledForm, user.role));
            }
        } catch (Exception e) {
            return ok(message.render(e.getMessage()));
        }
    }

//	public static Result viewRecruitersList() {
//		try {
//                        com.feth.play.module.pa.controllers.Authenticate.noCache(response());
//
//                        final User user = Application.getLocalUser(session());
//                        if (!user.role.equals("Admin"))
//                                return ok(message.render("You are not authorized"));
//
//			List<User> recruiterList = user.getRecruitersList();
//
//                        return ok(recruiters_list.render(recruiterList, form(String.class)));
//                } catch (Exception e) {
//                        return ok(message.render(e.getMessage()));
//                }
//	}

    public static Result modifyAccess() {
        try {
            com.feth.play.module.pa.controllers.Authenticate.noCache(response());

            final User user = Application.getLocalUser(session());
            if (!user.role.equals("Admin"))
                return ok(message.render("Sie sind nicht berechtigt"));

//                        final Form<String> filledForm = form(String.class)
//                                        .bindFromRequest();
//
//			System.out.println("In modifyAccess" + filledForm.get());
//
//			if (filledForm.hasErrors()) {
//                                return ok(message.render("Error"));
//                        } else {
//				user.modifyAccess(filledForm.get());
//				return redirect(routes.Account.viewRecruitersList());
            return ok(message.render("Hello"));
        } catch (Exception e) {
            return ok(message.render(e.getMessage()));
        }
    }

    public static Result doSortByExperience() {
        try {
            com.feth.play.module.pa.controllers.Authenticate.noCache(response());
            System.out.println("In Sort By Exp");

            final User user = Application.getLocalUser(session());
            if (!user.role.equals("Recruiter") && !user.role.equals("Admin"))
                return ok(message.render("Sie sind nicht berechtigt"));

            final Form<Filter> FILTER_FORM = form(Filter.class);

            final Form<Filter> filledForm = FILTER_FORM
                    .bindFromRequest();

            if (filledForm.hasErrors()) {
                return ok(message.render("Error"));
            } else {
                Filter filter = filledForm.get();
                System.out.println(filter + "Filter");


                List<String> category = new ArrayList<String>();
                List<String> experience = new ArrayList<String>();
                List<String> language = new ArrayList<String>();
                List<String> computerSkills = new ArrayList<String>();

                if (user.role.equals("Admin")) {

                    if (filter.academicAssistant != null)
                        category.add("Academic Assistant");
                    if (filter.technician != null)
                        category.add("Technician");
                    if (filter.secretary != null)
                        category.add("Secretary");
                    if (filter.librarian != null)
                        category.add("Librarian");
                    if (filter.craftsman != null)
                        category.add("Craftsman");
                    if (filter.animalKeeeper != null)
                        category.add("Animal Keeeper");
                    if (filter.studentAssistant != null)
                        category.add("Student Assistant");
                    if (filter.mittlerer != null)
                        category.add("Mittlerer Dienst");
                    if (filter.hoHerer != null)
                        category.add("HoHerer Dienst");
                    if (filter.gehobener != null)
                        category.add("Gehobener Dienst");
                } else {
                    category.add(user.category_acccess);
                }

                if (filter.experience1 != null) {
                    System.out.println("Exp");
                    experience.add("1");
                }
                if (filter.experience2 != null) {
                    System.out.println("Exp2");
                    experience.add("2");
                }
                if (filter.experience3 != null) {
                    System.out.println("Exp3");
                    experience.add("3");
                }
                if (filter.experience4 != null) {
                    System.out.println("Exp4");
                    experience.add("more");
                }

                if (filter.langGA1 != null) {
                    System.out.println("GA1");
                    language.add("German-A1");
                }
                if (filter.langGA2 != null)
                    language.add("German-A2");
                if (filter.langGB1 != null)
                    language.add("German-B1");
                if (filter.langGB2 != null)
                    language.add("German-B2");
                if (filter.langGC1 != null)
                    language.add("German-C1");
                if (filter.langGC2 != null)
                    language.add("German-C2");

                if (filter.frenchA1 != null)
                    language.add("French-A1");
                if (filter.frenchA2 != null)
                    language.add("French-A2");
                if (filter.frenchB1 != null)
                    language.add("French-B1");
                if (filter.frenchB2 != null)
                    language.add("French-B2");
                if (filter.frenchC1 != null)
                    language.add("French-C1");
                if (filter.frenchC2 != null)
                    language.add("French-C2");

                if (filter.spanishA1 != null)
                    language.add("Spanish-A1");
                if (filter.spanishA2 != null)
                    language.add("Spanish-A2");
                if (filter.spanishB1 != null)
                    language.add("Spanish-B1");
                if (filter.spanishB2 != null)
                    language.add("Spanish-B2");
                if (filter.spanishC1 != null)
                    language.add("Spanish-C1");
                if (filter.spanishC2 != null)
                    language.add("Spanish-C2");

                if (filter.italienA1 != null)
                    language.add("Italien-A1");
                if (filter.italienA2 != null)
                    language.add("Italien-A2");
                if (filter.italienB1 != null)
                    language.add("Italien-B1");
                if (filter.italienB2 != null)
                    language.add("Italien-B2");
                if (filter.italienC1 != null)
                    language.add("Italien-C1");
                if (filter.italienC2 != null)
                    language.add("Italien-C2");

                if (filter.english != null)
                    language.add("English");

                if (filter.word != null)
                    computerSkills.add("MS-Office Word");
                if (filter.excel != null)
                    computerSkills.add("MS-Office Excel");
                if (filter.powerPoint != null)
                    computerSkills.add("MS-Office Powerpoint");
                if (filter.access != null)
                    computerSkills.add("MS-Office Access");
                if (filter.sAPHR != null)
                    computerSkills.add("SAP-HR");
                if (filter.sAPFI != null)
                    computerSkills.add("SAP-FI");
                if (filter.sAPMM != null)
                    computerSkills.add("SAP-MM");


                for(int i=0;i<language.size();i++){
                    System.out.println(language.get(i));
                }

                for(int i=0;i<category.size();i++){
                    System.out.println(category.get(i));
                }

                for(int i=0;i<computerSkills.size();i++){
                    System.out.println(computerSkills.get(i));
                }

                for(int i=0;i<experience.size();i++){
                    System.out.println(experience.get(i));
                }

                List<UserProfile> filteredProfiles = new ArrayList<UserProfile>();
                List<UserProfile> userProfileList = new ArrayList<UserProfile>();
                try {
                    filteredProfiles = mapper.get(user.name);
                } catch(Exception e) {
                    System.out.println("Excep");
                }

                if (filteredProfiles == null) {
                    System.out.println("FilterProfile is null");
                    userProfileList = UserProfile.getAllUserProfilesSortByExp(null, true, user.category_acccess);
                } else
                    userProfileList = UserProfile.getAllUserProfilesSortByExp(filteredProfiles, true, user.category_acccess);

                return ok(recruiter.render(userProfileList, filledForm, user.role));
            }
        } catch (Exception e) {
            return ok(message.render(e.getMessage()));
        }

    }

    public static Result interestedProfile(String userName) {
        try {
            com.feth.play.module.pa.controllers.Authenticate.noCache(response());

            final User user = Application.getLocalUser(session());
            if (!user.role.equals("Recruiter") && !user.role.equals("Admin"))
                return ok(message.render("Sie sind nicht berechtigt"));

            UserProfile userProfile = UserProfile.getUserProfilebyName(userName);
            if (user.role.equals("Recruiter") && !user.category_acccess.equals(userProfile.category))
                return ok(message.render("Sie sind nicht berechtigt"));


            if (user.role.equals("Recruiter")) {

                final String username = "hrtoolinterne@gmail.com";
                final String password = "hrtool123";

                Properties props = new Properties();
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.starttls.enable", "true");
                props.put("mail.smtp.host", "smtp.gmail.com");
                props.put("mail.smtp.port", "587");

                try {
                    Session session = Session.getInstance(props,
                            new javax.mail.Authenticator() {
                                protected PasswordAuthentication getPasswordAuthentication() {
                                    return new PasswordAuthentication(username, password);
                                }
                            });

                    Message message = new MimeMessage(session);
                    message.setFrom(new InternetAddress("hrtoolinterne@gmail.com"));
                    message.setRecipients(Message.RecipientType.TO,
                            InternetAddress.parse("hrtoolinterne@gmail.com"));
                    message.setSubject(user.name + " ist interessiert an "+ userName);
                    message.setText("Hallo Admin"
                            + "\n\n" + user.name + " ist interessiert an " + userName +  "\nGrüße,\nHRTool");

                    Transport.send(message);

                    System.out.println("Done");

                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }
            }


            return ok(interested_view.render(userProfile, user.role));
        } catch (Exception e) {
            return ok(message.render(e.getMessage()));
        }
    }

    public static Result disableJobSeeker(String userName) {
        try {
            com.feth.play.module.pa.controllers.Authenticate.noCache(response());

            final User user = Application.getLocalUser(session());
            if (!user.role.equals("Admin") && !user.name.equals(userName))
                return ok(message.render("Sie sind nicht berechtigt"));

            UserProfile userProfile = UserProfile.getUserProfilebyName(userName);
            if (userProfile.getEnabled() == false) {
                flash(Application.FLASH_ERROR_KEY, "Konto wurde bereits deaktiviert");
                return redirect(routes.Account.profile());
            }
            userProfile.disableJSAccount();

            if (user.role.equals("Admin")) {
                flash(Application.FLASH_ERROR_KEY, "Account wurde deaktiviert");
                return redirect(routes.Application.admin_ViewJobseekerUsers());
            }
            else {
                flash(Application.FLASH_ERROR_KEY, "Ihr Konto wurde deaktiviert");
                return redirect(routes.Account.profile());
            }
        } catch (Exception e) {
            return ok(message.render(e.getMessage()));
        }
    }


    public static Result enableJobSeeker(String userName) {
        try {
            com.feth.play.module.pa.controllers.Authenticate.noCache(response());

            final User user = Application.getLocalUser(session());
            if (!user.role.equals("Admin"))
                return ok(message.render("Sie sind nicht berechtigt"));

            UserProfile userProfile = UserProfile.getUserProfilebyName(userName);
            if (userProfile.getEnabled() == true)
                return ok(message.render("Konto ist bereits aktiviert"));
            userProfile.enableJSAccount();

            flash(Application.FLASH_MESSAGE_KEY, "Ihr Konto wurde aktiviert");
            return redirect(routes.Application.admin_ViewJobseekerUsers());
        } catch (Exception e) {
            return ok(message.render(e.getMessage()));
        }
    }

    public static Result sendEnableJobSeekerRequest(String userName) {
        try {
            com.feth.play.module.pa.controllers.Authenticate.noCache(response());

            final User user = Application.getLocalUser(session());
            if (!user.name.equals(userName))
                return ok(message.render("Sie sind nicht berechtigt"));

            UserProfile userProfile = UserProfile.getUserProfilebyName(userName);
            if (userProfile.getEnabled() == true) {
                flash(Application.FLASH_ERROR_KEY, "Ihr Konto ist bereits aktiviert");
                return redirect(routes.Account.profile());
            }
            userProfile.sendEnableJSAccount();

            flash(Application.FLASH_MESSAGE_KEY, "Eine E-Mail wurde an den Admin gesendet, um Ihr Konto zu aktivieren");
            return redirect(routes.Account.profile());
        } catch (Exception e) {
            return ok(message.render(e.getMessage()));
        }
    }

    public static Result grantAccessToRecruiter(String email) {
        try {
            com.feth.play.module.pa.controllers.Authenticate.noCache(response());
            User user = User.findByEmail(email);
            Date currentDate = new Date();
            LocalDateTime localDateTime = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            localDateTime = localDateTime.plusDays(7);
            Date access = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
            user.access_until = access;
            User user1 = User.modifyAccess(user);
            flash(Application.FLASH_MESSAGE_KEY, "Der Zugang wurde für weitere 7 Tage gewährt Bis " + user1.access_until);
            return redirect(routes.Application.admin_ViewRecruiterUsers());
        } catch (Exception e) {
            return ok(message.render(e.getMessage()));
        }
    }

    public static Result evokeAccessToRecruiter(String email) {
        try {
            com.feth.play.module.pa.controllers.Authenticate.noCache(response());
            User user = User.findByEmail(email);
            Date currentDate = new Date();
            LocalDateTime localDateTime = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            localDateTime = localDateTime.minusDays(1);
            Date access = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
            user.access_until = access;
            User user1 = User.modifyAccess(user);
            flash(Application.FLASH_ERROR_KEY, "Der Zugang wurde widerrufen");
            return redirect(routes.Application.admin_ViewRecruiterUsers());
        } catch (Exception e) {
            return ok(message.render(e.getMessage()));
        }
    }

    public static Result categoryToRecruiter(String email) {
        try {
            com.feth.play.module.pa.controllers.Authenticate.noCache(response());
            User user = User.findByEmail(email);
            final Form<User> categoryForm = form(User.class);
            return ok(recruiters_list.render(user, categoryForm)); 
        } catch (Exception e) {
            return ok(message.render(e.getMessage()));
        }
    }

    public static Result doCategoryUpdate() {
        try {
            com.feth.play.module.pa.controllers.Authenticate.noCache(response());

	    final Form<User> USER_FORM = form(User.class);

	    System.out.println("Here");
            final Form<User> userForm = USER_FORM
                    .bindFromRequest();

	    User user = userForm.get();
	    System.out.println("User  is" + user.name);
            User.modifyCategory(user);
            System.out.println("User category is" + user.category_acccess);
            
	    return redirect(routes.Application.admin_ViewRecruiterUsers());
        } catch (Exception e) {
            return ok(message.render(e.getMessage()));
        }
    }

    public static Result downloadCV(String userName) {
        try {
            com.feth.play.module.pa.controllers.Authenticate.noCache(response());

            final User user = Application.getLocalUser(session());
            if (!user.role.equals("Recruiter") && !user.role.equals("Admin"))
                return ok(message.render("Sie sind nicht berechtigt"));

            UserProfile userProfile = UserProfile.getUserProfilebyName(userName);
            if (user.role.equals("Recruiter") && !user.category_acccess.equals(userProfile.category))
                return ok(message.render("Sie sind nicht berechtigt"));

            File file = UserProfile.retreiveResumeByName(userName);

            if (file == null)
                return redirect(routes.Account.doViewProfiles());

            return ok(file);
        } catch (Exception e) {
            return ok(message.render(e.getMessage()));
        }
    }


    @SubjectPresent
    public static Result askLink() {
        com.feth.play.module.pa.controllers.Authenticate.noCache(response());
        final AuthUser u = PlayAuthenticate.getLinkUser(session());
        if (u == null) {
            // account to link could not be found, silently redirect to login
            return redirect(routes.Application.index());
        }
        return ok(ask_link.render(ACCEPT_FORM, u));
    }

    @SubjectPresent
    public static Result doLink() {
        com.feth.play.module.pa.controllers.Authenticate.noCache(response());
        final AuthUser u = PlayAuthenticate.getLinkUser(session());
        if (u == null) {
            // account to link could not be found, silently redirect to login
            return redirect(routes.Application.index());
        }

        final Form<Accept> filledForm = ACCEPT_FORM.bindFromRequest();
        if (filledForm.hasErrors()) {
            // User did not select whether to link or not link
            return badRequest(ask_link.render(filledForm, u));
        } else {
            // User made a choice :)
            final boolean link = filledForm.get().accept;
            if (link) {
                flash(Application.FLASH_MESSAGE_KEY,
                        Messages.get("playauthenticate.accounts.link.success"));
            }
            return PlayAuthenticate.link(ctx(), link);
        }
    }

    @SubjectPresent
    public static Result askMerge() {
        com.feth.play.module.pa.controllers.Authenticate.noCache(response());
        // this is the currently logged in user
        final AuthUser aUser = PlayAuthenticate.getUser(session());

        // this is the user that was selected for a login
        final AuthUser bUser = PlayAuthenticate.getMergeUser(session());
        if (bUser == null) {
            // user to merge with could not be found, silently redirect to login
            return redirect(routes.Application.index());
        }

        // You could also get the local user object here via
        // User.findByAuthUserIdentity(newUser)
        return ok(ask_merge.render(ACCEPT_FORM, aUser, bUser));
    }

    @SubjectPresent
    public static Result doMerge() {
        com.feth.play.module.pa.controllers.Authenticate.noCache(response());
        // this is the currently logged in user
        final AuthUser aUser = PlayAuthenticate.getUser(session());

        // this is the user that was selected for a login
        final AuthUser bUser = PlayAuthenticate.getMergeUser(session());
        if (bUser == null) {
            // user to merge with could not be found, silently redirect to login
            return redirect(routes.Application.index());
        }

        final Form<Accept> filledForm = ACCEPT_FORM.bindFromRequest();
        if (filledForm.hasErrors()) {
            // User did not select whether to merge or not merge
            return badRequest(ask_merge.render(filledForm, aUser, bUser));
        } else {
            // User made a choice :)
            final boolean merge = filledForm.get().accept;
            if (merge) {
                flash(Application.FLASH_MESSAGE_KEY,
                        Messages.get("playauthenticate.accounts.merge.success"));
            }
            return PlayAuthenticate.merge(ctx(), merge);
        }
    }

}
