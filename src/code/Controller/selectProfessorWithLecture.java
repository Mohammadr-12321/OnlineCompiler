package code.Controller;

import code.Model.DAO;
import code.beans.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

@WebServlet(name="selectProfessorWithLecture",urlPatterns ="/selectProfessorWithLecture")
public class selectProfessorWithLecture extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user= (User) request.getSession().getAttribute("user");
        int professor_id=Integer.parseInt(request.getParameter("selectedProfessor"));
        String lecture=request.getParameter("lecture");
        int student_id=user.getUser_id();


        int lecture_id=0;
        if(lecture.equalsIgnoreCase("FundamentalProgramming"))
            lecture_id=1;
        else if(lecture.equalsIgnoreCase("AdvancedProgramming"))
            lecture_id=2;
        else if(lecture.equalsIgnoreCase("DesignAlgorithm"))
            lecture_id=3;
        else if(lecture.equalsIgnoreCase("DataStructure"))
            lecture_id=4;

        System.out.println("in select professor with lecture servlet ... ");
        System.out.println("student with id : "+student_id+" select lecture with id : "+lecture_id+" and  professor with id : "+professor_id);
        DAO dao=new DAO();
        Connection connection= (Connection) getServletContext().getAttribute("dbconnection");
        int result=dao.selectDesiredLectureWithDesiredProfessor(student_id,professor_id,lecture_id,connection);
        PrintWriter out=response.getWriter();
        if(result!=0){
            out.println("selection lecture was successfully");
        }
        else
            out.println("selection lecture failed ... ");
    }
}
