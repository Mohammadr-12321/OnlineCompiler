
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="code.beans.User" %>
<%@ page import="code.beans.Professor" %>
<%@ page import="code.beans.Student" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>

<head>
  <title>profile</title>
  <meta name="description" content="website description" />
  <meta name="keywords" content="website keywords, website keywords" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <link rel="stylesheet" type="text/css" href="style/style.css" />
    <link rel="stylesheet" type="text/css" href="style/profilePageStyle.css" />

    <!-- i remove the style code of this html document  -->
</head>

<body>
  <div class="main">
    <div id="header">
      <div id="logo">
        <div id="logo_text">
          <!-- class="logo_colour", allows you to change the colour of the text -->
          <h1><a href="simple">CODE<span class="logo_colour">CAMP</span><img src="logo.png" width="50" height="50"></a></h1>
          <h2>Teaching the world how to code.</h2>
        </div>
      </div>
      <div id="menubar">
        <ul id="menu">
          <!-- put class="selected" in the li tag for the selected page - to highlight which page you're on -->
          <li class="selected"><a href="index.jsp">Home</a></li>
          <li><a href="code_editor.jsp">Practise</a></li>
          <li><a href="login.jsp">Login</a></li>
          <li><a href="register.html">Register</a></li>
          <li><a href="contact.html">Contact Us</a></li>
        </ul>
      </div>
    </div>
	
	
	



<div id="main">
<h1>USER PROFILE</h1></div>
<div id="fors">
    
     <table>
        
         <%
            if(session.getAttribute("user")!=null){
                User user=null;
                List<String>field_study=null;
               String isProfessor= (String) request.getSession().getAttribute("isProfessor");
               if(isProfessor.equalsIgnoreCase("yes")){
                   user= (Professor) request.getSession().getAttribute("user");
                   field_study=((Professor) user).getDegree();
               }
               else{
                   user= (Student) request.getSession().getAttribute("user");
               }
         %>
                
                <tr>
                    <td class="m">Profile Picture:</td><td><img src="/userProfileImageServlet" height="100" width="100"></td>
                </tr>

                 <tr>
                     <td class="m">Name:</td><td class="m"><%=user.getUser_name()%></td>
                 </tr>

                 <tr>
                     <td class="m">Email:</td><td class="m"><%=user.getUser_email()%></td>
                 </tr>

                 <tr>
                     <td class="m">College:</td><td class="m"><%=user.getUser_college()%></td>
                 </tr>

                 <tr>
                     <td class="m">Gender:</td><td class="m"><%=user.getUser_gender()%></td>
                 </tr>

                 <tr>
                     <td class="m">LinkedIn Id:</td><td class="m"><%=user.getUser_linkedin()%></td>
                 </tr>

                  <tr>
                      <td class="m">GitHub Id</td><td class="m"><%=user.getUser_github()%></td>
                  </tr>

                  <tr>
                      <td class="m">Contact:</td><td class="m"><%=user.getUser_contact()%></td>
                  </tr>

                  <%
                      if(field_study!=null)
                          for(String  field:field_study){
                  %>
                  <tr>
                      <td class="m">Degree : </td><td class="m"><%=field%> </td>
                  </tr>
                  <%
                      }
                  %>

 </table>

                   

</div>
       <% }
        else{
            out.print("Please login first!");
            response.sendRedirect("login.jsp");
            request.getRequestDispatcher("login.jsp").include(request, response);
        }
           %>
                    
                
               
	
	
	
	
	
	
	
    
    <div id="content_footer"></div>
    <div id="footer">
      Copyright &copy; 2017 
    </div>
  </div>
</body>
</html>
