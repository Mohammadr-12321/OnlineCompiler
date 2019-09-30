package code.Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "choosedLectureServlet",urlPatterns = "/choosedLectureServlet")
public class choosedLectureServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

        HttpSession session=request.getSession();
        System.out.println(" Lecture Id Is : "+lecture_id+" Lecture Name is : "+lecture_name);
        session.setAttribute("lecture_name",lecture_name);
        session.setAttribute("lecture_id",lecture_id);
        request.getRequestDispatcher("studentsListAndPractiseList.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
