package code.Controller;

import code.Model.DAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "contactUsServlet",urlPatterns = "/contactUsServlet")
public class contactUsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name=request.getParameter("name");
        String email=request.getParameter("email");
        String message=request.getParameter("message");

        Connection connection= (Connection) getServletContext().getAttribute("dbconnection");
        DAO dao=new DAO();
        int id=dao.insertToContactUSTable(name,email,message,connection);
        if(id==0){
            System.out.println("Insert Into Data Base is Failed ... ");
            request.getRequestDispatcher("contact.html").forward(request,response);
        }
        else{
            System.out.println("Insert Into Data Base is Successful .... ");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
