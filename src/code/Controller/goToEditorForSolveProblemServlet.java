package code.Controller;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "goToEditorForSolveProblemServlet",urlPatterns = "/goToEditorForSolveProblemServlet")
public class goToEditorForSolveProblemServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("set practise_id in the session of student and forward request to code editor html page");
        HttpSession session=request.getSession();
        int practise_id=Integer.parseInt(request.getParameter("practise_id"));
        session.setAttribute("practise_id",practise_id);

        response.addHeader("Location","/code_editor.jsp");
        PrintWriter out=response.getWriter();
        out.write("success");
        request.getRequestDispatcher("/code_editor.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
