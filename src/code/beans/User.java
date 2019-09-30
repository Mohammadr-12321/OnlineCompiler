package code.beans;

public class User {
    private int    user_id;
    private String user_name;
    private String user_password;
    private String user_email;
    private String user_fullName;
    private String user_college;
    private String user_gender;
    private String user_linkedin;
    private String user_github;
    private String user_contact;

    public User() {
    }

    public User(String user_name, String user_password, String user_email,
                String user_fullName, String user_college, String user_gender,
                String user_linkedin, String user_github, String user_contact) {
        this.user_name = user_name;
        this.user_password = user_password;
        this.user_email = user_email;
        this.user_fullName = user_fullName;
        this.user_college = user_college;
        this.user_gender = user_gender;
        this.user_linkedin=user_linkedin;
        this.user_github=user_github;
        this.user_contact=user_contact;
    }
    public User(int user_id,String user_name, String user_password, String user_email,
                String user_fullName, String user_college, String user_gender,
                String user_linkedin,String user_github,String user_contact){
        this.user_id=user_id;
        this.user_name = user_name;
        this.user_password = user_password;
        this.user_email = user_email;
        this.user_fullName = user_fullName;
        this.user_college = user_college;
        this.user_gender = user_gender;
        this.user_linkedin=user_linkedin;
        this.user_github=user_github;
        this.user_contact=user_contact;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_fullName() {
        return user_fullName;
    }

    public void setUser_fullName(String user_fullName) {
        this.user_fullName = user_fullName;
    }

    public String getUser_college() {
        return user_college;
    }

    public void setUser_college(String user_college) {
        this.user_college = user_college;
    }

    public String getUser_gender() {
        return user_gender;
    }

    public void setUser_gender(String user_gender) {
        this.user_gender = user_gender;
    }
    public String getUser_linkedin(){
        return this.user_linkedin;
    }
    public void setUser_linkedin(String user_linkedin){
        this.user_linkedin=user_linkedin;
    }

    public String getUser_github(){
        return user_github;
    }

    public void setUser_github(String user_github){
        this.user_github=user_github;
    }

    public void setUser_contact(String user_contact){
        this.user_contact=user_contact;
    }

    public String getUser_contact(){
        return user_contact;
    }

    public String toString(){
        return "User Name is : "+user_name+" user full name is : "+user_fullName;
    }
}

