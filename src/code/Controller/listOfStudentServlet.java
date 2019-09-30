package code.Controller;

import code.Model.DAO;
import code.beans.Student;
import code.beans.User;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

@WebServlet(name = "listOfStudentServlet",urlPatterns = "/listOfStudentServlet")
public class listOfStudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        User user= (User) session.getAttribute("user");
        String lecture_name= (String) session.getAttribute("lecture_name");
        System.out.println("we want to display list of students of "+lecture_name+" lecture ");
        int lecture_id=(int)request.getSession().getAttribute("lecture_id");
        Connection connection= (Connection) getServletContext().getAttribute("dbconnection");
        DAO dao=new DAO();
        List<Student> studentList=dao.getListOfStudentOfThisLecture(user.getUser_id(),lecture_id,connection);
        Gson gson=new Gson();
        PrintWriter out=response.getWriter();
        out.write(gson.toJson(studentList));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
