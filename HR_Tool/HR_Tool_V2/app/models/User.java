package models;

import be.objectify.deadbolt.core.models.Permission;
import be.objectify.deadbolt.core.models.Role;
import be.objectify.deadbolt.core.models.Subject;
import com.feth.play.module.pa.providers.password.UsernamePasswordAuthUser;
import com.feth.play.module.pa.user.*;
import helper.datasources.MorphiaObject;
import models.TokenAction.Type;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.query.Query;
import play.data.format.Formats;
import play.data.validation.Constraints;
import org.mongodb.morphia.query.UpdateResults;
import org.mongodb.morphia.query.*;

import java.util.*;
import java.time.LocalDateTime;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * @author Subhashini Jantwal
 */
@Entity
public class User implements Subject {
    @Id
    public ObjectId id;

    @Constraints.Email
    // if you make this unique, keep in mind that users *must* merge/link their
    // accounts then on signup with additional providers
    // @Column(unique = true)
    public String email;

    public String name;

    public String firstName;

    public String lastName;

    @Formats.DateTime(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date lastLogin;

    public boolean active;

    public boolean emailValidated;

    public String department;

    public String empID;

    public String role;

    public String category_acccess;

    @Formats.DateTime(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date access_until;

    @Embedded
    public List<SecurityRole> roles = new ArrayList<SecurityRole>();

    @Embedded
    public List<LinkedAccount> linkedAccounts = new ArrayList<LinkedAccount>();

    @Embedded
    public List<UserPermission> permissions = new ArrayList<UserPermission>();

    @Override
    public String getIdentifier() {
        return id.toString();
    }

    @Override
    public List<? extends Role> getRoles() {

        return roles;
    }

    @Override
    public List<? extends Permission> getPermissions() {

        return permissions;
    }

    public List<UserPermission> getUserPermissions() {
        return permissions;
    }

    public List<LinkedAccount> getLinkedAccounts() {
        return linkedAccounts;
    }

    public static boolean existsByAuthUserIdentity(final AuthUserIdentity identity) {
        final Query<User> exp;
        if (identity instanceof UsernamePasswordAuthUser) {
            exp = getUsernamePasswordAuthUserFind((UsernamePasswordAuthUser) identity);
        } else {
            exp = getAuthUserFind(identity);
        }
        return exp.countAll() > 0;
    }

    private static Query<User> getAuthUserFind(
            final AuthUserIdentity identity) {
        return MorphiaObject.datastore.createQuery((User.class)).filter("active =", true)
                .filter("linkedAccounts.providerUserId", identity.getId())
                .filter("linkedAccounts.providerKey", identity.getProvider());
    }

    public static User findByAuthUserIdentity(final AuthUserIdentity identity) {
        if (identity == null) {
            return null;
        }
        if (identity instanceof UsernamePasswordAuthUser) {
            return findByUsernamePasswordIdentity((UsernamePasswordAuthUser) identity);
        } else {
            return getAuthUserFind(identity).get();
        }
    }

    public static User findByUsernamePasswordIdentity(
            final UsernamePasswordAuthUser identity) {
        return getUsernamePasswordAuthUserFind(identity).get();
    }

    private static Query<User> getUsernamePasswordAuthUserFind(
            final UsernamePasswordAuthUser identity) {
        return getEmailUserFind(identity.getEmail())
                .filter("linkedAccounts.providerKey", identity.getProvider());
    }

    public void merge(final User otherUser) {
        for (final LinkedAccount acc : otherUser.linkedAccounts) {
            this.linkedAccounts.add(LinkedAccount.create(acc));
        }
        // do all other merging stuff here - like resources, etc.

        // deactivate the merged user that got added to this one
        otherUser.active = false;
        MorphiaObject.datastore.save(otherUser);
        MorphiaObject.datastore.save(this);
    }

    public static User create(final AuthUser authUser) {
        final User user = new User();
        user.roles = Collections.singletonList(SecurityRole
                .findByRoleName(controllers.Application.USER_ROLE));
        // user.permissions = new ArrayList<UserPermission>();
        // user.permissions.add(UserPermission.findByValue("printers.edit"));
        user.active = true;
        user.lastLogin = new Date();
        user.role = "Jobseeker";
        user.linkedAccounts = Collections.singletonList(LinkedAccount
                .create(authUser));

        if (authUser instanceof EmailIdentity) {
            final EmailIdentity identity = (EmailIdentity) authUser;
            // Remember, even when getting them from FB & Co., emails should be
            // verified within the application as a security breach there might
            // break your security as well!
            user.email = identity.getEmail();
            user.emailValidated = false;
        }

        if (authUser instanceof NameIdentity) {
            final NameIdentity identity = (NameIdentity) authUser;
            final String name = identity.getName();
            if (name != null) {
                user.name = name;
            }
        }

        if (authUser instanceof FirstLastNameIdentity) {
            final FirstLastNameIdentity identity = (FirstLastNameIdentity) authUser;
            final String firstName = identity.getFirstName();
            final String lastName = identity.getLastName();
            if (firstName != null) {
                user.firstName = firstName;
            }
            if (lastName != null) {
                user.lastName = lastName;
            }
        }

        MorphiaObject.datastore.save(user);

        // user.saveManyToManyAssociations("roles");
        // user.saveManyToManyAssociations("permissions");
        return user;
    }

//
//    public List<User> getAllUsers() {
//
//        List<String> userNameList = MorphiaObject.datastore.getCollection(User.class).distinct("email");
//        List<User> userList = new ArrayList<User>();
//
//        for (ListIterator<String> it = userNameList.listIterator(userNameList.size()); it.hasPrevious(); ) {
//            String user = it.previous();
//            User user1 = findByEmail(user);
//            userList.add(user1);
//        }
//        return userList;
//
//    }


    public List<User> getRecruitersList() {
	final Query<User> userQuery = MorphiaObject.datastore.createQuery(User.class)
                                             .filter("role", "Recruiter");
	List<User> userList = new ArrayList<>();

	for (ListIterator<User> it = userQuery.asList().listIterator(userQuery.asList().size()); it.hasPrevious(); ) {
	  	User user = it.previous();
		if (user.name != null)
			userList.add(user);
	}

	return userList;
    }

//    public List<User> getJobseekersList() {
//        final Query<User> userQuery = MorphiaObject.datastore.createQuery(User.class)
//                .filter("role", "Jobseeker");
//        List<User> userList = new ArrayList<>();
//
//        for (ListIterator<User> it = userQuery.asList().listIterator(userQuery.asList().size()); it.hasPrevious(); ) {
//            User user = it.previous();
//            if (user.name != null)
//                userList.add(user);
//        }
//
//        return userList;
//    }

    public static User modifyAccess(User user) {
            MorphiaObject.datastore.save(user);
            return user;
    }

    public static User modifyCategory(User user) {
	    /*MorphiaObject.datastore.save(user);
            return user;*/
            final Query<User> userUpdate = MorphiaObject.datastore.createQuery(User.class)
                .filter("name", user.name);
            UpdateOperations<User> updateOperations = MorphiaObject.datastore.createUpdateOperations(User.class);
	    updateOperations.set("category_acccess", user.category_acccess);

	    final UpdateResults results = MorphiaObject.datastore.update(userUpdate, updateOperations);
            System.out.println("User category in modifyCategory " + user.category_acccess + "User is " + user.name);

            return user;
    }

    public static void merge(final AuthUser oldUser, final AuthUser newUser) {
        User.findByAuthUserIdentity(oldUser).merge(
                User.findByAuthUserIdentity(newUser));
    }

    public Set<String> getProviders() {
        final Set<String> providerKeys = new HashSet<String>(
                linkedAccounts.size());
        for (final LinkedAccount acc : linkedAccounts) {
            providerKeys.add(acc.providerKey);
        }
        return providerKeys;
    }

    public static void addLinkedAccount(final AuthUser oldUser,
                                        final AuthUser newUser) {
        final User u = User.findByAuthUserIdentity(oldUser);
        u.linkedAccounts.add(LinkedAccount.create(newUser));
        MorphiaObject.datastore.save(u);
    }

    public static void setLastLoginDate(final AuthUser knownUser) {
        final User u = User.findByAuthUserIdentity(knownUser);
        u.lastLogin = new Date();
        MorphiaObject.datastore.save(u);
    }

    public static User findByEmail(final String email) {
        return getEmailUserFind(email).get();
    }

    private static Query<User> getEmailUserFind(final String email) {
        return MorphiaObject.datastore.createQuery(User.class).filter("active", true)
                .filter("email", email);
    }

    public LinkedAccount getAccountByProvider(final String providerKey) {
        return LinkedAccount.findByProviderKey(this, providerKey);
    }

    public static void verify(final User unverified) {
        // You might want to wrap this into a transaction
        unverified.emailValidated = true;
        MorphiaObject.datastore.save(unverified);

        TokenAction.deleteByUser(unverified, Type.EMAIL_VERIFICATION);
    }

    public void changePassword(final UsernamePasswordAuthUser authUser,
                               final boolean create) {
        LinkedAccount a = this.getAccountByProvider(authUser.getProvider());
        if (a == null) {
            if (create) {
                a = LinkedAccount.create(authUser);
            } else {
                throw new RuntimeException(
                        "Konto ist für die Verwendung des Passworts nicht aktiviert");
            }
        }
        a.providerUserId = authUser.getHashedPassword();
        this.linkedAccounts.add(a);
        MorphiaObject.datastore.save(this);
    }

    public void resetPassword(final UsernamePasswordAuthUser authUser,
                              final boolean create) {
        // You might want to wrap this into a transaction
        this.changePassword(authUser, create);
        TokenAction.deleteByUser(this, Type.PASSWORD_RESET);
    }
}
