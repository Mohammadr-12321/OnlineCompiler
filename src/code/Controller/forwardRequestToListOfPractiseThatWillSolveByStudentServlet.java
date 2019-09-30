package code.Controller;


import code.beans.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "forwardRequestToListOfPractiseThatWillSolveByStudentServlet",urlPatterns = "/forwardRequestToListOfPractiseThatWillSolveByStudentServlet")
public class forwardRequestToListOfPractiseThatWillSolveByStudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        User user= (User)session.getAttribute("user");
        int professor_id=Integer.parseInt(request.getParameter("professor_id"));
        String lecture_name=request.getParameter("lecture_name");

        int lecture_id=0;
        if(lecture_name.equalsIgnoreCase("FundamentalProgramming"))
            lecture_id=1;
        else if(lecture_name.equalsIgnoreCase("AdvancedProgramming"))
            lecture_id=2;
        else if(lecture_name.equalsIgnoreCase("DesignAlgorithm"))
            lecture_id=3;
        else if(lecture_name.equalsIgnoreCase("DataStructure"))
            lecture_id=4;

        System.out.println("in practises of lecture servlet : \n student with student_id : "+user.getUser_id()+" have "+lecture_name+" with professor with professor_id : "+professor_id);

        session.setAttribute("lecture_id",lecture_id);
        session.setAttribute("professor_id",professor_id);

        response.setHeader("Location","listOfPractisesThatWillSolveByStudent.jsp");
        PrintWriter out=response.getWriter();
        out.write("success");
        request.getRequestDispatcher("listOfPractisesThatWillSolveByStudent.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
