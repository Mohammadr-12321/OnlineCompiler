package code.Controller;

import code.Model.DAO;
import code.beans.File;
import code.beans.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;

@WebServlet(name = "designProblemServlet",urlPatterns = "/designProblemServlet")
public class designProblemServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        User user= (User) session.getAttribute("user");
        Connection connection= (Connection) getServletContext().getAttribute("dbconnection");
        String practise_title=request.getParameter("practise_title");
        String programming_languages=request.getParameter("programming_languages");
        String practise_description=request.getParameter("practise_description");
        String lecture_name= (String) session.getAttribute("lecture_name");
        int lecture_id=(int)session.getAttribute("lecture_id");
        DAO dao=new DAO();

        // Add Practise Description To File Data Base and after that,  get file_id from result of DML Query
        String file_name="Practise_Designed_By_"+user.getUser_fullName()+".txt";
        InputStream file_data= File.convertStringToInputStream(practise_description);
        String file_type="Student_Practise_Text";
        int file_id=dao.insertTODB(file_name,file_data,file_type,connection);

        // Add To Practise Table and after that get practise id from result of DML Query
        int practise_id=dao.insertIntoPractiseTable(file_id,practise_title,programming_languages,connection);

        // Add to Professor Practise Lecture Table
        dao.insertIntoProfessorPractiseLectureTable(user.getUser_id(),practise_id,lecture_id,connection);

        System.out.println("In the designProblemServlet and practise with practise id : "+practise_id+" and practise title : "+practise_title+" That related to "+lecture_name+" and  Designed By Professor "+user.getUser_fullName()+" Is Inserted to Data Base ");
        PrintWriter out=response.getWriter();
        out.write("Successfull ");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
