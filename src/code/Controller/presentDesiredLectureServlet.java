package code.Controller;

import code.Model.DAO;
import code.beans.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

@WebServlet(name = "presentDesiredLectureServlet",urlPatterns = "/presentDesiredLectureServlet")
public class presentDesiredLectureServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        User user= (User) session.getAttribute("user");
        Connection connection= (Connection) getServletContext().getAttribute("dbconnection");
        DAO dao=new DAO();
        String lecture_name=request.getParameter("lecture");
        int lecture_id=0;
        if(lecture_name.equalsIgnoreCase("FundamentalProgramming"))
            lecture_id=1;
        else if(lecture_name.equalsIgnoreCase("AdvancedProgramming"))
            lecture_id=2;
        else if(lecture_name.equalsIgnoreCase("DesignAlgorithm"))
            lecture_id=3;
        else if(lecture_name.equalsIgnoreCase("DataStructure"))
            lecture_id=4;

        String result=dao.professorPresentDesiredLecture(user.getUser_id(),lecture_id,connection);
        PrintWriter out=response.getWriter();
        out.write(result);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
