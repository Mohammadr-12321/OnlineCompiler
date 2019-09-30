package code.Controller;

import code.Model.DAO;
import code.beans.File;
import code.beans.User;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="retrieveServlet",urlPatterns = "/retrieveServlet")
public class retrieveServlet extends HttpServlet {


     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

            response.setContentType("application/json");

        PrintWriter out=response.getWriter();
        try{
            String sourceCodeName=request.getParameter("sourceCodeName");
            System.out.print(sourceCodeName);

            User user= (User) request.getSession().getAttribute("user");
            System.out.println("\nuser with user name "+user.getUser_name()+" is in the doPost method of retrieveServlet");
            Connection connection= (Connection) getServletContext().getAttribute("dbconnection");
            DAO dao=new DAO();
            File file=dao.retrieveFileFromDataBase(user.getUser_id(),sourceCodeName,connection);
            boolean isNull=(file==null);
            System.out.println("file is null ? "+isNull);
            String programmingLanguage=file.getFile_type();
            System.out.println("source code programming language is : "+programmingLanguage);
            String sourceCode=file.convertInputStreamToString();
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("programmingLanguage",programmingLanguage);
            jsonObject.put("sourceCode",sourceCode);
            out.write(jsonObject.toString());
        }
        catch(Exception e){
            e.printStackTrace();
        }
        response.setHeader("Access-Control-Allow-Origin", "*");
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
