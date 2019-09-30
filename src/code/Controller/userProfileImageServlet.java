package code.Controller;

import code.Model.DAO;
import code.beans.File;
import code.beans.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.annotation.XmlType;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;

import static org.apache.catalina.connector.InputBuffer.DEFAULT_BUFFER_SIZE;

@WebServlet(name = "userProfileImageServlet",urlPatterns = "/userProfileImageServlet")
public class userProfileImageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.print(" in user profile image servlet \n");

        User user= (User) request.getSession().getAttribute("user");
        String isProfessor= (String) request.getSession().getAttribute("isProfessor");

        DAO dao=new DAO();
        Connection connection= (Connection) getServletContext().getAttribute("dbconnection");
        File file=dao.retrieveUserProfileImage(user.getUser_id(),"User_Profile_Image",isProfessor,connection);

        String contentType=getServletContext().getMimeType(file.getFile_name());
        response.setHeader("Content-Type",contentType);
        response.setHeader("Content-Disposition", "inline; filename=\"" + file.getFile_name() + "\"");
        System.out.println("file is found : "+(file!=null));
        InputStream user_profile_image_stream=null;
        BufferedInputStream input=null;
        BufferedOutputStream output=null;

        try {
            response.setHeader("Content-Length",String.valueOf(file.getFile_data().length()));
            System.out.println("image content length : "+file.getFile_data().length());
            user_profile_image_stream=file.getFile_data().getBinaryStream();
            // open stream
            input=new BufferedInputStream(user_profile_image_stream,DEFAULT_BUFFER_SIZE);
            output=new BufferedOutputStream(response.getOutputStream(),DEFAULT_BUFFER_SIZE);


            byte[] buffer=new byte[DEFAULT_BUFFER_SIZE];

            // Write file contents to response object

            int length=0;
            while((length=input.read(buffer))>0){
                output.write(buffer,0,length);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            output.close();
            input.close();
            user_profile_image_stream.close();
            response.getOutputStream().close();
        }
    }
}
