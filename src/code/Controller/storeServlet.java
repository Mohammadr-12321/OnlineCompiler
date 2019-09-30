package code.Controller;

import code.Model.DAO;
import code.beans.File;
import code.beans.User;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="storeServlet",urlPatterns = "/storeServlet")
public class storeServlet extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out=response.getWriter();

        String sourceCodeName=request.getParameter("sourceCodeName");
        String sourceCode=request.getParameter("sourceCode");
        String programmingLanguage=request.getParameter("programmingLanguage");


        InputStream file_data= File.convertStringToInputStream(sourceCode);
        programmingLanguage=programmingLanguage.toUpperCase();
        programmingLanguage+="_Source_Code";



        User user= (User) request.getSession().getAttribute("user");

        Connection connection= (Connection) getServletContext().getAttribute("dbconnection");

        DAO dao=new DAO();

        int file_id=dao.insertTODB(sourceCodeName,file_data,programmingLanguage,connection);

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("text/html;charset=UTF-8");
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
