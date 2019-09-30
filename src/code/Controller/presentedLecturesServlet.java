package code.Controller;

import code.Model.DAO;
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

@WebServlet(name = "presentedLecturesServlet",urlPatterns = "/presentedLecturesServlet")
public class presentedLecturesServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        User user= (User) session.getAttribute("user");
        Connection connection= (Connection) getServletContext().getAttribute("dbconnection");
        DAO dao=new DAO();
        List<String> presentedLectures=dao.getPresentedLectures(user.getUser_id(),connection);
        System.out.println("professor's user id is : "+user.getUser_id());
        System.out.println("in the presented lecture servlet \nAfter call presented Lecture in client side send xmlhttprequest to presented lecture servlet ");
        System.out.println("presented lecture list is null ?"+(presentedLectures==null));
        for(String s:presentedLectures)
            System.out.println(s);
        Gson gson=new Gson();
        PrintWriter out=response.getWriter();
        out.write(gson.toJson(presentedLectures));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
