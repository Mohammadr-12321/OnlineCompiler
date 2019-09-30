package code.Model;

import code.Security.Encryption;
import code.beans.*;


import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAO {

        public int RegisterUser(String user_name,String user_password,String user_email,
                                String user_fullName, String user_college,String user_gender,
                                String user_linkedin,String user_github,
                                String user_contact,String isProfessor,Connection connection){
            String sql;

            if(isProfessor.equalsIgnoreCase("yes"))
                sql="INSERT INTO Professor(user_name,user_password,user_email,user_fullName,user_college,user_gender,user_linkedin,user_github,user_contact) VALUES(?,?,?,?,?,?,?,?,?)";
            else
                sql="INSERT INTO Student(user_name,user_password,user_email,user_fullName,user_college,user_gender,user_linkedin,user_github,user_contact) VALUES(?,?,?,?,?,?,?,?,?)";

            PreparedStatement statement=null;
            ResultSet resultSet=null;
            int id_of_the_last_inserted_value=0;
            try {

                statement=connection.prepareStatement(sql,statement.RETURN_GENERATED_KEYS);

                statement.setString(1,user_name);
                statement.setString(2,Encryption.encryptPassword(user_password));
                statement.setString(3,user_email);
                statement.setString(4,user_fullName);
                statement.setString(5,user_college);
                statement.setString(6,user_gender);
                statement.setString(7,user_linkedin);
                statement.setString(8,user_github);
                statement.setString(9,user_contact);

                statement.executeUpdate();
                resultSet=statement.getGeneratedKeys();
                resultSet.next();
                id_of_the_last_inserted_value=resultSet.getInt(1);
                System.out.println("User id : "+id_of_the_last_inserted_value);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return id_of_the_last_inserted_value;
        }

        public User validateUser(String user_name,String user_email, String user_password,String isProfessor ,Connection connection){
            User user=null;
            String sql;
            // Select Sql Query
            if(isProfessor.equalsIgnoreCase("yes")){
                sql="SELECT * FROM Professor WHERE user_name=? AND user_email=?";
            }
            else{
                sql="SELECT * FROM Student WHERE user_name=? AND user_email=?";
            }

            // Create Prepared Statement Object
            PreparedStatement statement;

            // Create Reference Variable from Result Set Class
            // This Reference Variable is in the Stack part of memory
            ResultSet resultSet=null;
            try {
                statement=connection.prepareStatement(sql);

                // Set Parameters to Prepared Statement Object
                statement.setString(1,user_name);
                statement.setString(2,user_email);

                // Execute Sql Query and get  the Result Set
                resultSet=statement.executeQuery();

                if(resultSet.next()){
                    // Create Object from class User
                    // This Object is in the Heap part of memory
                    if(Encryption.checkPassword(user_password,resultSet.getString("user_password"))){
                        if(isProfessor.equalsIgnoreCase("yes")){
                            user=new Professor(resultSet.getInt("user_id"),user_name,user_password,resultSet.getString("user_email")
                                    ,resultSet.getString("user_fullName"),resultSet.getString("user_college")
                                    ,resultSet.getString("user_gender"),resultSet.getString("user_linkedin"),
                                    resultSet.getString("user_github"),resultSet.getString("user_contact"));
                            ((Professor) user).setDegree(getDegreeFieldStudyOfProfessor(user.getUser_id(),connection));
                            ((Professor) user).setLectures(getLecturesOfProfessor(user.getUser_id(),connection));
                        }
                        else{
                            user=new Student(resultSet.getInt("user_id"),user_name,user_password,resultSet.getString("user_email")
                                    ,resultSet.getString("user_fullName"),resultSet.getString("user_college")
                                    ,resultSet.getString("user_gender"),resultSet.getString("user_linkedin"),
                                    resultSet.getString("user_github"),resultSet.getString("user_contact"));
                        }
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            return user;
        }

    public int insertTODB(String file_name, InputStream file_data,String file_type,Connection connection){

        // Write Insert Sql Query
        String sql="INSERT INTO File(file_name,file_data,file_type) VALUES(?,?,?)";
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        int id_of_the_last_inserted_value=0;
        try {
            statement=connection.prepareStatement(sql,statement.RETURN_GENERATED_KEYS);
            statement.setString(1,file_name);
            statement.setBlob(2,file_data);
            statement.setString(3,file_type);

            statement.executeUpdate();
            resultSet=statement.getGeneratedKeys();
            resultSet.next();
            id_of_the_last_inserted_value=resultSet.getInt(1);

            System.out.println("file id : "+id_of_the_last_inserted_value);


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id_of_the_last_inserted_value;
    }

    // insert into professor file table or student file table

    public void pfORsf(int user_id,int file_id,String isProfessor,Connection connection){
            String sql;
            if(isProfessor.equalsIgnoreCase("yes")){
                sql="INSERT INTO Professor_File(professor_id,file_id) VALUES(?,?)";
            }
            else{
                sql="INSERT INTO Student_File(Student_id,file_id) VALUES(?,?)";
            }

        try {
            PreparedStatement statement=connection.prepareStatement(sql);
            statement.setInt(1,user_id);
            statement.setInt(2,file_id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // insert into professor lecture
    public void insertIntoProfessorLecture(int professor_id,int lecture_id,Connection connection){
            String sql="INSERT INTO Professor_Lecture(professor_id,lecture_id) VALUES(?,?)";
        PreparedStatement statement= null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1,professor_id);
            statement.setInt(2,lecture_id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<File> getFilsFromDB(int user_id,String isProfessor, Connection connection){

        List<File> files=new ArrayList<File>();

        String sql;
        if(isProfessor.equalsIgnoreCase("yes"))
            sql="SELECT * FROM Professor INNER JOIN Professor_File " +
                    "ON Professor.user_id=Professor_File.professor_id INNER JOIN File " +
                    "ON Professor_File.file_id=File.file_id " +
                    "WHERE Professor.user_id=?";
        else
            sql="SELECT * FROM Student INNER JOIN Student_File " +
                    "ON Student.user_id=Student_File.student_id INNER JOIN File " +
                    "ON Student_File.file_id=File.file_id " +
                    "WHERE Student.user_id=?";

        ResultSet resultSet=null;
        PreparedStatement statement=null;
        try {
            statement=connection.prepareStatement(sql);
            statement.setInt(1,user_id);
            resultSet=statement.executeQuery();

            while(resultSet.next()){
                String file_name=resultSet.getString("file_name");
                Blob blob=resultSet.getBlob("file_data");
                String file_type=resultSet.getString("file_type");
                File file=new File(file_name,blob,file_type);
                files.add(file);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return files;
    }

    public List<String> getLecturesOfProfessor(int professor_id,Connection connection){
            ResultSet resultSet=null;
            List<String> lectures=new ArrayList<>();
            String sql="SELECT lecture_name FROM Professor INNER JOIN Professor_Lecture " +
                    "ON Professor.user_id=Professor_Lecture.professor_id INNER JOIN Lecture " +
                    "ON Professor_Lecture.lecture_id=Lecture.lecture_id " +
                    "WHERE Professor.user_id=?";
        try {
            PreparedStatement statement=connection.prepareStatement(sql);
            statement.setInt(1,professor_id);
            resultSet=statement.executeQuery();
            while (resultSet.next()){
                lectures.add(resultSet.getString("lecture_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lectures;
    }

    public File retrieveFileFromDataBase(int user_id,String file_name,Connection connection){

            File file=null;
            String sql="SELECT * FROM File WHERE user_id=? AND file_name=?";
            PreparedStatement statement=null;
            ResultSet myResultSet=null;

            try{
                statement=connection.prepareStatement(sql);
                statement.setInt(1,user_id);
                statement.setString(2,file_name);
                myResultSet=statement.executeQuery();

                while (myResultSet.next()){
                    file=new File(myResultSet.getString("file_name"),myResultSet.getBlob("file_data"),myResultSet.getString("file_type"));
                }
            }
            catch (SQLException e){
                e.printStackTrace();
            }

            return file;
    }

    public File retrieveUserProfileImage(int user_id,String file_type,String isProfessor,Connection connection){

            String sql;
            if(isProfessor.equalsIgnoreCase("yes"))
                sql="SELECT File.file_name,File.file_data,File.file_type " +
                        "FROM Professor INNER JOIN Professor_File " +
                        "ON Professor.user_id=Professor_File.professor_id INNER JOIN File " +
                        "ON Professor_File.file_id=File.file_id " +
                        "WHERE Professor.user_id=? AND File.file_type=?";
            else
                sql="SELECT File.file_name,File.file_data,File.file_type " +
                        "FROM Student INNER JOIN Student_File " +
                        "ON Student.user_id=Student_File.student_id " +
                        "INNER JOIN File ON Student_File.file_id=File.file_id " +
                        "WHERE Student.user_id=? AND File.file_type=?";
            ResultSet myResultSet=null;
            File file=null;
        try {
            PreparedStatement statement=connection.prepareStatement(sql);
            statement.setInt(1,user_id);
            statement.setString(2,file_type);
            myResultSet=statement.executeQuery();

            while(myResultSet.next()){
                file=new File(myResultSet.getString("file_name"),myResultSet.getBlob("file_data"),myResultSet.getString("file_type"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return file;
    }

    public int insertToContactUSTable(String name,String email,String message,Connection connection){
            int rowsAffected=0;
            String sql="INSERT INTO ContactUS(name,email,message) VALUES(?,?,?)";
        try {

            PreparedStatement statement=connection.prepareStatement(sql);
            statement.setString(1,name);
            statement.setString(2,email);
            statement.setString(3,message);

            rowsAffected=statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rowsAffected;
    }

    public void setDegreeFieldStudyOfProfessor(int professor_id,int degree_id,int field_study_id,Connection connection){
            String sql="INSERT INTO Professor_Degree_FieldStudy(professor_id,degree_id,field_study_id) " +
                    "VALUES(?,?,?)";
        try {
            PreparedStatement statement=connection.prepareStatement(sql);
            statement.setInt(1,professor_id);
            statement.setInt(2,degree_id);
            statement.setInt(3,field_study_id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> getDegreeFieldStudyOfProfessor(int professor_id,Connection connection){
            String sql="SELECT field_study_title,Degree.degree_name FROM Professor_Degree_FieldStudy INNER JOIN FieldStudy " +
                    "ON Professor_Degree_FieldStudy.field_study_id=FieldStudy.field_study_id " +
                    "INNER JOIN Degree ON Professor_Degree_FieldStudy.degree_id=Degree.degree_id " +
                    "WHERE Professor_Degree_FieldStudy.professor_id=?";
            ResultSet resultSet=null;
            List<String> field_study=new ArrayList<>();
        try {
            PreparedStatement statement=connection.prepareStatement(sql);
            statement.setInt(1,professor_id);
            resultSet=statement.executeQuery();
            while(resultSet.next()){
                field_study.add(resultSet.getString("degree_name")+" IN "+resultSet.getString("field_study_title"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return field_study;
    }

    public List<Professor> getProfessorsThatPresentThisLecture(String lecture_name,Connection connection){
            List<Professor> professor_list=new ArrayList<>();
            ResultSet resultSet=null;
            String sql="SELECT user_id,user_fullName FROM Professor INNER JOIN Professor_Lecture " +
                    "ON Professor.user_id=Professor_Lecture.professor_id INNER JOIN Lecture " +
                    "ON Professor_Lecture.lecture_id=Lecture.lecture_id " +
                    "WHERE Lecture.lecture_name=?";
        try {
            PreparedStatement statement=connection.prepareStatement(sql);
            statement.setString(1,lecture_name);
            resultSet=statement.executeQuery();
            while(resultSet.next()){
                Professor professor=new Professor();
                professor.setUser_id(resultSet.getInt("user_id"));
                professor.setUser_fullName(resultSet.getString("user_fullName"));
                professor_list.add(professor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return professor_list;
    }

    public int selectDesiredLectureWithDesiredProfessor(int student_id,int professor_id,int lecture_id,Connection connection){
            int id_of_the_last_inserted_value=0;
            String sql="INSERT INTO Student_Professor_Lecture(student_id,professor_id,lecture_id) VALUES(?,?,?)";
            PreparedStatement statement=null;
            ResultSet resultSet=null;
        try {
            statement=connection.prepareStatement(sql,statement.RETURN_GENERATED_KEYS);
            statement.setInt(1,student_id);
            statement.setInt(2,professor_id);
            statement.setInt(3,lecture_id);
            statement.executeUpdate();
            resultSet=statement.getGeneratedKeys();
            resultSet.next();
            id_of_the_last_inserted_value=resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id_of_the_last_inserted_value;
    }

    public List<Professor> selectedLectures(int student_id,Connection connection){
            List<Professor> professors=new ArrayList<>();
            ResultSet resultSet=null;
            String sql="SELECT Professor.user_fullName,Professor.user_id,Lecture.lecture_name " +
                    "FROM Professor INNER JOIN Student_Professor_Lecture " +
                    "ON Professor.user_id=Student_Professor_Lecture.professor_id " +
                    "INNER JOIN Lecture ON Student_Professor_Lecture.lecture_id=Lecture.lecture_id " +
                    "WHERE Student_Professor_Lecture.student_id=?";
        try {
            PreparedStatement statement=connection.prepareStatement(sql);
            statement.setInt(1,student_id);
            resultSet=statement.executeQuery();
            while(resultSet.next()){
                Professor professor=new Professor();
                professor.setUser_fullName(resultSet.getString("user_fullName"));
                professor.setUser_id(resultSet.getInt("user_id"));
                List<String> lectures=new ArrayList<>();
                lectures.add(resultSet.getString("lecture_name"));
                professor.setLectures(lectures);
                professors.add(professor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return professors;
    }

    public List<Practise> getPractises(int student_id,int professor_id,int lecture_id,Connection connection){
            String sql="SELECT Practise.practise_id,Practise.practise_title,Practise.programming_languages,File.file_data,Lecture.lecture_name FROM Student_Professor_Lecture " +
                    "INNER JOIN Professor_Practise_Lecture ON " +
                    "Student_Professor_Lecture.professor_id=Professor_Practise_Lecture.professor_id AND " +
                    "Student_Professor_Lecture.lecture_id=Professor_Practise_Lecture.lecture_id " +
                    "INNER JOIN Practise ON Professor_Practise_Lecture.practise_id=Practise.practise_id " +
                    "INNER JOIN File ON Practise.file_id=File.file_id " +
                    "INNER JOIN Lecture ON Professor_Practise_Lecture.lecture_id=Lecture.lecture_id " +
                    "WHERE Student_Professor_Lecture.student_id=? AND Student_Professor_Lecture.professor_id=? " +
                    "AND Professor_Practise_Lecture.lecture_id=?";
        PreparedStatement statement= null;
        ResultSet resultSet=null;
        List<Practise> practiseList=new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1,student_id);
            statement.setInt(2,professor_id);
            statement.setInt(3,lecture_id);
            resultSet=statement.executeQuery();
            while(resultSet.next()){
                int practise_id=resultSet.getInt("practise_id");
                String practise_title=resultSet.getString("practise_title");
                String programming_languages=resultSet.getString("programming_languages");
                File file=new File(null,resultSet.getBlob("file_data"));
                String practise_description=null;
                try {
                    practise_description=file.convertInputStreamToString();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String lecture_name=resultSet.getString("lecture_name");
                Practise practise=new Practise(practise_id,practise_title,programming_languages,practise_description,lecture_name);
                practiseList.add(practise);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return practiseList;
    }

    public List<String> getPresentedLectures(int professor_id,Connection connection){
            List<String> presentedLectures=new ArrayList<>();
            ResultSet resultSet=null;
            String sql="SELECT lecture_name FROM Professor_Lecture INNER JOIN Lecture ON Professor_Lecture.lecture_id=Lecture.lecture_id WHERE Professor_Lecture.professor_id=?";
        try {
            PreparedStatement statement=connection.prepareStatement(sql);
            statement.setInt(1,professor_id);
            resultSet=statement.executeQuery();
            while(resultSet.next()){
                presentedLectures.add(resultSet.getString("lecture_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return presentedLectures;
    }

    public String professorPresentDesiredLecture(int professor_id,int lecture_id,Connection connection){
            if(alreadyPresentLecture(professor_id,lecture_id,connection)){
                return "failed to add record into data base because you already present this lecture";
            }
            else{
                String sql="INSERT INTO Professor_Lecture(professor_id,lecture_id) VALUES(?,?)";
                try {
                    PreparedStatement statement=connection.prepareStatement(sql);
                    statement.setInt(1,professor_id);
                    statement.setInt(2,lecture_id);
                    statement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return "select lecture to present by proessor is successful and  record added to data base ";
    }

    public boolean alreadyPresentLecture(int professor_id,int lecture_id,Connection connection){
            ResultSet resultSet=null;
            boolean result=false;
            String sql="SELECT * FROM Professor_Lecture WHERE professor_id=? AND lecture_id=?";
        try {
            PreparedStatement statement=connection.prepareStatement(sql);
            statement.setInt(1,professor_id);
            statement.setInt(2,lecture_id);
            resultSet=statement.executeQuery();
            if(resultSet.next())
                result=true;
            else
                result=false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Student> getListOfStudentOfThisLecture(int professor_id,int lecture_id,Connection connection){
            String sql="SELECT user_id,user_fullName,user_college FROM Student_Professor_Lecture " +
                    "INNER JOIN Lecture ON Student_Professor_Lecture.lecture_id=Lecture.lecture_id " +
                    "INNER JOIN Student ON Student_Professor_Lecture.student_id=Student.user_id " +
                    "WHERE Student_Professor_Lecture.professor_id=? AND Student_Professor_Lecture.lecture_id=?";
            ResultSet resultSet=null;
            List<Student> studentList=new ArrayList<>();
        try {
            PreparedStatement statement=connection.prepareStatement(sql);
            statement.setInt(1,professor_id);
            statement.setInt(2,lecture_id);
            resultSet=statement.executeQuery();
            while(resultSet.next()){
                Student student=new Student();
                student.setUser_id(resultSet.getInt("user_id"));
                student.setUser_fullName(resultSet.getString("user_fullName"));
                student.setUser_college(resultSet.getString("user_college"));
                studentList.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentList;
    }

    public List<Practise> getPractisesThatSolved(int student_id,int professor_id, int lecture_id, Connection connection){
            ResultSet resultSet=null;
            List<Practise> practiseList=new ArrayList<>();
            String sql="SELECT Practise.practise_id,Practise.practise_title,Practise.Programming_languages,File.file_data,Lecture.lecture_name " +
                    "FROM Student_Solution_Practise_Lecture_Professor INNER JOIN Practise " +
                    "ON Student_Solution_Practise_Lecture_Professor.practise_id=Practise.practise_id " +
                    "INNER JOIN File ON Practise.file_id=File.file_id " +
                    "INNER JOIN Lecture ON Student_Solution_Practise_Lecture_Professor.lecture_id=Lecture.lecture_id" +
                    "WHERE student_id=? AND professor_id=? AND lecture_id=?";

        try {
            PreparedStatement statement=connection.prepareStatement(sql);
            statement.setInt(1,student_id);
            statement.setInt(2,professor_id);
            statement.setInt(3,lecture_id);
            resultSet=statement.executeQuery();
            while (resultSet.next()){
                int practise_id=resultSet.getInt("practise_id");
                String practise_title=resultSet.getString("practise_title");
                String programming_languages=resultSet.getString("programming_languages");
                Blob file_data=resultSet.getBlob("file_data");
                String lecture_name=resultSet.getString("lecture_name");
                File practise_file=new File(null,file_data,null);
                String practise_description=null;
                try {
                    practise_description=practise_file.convertInputStreamToString();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Practise practise=new Practise(practise_id,practise_title,programming_languages,practise_description,lecture_name);
                practiseList.add(practise);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return practiseList;
    }

    public int insertIntoPractiseTable(int file_id,String practise_title,String programming_languages,Connection connection){
            String sql="INSERT INTO Practise(file_id,practise_title,programming_languages) VALUES(?,?,?)";
            int practise_id=0;
            ResultSet resultSet=null;
        PreparedStatement statement=null;
        try {
            statement=connection.prepareStatement(sql,statement.RETURN_GENERATED_KEYS);
            statement.setInt(1,file_id);
            statement.setString(2,practise_title);
            statement.setString(3,programming_languages);
            statement.executeUpdate();
            resultSet=statement.getGeneratedKeys();
            resultSet.next();
            practise_id=resultSet.getInt(1);

            System.out.println("Id Of The Last Practise Added To Practise Table is "+practise_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return practise_id;
    }

    public void insertIntoProfessorPractiseLectureTable(int professor_id,int practise_id,int lecture_id,Connection connection){
            String sql="INSERT INTO Professor_Practise_Lecture(professor_id,practise_id,lecture_id) VALUES(?,?,?)";
        try {
            PreparedStatement statement=connection.prepareStatement(sql);
            statement.setInt(1,professor_id);
            statement.setInt(2,practise_id);
            statement.setInt(3,lecture_id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
