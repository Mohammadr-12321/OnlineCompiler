package code.Controller;

import code.Model.DAO;
import code.beans.Practise;
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

@WebServlet(name = "getPractisesThatSolvedByStudentServlet",urlPatterns = "/getPractisesThatSolvedByStudentServlet")
public class getPractisesThatSolvedByStudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        int student_id= Integer.parseInt(request.getParameter("student_id"));
        User user= (User) session.getAttribute("user");
        int lecture_id=(int)session.getAttribute("lecture_id");
        DAO dao=new DAO();
        Connection connection= (Connection) getServletContext().getAttribute("dbconnection");
        List<Practise> practiseList=dao.getPractisesThatSolved(student_id,user.getUser_id(),lecture_id,connection);

        Gson gson=new Gson();
        PrintWriter out=response.getWriter();
        out.write(gson.toJson(practiseList));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
