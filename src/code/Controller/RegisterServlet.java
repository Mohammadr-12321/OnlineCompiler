package code.Controller;




import code.Model.DAO;
import code.beans.File;
import code.beans.Professor;
import code.beans.Student;
import code.beans.User;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet(name="RegisterServlet",urlPatterns = "/RegisterServlet")
@MultipartConfig(fileSizeThreshold = 1024*1024*2,maxFileSize = 1024*1024*10,maxRequestSize = 1024*1024*50)
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Gather form data from http request
        String user_name=request.getParameter("user_name");
        String user_fullName=request.getParameter("user_fullName");
        String user_gender=request.getParameter("user_gender");
        String user_college=request.getParameter("user_college");
        String user_email=request.getParameter("user_email");
        String user_linkedin=request.getParameter("user_linkedin");
        String user_github=request.getParameter("user_github");
        String user_contact=request.getParameter("user_contact");
        String user_password=request.getParameter("user_password");
        String isProfessor=(request.getParameterValues("isProfessor")!=null)?"yes":"no";
        Part part=request.getPart("file_data");



        String file_name=extractFileName(part);
        String file_type="User_Profile_Image";



        // We Store two type of File in the data base
        // 1. User's Java Source codes that User compile with Server Java Compiler After registration/login process
        // 2. User Profile image

        // retrive connection reference variable from servlet context
        Connection connection= (Connection) getServletContext().getAttribute("dbconnection");
        // Use Data Access Object Model
        DAO dao=new DAO();

        if(file_name!=null && file_name.length()>0){

            InputStream file_data=part.getInputStream();


            int id=dao.RegisterUser(user_name,user_password,user_email,user_fullName,user_college,user_gender,user_linkedin,user_github,user_contact,isProfessor,connection);
            if(id==0){
                request.getRequestDispatcher("/register.html").forward(request,response);
            }
            else{
               // InputStream final_file_data=resizeImage(Image_Size,file_data);
                System.out.println("User ADDED TO DATA BASE ");
                int file_id=dao.insertTODB(file_name,file_data,file_type,connection);
                dao.pfORsf(id,file_id,isProfessor,connection);
                if(isProfessor.equalsIgnoreCase("yes")){
                    setLecturesOfProfessor(request,dao,id,connection);
                    setDegreeOfProfessor(request,dao,id,connection);
                }
                request.getRequestDispatcher("/login.jsp").forward(request,response);
            }
        }

    }

    private void setLecturesOfProfessor(HttpServletRequest request,DAO dao,int professor_id,Connection connection){
        String advanced_programming[]=request.getParameterValues("AdvancedProgramming");;
        String fundamental_programming[]=request.getParameterValues("FundamentalProgramming");
        String data_structure[]=request.getParameterValues("DataStructure");
        String design_algorithm[]=request.getParameterValues("DesignAlgorithm");;

        if(advanced_programming!=null){
            // lecture id of advanced programming in Lecture Table is 2
            System.out.println("professor present the "+advanced_programming[0]);
            dao.insertIntoProfessorLecture(professor_id,2,connection);
        }
        if(fundamental_programming!=null){
            System.out.println("professor present the "+fundamental_programming[0]);
            // lecture id of fundamental programming in Lecture Table is 1
            dao.insertIntoProfessorLecture(professor_id,1,connection);
        }
        if(data_structure!=null){
            System.out.println(" professor present the "+data_structure[0]);
            // lecture id of data structure in Lecture Table is 4
            dao.insertIntoProfessorLecture(professor_id,4,connection);
        }
        if(design_algorithm!=null){
            System.out.println("professor present the "+design_algorithm[0]);
            // lecture id of design algorithm is 3
            dao.insertIntoProfessorLecture(professor_id,3,connection);
        }

    }

    private void setDegreeOfProfessor(HttpServletRequest request,DAO dao,int professor_id,Connection connection){
        String bachelor_field_study=request.getParameter("Bachelor");
        String master_field_study=request.getParameter("Master");
        String phd_field_study=request.getParameter("Phd");
        int field_study_id=0;
        if(bachelor_field_study!=null){
            if(bachelor_field_study.equalsIgnoreCase("Software Engineering")){
                field_study_id=1;
            }
            else if(bachelor_field_study.equalsIgnoreCase("Hardware Engineering")){
                field_study_id=2;
            }
            else if(bachelor_field_study.equalsIgnoreCase("Computer Science")){
                field_study_id=3;
            }
                System.out.println("professor have bachelor degree in "+bachelor_field_study+" field study ");
                dao.setDegreeFieldStudyOfProfessor(professor_id,1,field_study_id,connection);
        }
        if(master_field_study!=null){
            if(master_field_study.equalsIgnoreCase("Software Engineering")){
                field_study_id=1;
            }
            else if(master_field_study.equalsIgnoreCase("Hardware Engineering")){
                field_study_id=2;
            }
            else if(master_field_study.equalsIgnoreCase("Computer Science")){
                field_study_id=3;
            }
            System.out.println("Professor have master degree in "+master_field_study+" field study ");
            dao.setDegreeFieldStudyOfProfessor(professor_id,2,field_study_id,connection);
        }
        if(phd_field_study!=null){
            if(phd_field_study.equalsIgnoreCase("Software Engineering")){
                field_study_id=1;
            }
            else if(phd_field_study.equalsIgnoreCase("Hardware Engineering")){
                field_study_id=2;
            }
            else if(phd_field_study.equalsIgnoreCase("Computer Science")){
                field_study_id=3;
            }
            System.out.println("Professor have phd degree in "+phd_field_study+" field study ");
            dao.setDegreeFieldStudyOfProfessor(professor_id,3,field_study_id,connection);
        }
    }

    private String extractFileName(Part part){

        // form-data; name="file"; filename="/home/ubuntu/Downloads/images/ile1.zip"
        // form-data; name="file"; filename="/home/ubuntu/Downloads/images/ile2.zip"
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                // /home/ubuntu/Downloads/images/file1.zip
                // /home/ubuntu/Downloads/images/file2.zip
                String clientFileName = s.substring(s.indexOf("=") + 2, s.length() - 1);

                // following code for windows os directory
                //  clientFileName = clientFileName.replace("\\", "/");
                int i = clientFileName.lastIndexOf('/');
                // file1.zip
                // file2.zip
                return clientFileName.substring(i + 1);
            }
        }
        return null;
    }

    public InputStream resizeImage(String ImageSize,InputStream originalImage){
        switch (ImageSize){
            case "ICON 200px"     : return File.resizeImage(originalImage,200,200);
            case "ICON 80px"      : return File.resizeImage(originalImage,80,80);
            case "FAVICON 24px"   : return File.resizeImage(originalImage,24,24);
            case "FAVICON 36px"   : return File.resizeImage(originalImage,36,36);
            case "FAVICON 48px"   : return File.resizeImage(originalImage,48,48);
            case "MOBILE 600px"   : return File.resizeImage(originalImage,600,600);
            case "THUMBNEIL 150px": return File.resizeImage(originalImage,150,150);
            default               :return originalImage;
        }
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
