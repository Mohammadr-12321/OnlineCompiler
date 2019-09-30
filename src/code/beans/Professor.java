package code.beans;

import java.util.List;

public class Professor extends User{
    private List<String> lectures;
    private List<String> field_study;
    private List<String> practise;

    public Professor(){
        super();
    }
    public Professor(String user_name, String user_password, String user_email, String user_fullName, String user_college, String user_gender, String user_linkedin, String user_github, String user_contact) {
        super(user_name, user_password, user_email, user_fullName, user_college, user_gender, user_linkedin, user_github, user_contact);
    }

    public Professor(int user_id, String user_name, String user_password, String user_email, String user_fullName, String user_college, String user_gender, String user_linkedin, String user_github, String user_contact) {
        super(user_id, user_name, user_password, user_email, user_fullName, user_college, user_gender, user_linkedin, user_github, user_contact);
    }

    public void setLectures(List<String> lectures) {
        this.lectures = lectures;
    }

    public void setDegree(List<String> field_study) {
        this.field_study = field_study;
    }

    public void setPractise(List<String> practise) {
        this.practise = practise;
    }

    public List<String> getLectures() {
        return lectures;
    }

    public List<String> getDegree() {
        return field_study;
    }

    public List<String> getPractise() {
        return practise;
    }
}
