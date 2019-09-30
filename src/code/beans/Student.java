package code.beans;

public class Student extends User {


    public Student(String user_name, String user_password, String user_email, String user_fullName, String user_college, String user_gender, String user_linkedin, String user_github, String user_contact) {
        super(user_name, user_password, user_email, user_fullName, user_college, user_gender, user_linkedin, user_github, user_contact);
    }

    public Student(int user_id, String user_name, String user_password, String user_email, String user_fullName, String user_college, String user_gender, String user_linkedin, String user_github, String user_contact) {
        super(user_id, user_name, user_password, user_email, user_fullName, user_college, user_gender, user_linkedin, user_github, user_contact);
    }

    public Student(){

    }


}
