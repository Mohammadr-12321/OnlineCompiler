package code.Controller;

import code.Compiler.Compiler;
import code.beans.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name="CompilerServlet",urlPatterns = "/CompilerServlet")
public class CompilerServlet extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String sourceCode=request.getParameter("sourceCode");
        System.out.println("after decoding source code ");
        String sourceCodeName=request.getParameter("sourceCodeName");
        String programmingLanguage=request.getParameter("programmingLanguage");
        System.out.println("source code programming language is : "+programmingLanguage);
        PrintWriter out=response.getWriter();


        System.out.println("Your Source Code");
        System.out.println(sourceCode);
        System.out.println("with name : "+sourceCodeName);
        User user= (User) request.getSession().getAttribute("user");
        System.out.println("user name "+user.getUser_name()+" is in the Compiler Servlet .. ");

        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("text/plain");

        Compiler compiler=new Compiler("/home/ubuntu/sourceCodes");
        out.println(compiler.compileSourceCode(programmingLanguage,sourceCodeName,sourceCode)+"\n is compiled ");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
