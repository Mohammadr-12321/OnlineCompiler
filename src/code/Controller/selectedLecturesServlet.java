package code.Controller;

import code.Model.DAO;
import code.beans.Professor;
import code.beans.User;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

@WebServlet(name = "selectedLecturesServlet",urlPatterns = "/selectedLecturesServlet")
public class selectedLecturesServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user= (User) request.getSession().getAttribute("user");
        Connection connection= (Connection) getServletContext().getAttribute("dbconnection");
        DAO dao=new DAO();
        List<Professor> professorList=dao.selectedLectures(user.getUser_id(),connection);
        Gson gson=new Gson();

        PrintWriter out=response.getWriter();
        out.write(gson.toJson(professorList));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
