package code.Controller;


import code.Model.DAO;
import code.beans.Professor;
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
import java.util.Enumeration;
import java.util.List;

@WebServlet(name = "selectYourLectureServlet",urlPatterns = "/selectYourLectureServlet")
public class selectYourLectureServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setHeader("Access-Control-Allow-Origin", "*");
        System.out.println("In Select Your Lecture Servlet ... ");
        String selectedLecture=request.getParameter("p");
        HttpSession session=request.getSession();
        System.out.println("you select "+selectedLecture+" lecture");
        session.setAttribute("selectedLecture",selectedLecture);
        Connection connection= (Connection) getServletContext().getAttribute("dbconnection");
        DAO dao=new DAO();
        List<Professor> professor_list=dao.getProfessorsThatPresentThisLecture(selectedLecture,connection);
        for(Professor professor:professor_list)
            System.out.println("professor  : "+professor.getUser_fullName()+" have the user id :"+professor.getUser_id());
        Gson gson=new Gson();
        PrintWriter out=response.getWriter();
        out.write(gson.toJson(professor_list));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
