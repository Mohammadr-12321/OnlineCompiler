package code.Controller;

import code.Model.DAO;
import code.beans.File;
import code.beans.Professor;
import code.beans.Student;
import code.beans.User;



import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet(name = "userFilesServlet",urlPatterns = "/userFilesServlet")
public class userFilesServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("user in userFilesServlet in doPost method");

        HttpSession session=request.getSession();
        User user=null;
        String isProfessor= (String) session.getAttribute("isProfessor");
        if(isProfessor.equalsIgnoreCase("yes"))
            user=(Professor)request.getSession().getAttribute("user");
        else
            user=(Student)request.getSession().getAttribute("user");

        Connection connection= (Connection) getServletContext().getAttribute("dbconnection");
        DAO dao=new DAO();
        List<File> files=dao.getFilsFromDB(user.getUser_id(),isProfessor,connection);
        System.out.println(files.size());
        session.setAttribute("files",files);
        request.getRequestDispatcher("files.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("user in userFilesServlet in doGet method ");
    }
}
