package code.Controller;


import code.Model.DAO;
import code.beans.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name="LoginServlet",urlPatterns ="/LoginServlet")
public class LoginServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out=response.getWriter();
        try{
            String user_name=request.getParameter("user_name");
            String user_email=request.getParameter("user_email");
            String user_password=request.getParameter("user_password");
            String isProfessor=(request.getParameterValues("isProfessor")!=null)?"yes":"no";

            // *********************************************************
            HttpSession session=request.getSession();

            // **********************************************************
            DAO dao=new DAO();
            Connection connection=(Connection) getServletContext().getAttribute("dbconnection");
            // ************************************************************
            User user=dao.validateUser(user_name,user_email,user_password,isProfessor,connection);
            out.println(user);
        if(user!=null){
            System.out.println("User is not null");
            System.out.println("User with User Name : "+user.getUser_name()+"  is logged in to Compiler online ");
            session.setAttribute("user",user);
            session.setAttribute("isProfessor",isProfessor);
            RequestDispatcher rd=request.getRequestDispatcher("userpage.jsp");
            rd.forward(request, response);
        }
        else{
            System.out.println("User Is Null ");
            RequestDispatcher rd=request.getRequestDispatcher("login.jsp");
            rd.include(request, response);
            out.println("error");
        }
            
        }
        catch(Exception e){
            System.out.println(e);
        }
    }


    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
