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

// This Servlet Writed to Gather And Retrieve The Practises That Designed By Professor And Student Want to Solve Theses Practises

@WebServlet(name = "practisesOfLectureServlet",urlPatterns = "/practisesOfLectureServlet")
public class practisesOfLectureServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        User user= (User) session.getAttribute("user");
        int student_id=user.getUser_id();
        int professor_id= (int) session.getAttribute("professor_id");
        int lecture_id= (int) session.getAttribute("lecture_id");

        Connection connection= (Connection) getServletContext().getAttribute("dbconnection");
        DAO dao=new DAO();
        List<Practise> practiseList=dao.getPractises(student_id,professor_id,lecture_id,connection);
        Gson gson=new Gson();
        PrintWriter out=response.getWriter();
        out.write(gson.toJson(practiseList));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
