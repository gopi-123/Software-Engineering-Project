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

import java.util.*;

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

import static play.data.Form.form;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class UserProfile {
    @Id
    public ObjectId id;

    public String userName;

    public String firstName;

    public String lastName;

    public Date dOB;

    public String empID;

    public String phoneNo;

    public String category;

    public String language;

    public String department;

    public String experience;

    public String disability;

    public Date empAt;

    public Date empSince;

    public Boolean fixedTerm;

    public Date fixedTermTill;

    public String weeklyWorkingTime;

    public String specialWorkTime;

    public String currentJob;

    public String importantTasks;

    public String highestEducationLevel;

    public String additionalEducation;

    public ArrayList<String> languageSkills;

    public ArrayList<String> computerSkills;

    public String futureJobOpportunities;

    public Boolean enabled;

    public Boolean registered;

    //public File resume;

    public UserProfile() {
    }

    public static UserProfile getUserProfilebyName(final String name) {
        try {
            if (MorphiaObject.datastore.createQuery(UserProfile.class).filter("userName", name).get().userName == null) {
                UserProfile userProfile = new UserProfile();
                userProfile.userName = name;
                userProfile.enabled = true;
                userProfile.registered = false;
                MorphiaObject.datastore.save(userProfile);
                return userProfile;
            } else {
                UserProfile userProfile = MorphiaObject.datastore.createQuery(UserProfile.class).filter("userName", name).get();
                return userProfile;
            }
        } catch (Exception e) {
            UserProfile userProfile = new UserProfile();
            userProfile.userName = name;
            userProfile.enabled = true;
            userProfile.registered = false;
            MorphiaObject.datastore.save(userProfile);
            return userProfile;
        }
    }


    //NOTE: Will enable the account if it is disabled. Mutually exclusive
    public Boolean disableJSAccount() {
        try {
            if (this.getEnabled()) {
                final Query<UserProfile> userProfile = MorphiaObject.datastore.createQuery(UserProfile.class)
                        .filter("userName", this.userName);
                final UpdateOperations<UserProfile> updateOperations = MorphiaObject.datastore.createUpdateOperations(UserProfile.class)
                        .set("enabled", false);
                final UpdateResults results = MorphiaObject.datastore.update(userProfile, updateOperations);
                return true;
            } else {
                final Query<UserProfile> userProfile = MorphiaObject.datastore.createQuery(UserProfile.class)
                        .filter("userName", this.userName);
                final UpdateOperations<UserProfile> updateOperations = MorphiaObject.datastore.createUpdateOperations(UserProfile.class)
                        .set("enabled", true);
                final UpdateResults results = MorphiaObject.datastore.update(userProfile, updateOperations);
                return false;
            }
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public Boolean enableJSAccount() {
        try {
            if (!this.getEnabled()) {
                final Query<UserProfile> userProfile = MorphiaObject.datastore.createQuery(UserProfile.class)
                        .filter("userName", this.userName);
                final UpdateOperations<UserProfile> updateOperations = MorphiaObject.datastore.createUpdateOperations(UserProfile.class)
                        .set("enabled", true);
                final UpdateResults results = MorphiaObject.datastore.update(userProfile, updateOperations);
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }


    public Boolean deleteJSAccount() {

        try {
            final Query<UserProfile> userProfile = MorphiaObject.datastore.createQuery(UserProfile.class)
                    .filter("userName ", this.userName);
            MorphiaObject.datastore.delete(userProfile);
            return true;
        } catch (Exception e) {
            System.out.println("Delete JSAccount error is" + e);
            return false;
        }
    }


    public static void updateUserProfile(String userName, UserProfile filledProfile, File resume, String emailID) {


        final Query<UserProfile> userProfile = MorphiaObject.datastore.createQuery(UserProfile.class)
                .filter("userName", userName);
        System.out.println("User is " + userProfile.get().userName);
        UpdateOperations<UserProfile> updateOperations = MorphiaObject.datastore.createUpdateOperations(UserProfile.class);

        updateOperations.set("enabled", true);
        if (filledProfile.userName != null && !filledProfile.userName.isEmpty()) {
            updateOperations.set("userName", filledProfile.userName);
        }
        if (filledProfile.firstName != null && !filledProfile.firstName.isEmpty()) {
            updateOperations.set("registered", true);
            updateOperations.set("firstName", filledProfile.firstName);

            //This is where Job Seeker profile registration is successfull
            notifyUserThroughMail(userName, emailID);
        }
        if (filledProfile.lastName != null && !filledProfile.lastName.isEmpty())
            updateOperations.set("lastName", filledProfile.lastName);
        if (filledProfile.dOB != null)
            updateOperations.set("dOB", filledProfile.dOB);
        if (filledProfile.empID != null && !filledProfile.empID.isEmpty())
            updateOperations.set("empID", filledProfile.empID);
        if (filledProfile.phoneNo != null && !filledProfile.phoneNo.isEmpty())
            updateOperations.set("phoneNo", filledProfile.phoneNo);
        if (filledProfile.category != null && !filledProfile.category.isEmpty())
            updateOperations.set("category", filledProfile.category);
        if (filledProfile.languageSkills != null)
            updateOperations.set("languageSkills", filledProfile.languageSkills);
        if (filledProfile.computerSkills != null)
            updateOperations.set("computerSkills", filledProfile.computerSkills);
        if (filledProfile.department != null && !filledProfile.department.isEmpty())
            updateOperations.set("department", filledProfile.department);
        if (filledProfile.experience != null && !filledProfile.experience.isEmpty())
            updateOperations.set("experience", filledProfile.experience);
        if (filledProfile.disability != null)
            updateOperations.set("disability", filledProfile.disability);
        if (filledProfile.empAt != null)
            updateOperations.set("empAt", filledProfile.empAt);
        if (filledProfile.empSince != null)
            updateOperations.set("empSince", filledProfile.empSince);
        if (filledProfile.fixedTerm != null)
            updateOperations.set("fixedTerm", filledProfile.fixedTerm);
        if (filledProfile.fixedTermTill != null)
            updateOperations.set("fixedTermTill", filledProfile.fixedTermTill);
        if (filledProfile.weeklyWorkingTime != null && !filledProfile.weeklyWorkingTime.isEmpty())
            updateOperations.set("weeklyWorkingTime", filledProfile.weeklyWorkingTime);
        if (filledProfile.specialWorkTime != null && !filledProfile.specialWorkTime.isEmpty())
            updateOperations.set("specialWorkTime", filledProfile.specialWorkTime);
        if (filledProfile.currentJob != null && !filledProfile.currentJob.isEmpty())
            updateOperations.set("currentJob", filledProfile.currentJob);
        if (filledProfile.importantTasks != null && !filledProfile.importantTasks.isEmpty())
            updateOperations.set("importantTasks", filledProfile.importantTasks);
        if (filledProfile.highestEducationLevel != null && !filledProfile.highestEducationLevel.isEmpty())
            updateOperations.set("highestEducationLevel", filledProfile.highestEducationLevel);
        if (filledProfile.additionalEducation != null && !filledProfile.additionalEducation.isEmpty())
            updateOperations.set("additionalEducation", filledProfile.additionalEducation);
        if (filledProfile.futureJobOpportunities != null && !filledProfile.futureJobOpportunities.isEmpty())
            updateOperations.set("futureJobOpportunities", filledProfile.futureJobOpportunities);

        final UpdateResults results = MorphiaObject.datastore.update(userProfile, updateOperations);
        System.out.println("Result is " + results);

        try {
            if (resume != null) {
                DB db = MorphiaObject.datastore.getDB();

                GridFS gfsPhoto = new GridFS(db);
                GridFSInputFile gfsFile = gfsPhoto.createFile(resume);
                gfsFile.setFilename(filledProfile.userName);
                gfsFile.save();
                System.out.println("Resume saved");
            }
        } catch (Exception e) {
            System.out.println("Resume Exception");
        }
    }

    public static List<UserProfile> getAllUserProfiles(Boolean enabledOnly, String category) {

        List<String> userNameList = MorphiaObject.datastore.getCollection(UserProfile.class).distinct("userName");
        List<UserProfile> userProfileList = new ArrayList<UserProfile>();

        for (ListIterator<String> it = userNameList.listIterator(userNameList.size()); it.hasPrevious(); ) {
            String user = it.previous();
            UserProfile userProfile = getUserProfilebyName(user);
            if (userProfile.getRegistered() && userProfile.getEnabled()) {
		if (category == null)
			userProfileList.add(getUserProfilebyName(user));
		else if (category.equals(userProfile.category))
			userProfileList.add(getUserProfilebyName(user));
		else {}
//                continue;
//            if (enabledOnly && userProfile.getEnabled())
//                continue;
//            if (category != null && category.equals(userProfile.category))
//                continue;
		}
        }

        return userProfileList;

    }

    public static List<UserProfile> getAllJobseekerUsers() {

        List<String> userNameList = MorphiaObject.datastore.getCollection(UserProfile.class).distinct("userName");
        List<UserProfile> userProfileList = new ArrayList<UserProfile>();

        for (ListIterator<String> it = userNameList.listIterator(userNameList.size()); it.hasPrevious(); ) {
            String user = it.previous();
            UserProfile userProfile = getUserProfilebyName(user);
            if (userProfile.getRegistered())
                userProfileList.add(getUserProfilebyName(user));
        }
        return userProfileList;

    }

    static class Sortbyroll implements Comparator<UserProfile> {
        // Used for sorting in ascending order of
        // roll number
        public int compare(UserProfile a, UserProfile b) {
            System.out.println("A " + a.userName + "Exp is " + a.experience + " B " + b.userName + "Exp is " + b.experience);
            if (a.experience == null) {
                a.deleteJSAccount();
            }

            if (b.experience == null) {
                b.deleteJSAccount();
            }

            return Integer.parseInt(b.experience) - Integer.parseInt(a.experience);
        }
    }


    public static List<UserProfile> getAllUserProfilesSortByExp(List<UserProfile> filteredProfiles, Boolean enabledOnly, String category) {

	List<UserProfile> userProfileList = new ArrayList<UserProfile>();
	if (filteredProfiles == null)
        	userProfileList = UserProfile.getAllUserProfiles(enabledOnly, category);
	else
		userProfileList = filteredProfiles;


        System.out.println("In UserProfile Experience");
        Collections.sort(userProfileList, new Sortbyroll());
        System.out.println("After callback");


        for (int i = 0; i < userProfileList.size(); i++)
            System.out.println("Username is" + userProfileList.get(i).userName + "Experience is" + userProfileList.get(i).experience);

        return userProfileList;

    }

    public static List<UserProfile> getUserProfilesForFilter(Boolean enabledOnly, List<String> categoryList, List<String> experienceList, List<String> languageList, List<String> computerSkillsList, Boolean isAdmin) {

        List<UserProfile> userProfileList = new ArrayList<UserProfile>();
	List<UserProfile> userProfileFilterList = new ArrayList<UserProfile>();


	for(int i = 0; i < categoryList.size() ; i++) {
		System.out.println("Category is " + categoryList.get(i));
		userProfileList.addAll(UserProfile.getAllUserProfiles(enabledOnly, categoryList.get(i)));
       	}

	if (isAdmin) {
		System.out.println("Is Admin");
		if (categoryList.size() == 0)
			userProfileList.addAll(UserProfile.getAllUserProfiles(enabledOnly, null));
		if ((experienceList.size()) == 0 && (languageList.size() == 0) && (computerSkillsList.size() == 0))
			return userProfileList;
	}

	System.out.println("UP size is" + userProfileList.size());

        for (int i = 0; i < userProfileList.size(); i++) {

             Boolean exp = false;
             Boolean lang = false;
             Boolean comp = false;

            UserProfile userProfile = userProfileList.get(i);
            System.out.println("Pre Profile name is" + userProfile.userName);


		for(int j = 0; j < experienceList.size() ; j++) {
			if (experienceList.get(j).equals("more")) {
				if (Integer.parseInt(userProfile.experience) > 3) {
					userProfileFilterList.add(userProfile);
                                	exp = true;
                                	break;
				}
			}
			if (userProfile.experience.equals(experienceList.get(j))) {
				userProfileFilterList.add(userProfile);
				exp = true;
				break;
			}
        	}

		if (exp == true)
			continue;

		for(int j = 0; j < languageList.size() ; j++) {
			for(int k = 0; k < userProfile.languageSkills.size() ; k++) {
				String userLang = userProfile.languageSkills.get(k);
                        	if (userLang.equals(languageList.get(j))) {
                                	userProfileFilterList.add(userProfile);
                                	lang = true;
                                	break;
				}
                        }
			if ( lang == true)
				break;
                }

                if (lang == true)
                        continue;

		for(int j = 0; j < computerSkillsList.size() ; j++) {
                        for(int k = 0; k < userProfile.computerSkills.size() ; k++) {
                                String userComp = userProfile.computerSkills.get(k);
                                if (userComp.equals(computerSkillsList.get(j))) {
                                        userProfileFilterList.add(userProfile);
                                        comp = true;
                                        break;
                                }
                        }
                        if (comp == true)
                                break;
                }

        }

        return userProfileFilterList;
    }


    public static File retreiveResumeByName(String userName) {
        try {
            DB db = MorphiaObject.datastore.getDB();

            GridFS gfsResume = new GridFS(db);
            GridFSDBFile resumeForOutput = gfsResume.findOne(userName);

            File file = File.createTempFile(userName, ".pdf");
            resumeForOutput.writeTo(file);
            file.deleteOnExit();

            return file;
        } catch (Exception e) {
            return null;
        }
    }


    public void sendEnableJSAccount() {

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
            message.setSubject(this.userName + " möchte sein Konto aktivieren");
            message.setText("Hallo Admin,"
                    + "\n\n" + this.userName + " möchte sein Konto aktivieren.\nGrüße,\nHRTool");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }


    public static void notifyUserThroughMail(String userName, String emailID) {

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
                    InternetAddress.parse(emailID));
            message.setSubject("Anmeldung erfolgreich für " + userName);
            message.setText("Hallo " + username + ","
                    + "\n\n Sie haben sich erfolgreich beim HR-Tool angemeldet. Jetzt können die Personalvermittler Ihr Profil sehen und wir werden Sie benachrichtigen, wenn wir irgendwelche Updates bekommen." + "\nGrüße,\nHRTool");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }


    public String getUserName() {
        return userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDOB() {
        return dOB;
    }

    public void setDOB(Date dOB) {
        this.dOB = dOB;
    }

    public String getEmpID() {
        return empID;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDisability() {
        return disability;
    }

    public void setDisability(String disability) {
        this.disability = disability;
    }

    public Date getEmpAt() {
        return empAt;
    }

    public void setEmpAt(Date empAt) {
        this.empAt = empAt;
    }

    public Date getEmpSince() {
        return empSince;
    }

    public void setEmpSince(Date empSince) {
        this.empSince = empSince;
    }

    public Boolean getFixedTerm() {
        return fixedTerm;
    }

    public void setFixedTerm(Boolean fixedTerm) {
        this.fixedTerm = fixedTerm;
    }

    public Boolean getEnabled() {
        if (enabled == null) {
            return true;
        }
        return enabled;
    }

    public Boolean getRegistered() {
        if (registered == null)
            return false;
        return registered;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
