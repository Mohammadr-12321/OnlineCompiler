package code.beans;

public class Practise {

    private int practise_id;
    private String practise_title;
    private String programming_languages;
    private String practise_description;
    private String lecture_name;

    public Practise(int practise_id,String practise_title, String programming_languages, String practise_description, String lecture_name) {
        this.practise_id=practise_id;
        this.practise_title = practise_title;
        this.programming_languages = programming_languages;
        this.practise_description = practise_description;
        this.lecture_name = lecture_name;
    }

    public int getPractise_id() {
        return practise_id;
    }

    public void setPractise_id(int practise_id) {
        this.practise_id = practise_id;
    }

    public String getPractise_title() {
        return practise_title;
    }

    public void setPractise_title(String practise_title) {
        this.practise_title = practise_title;
    }

    public String getProgramming_languages() {
        return programming_languages;
    }

    public void setProgramming_languages(String programming_languages) {
        this.programming_languages = programming_languages;
    }

    public String getPractise_description() {
        return practise_description;
    }

    public void setPractise_description(String practise_description) {
        this.practise_description = practise_description;
    }


    public String getLecture_name() {
        return lecture_name;
    }

    public void setLecture_name(String lecture_name) {
        this.lecture_name = lecture_name;
    }
}
