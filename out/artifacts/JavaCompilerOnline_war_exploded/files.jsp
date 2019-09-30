<%@ page import="code.beans.File" %>
<%@ page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>

<head>
  <title>files</title>
  <meta name="description" content="website description" />
  <meta name="keywords" content="website keywords, website keywords" />
  <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
  <link rel="stylesheet" type="text/css" href="style/style.css" />
  <link rel="stylesheet" type="text/css" href="style/filesPageStyle.css"/>
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
          <li><a href="login.html">Login</a></li>
          <li><a href="register.html">Register</a></li>
          <li><a href="contact.html">Contact Us</a></li>
        </ul>
      </div>
    </div>

<div id="main">
<h1>FILES</h1></div>
<div id="fors">
    
     <table>
        <% List<File> files=(List<File>) request.getSession().getAttribute("files");
            for(int i=0;i<files.size();i++){
        %>
        <ol>
            <li id="m"> <%=files.get(i).getFile_name()%></li>
        </ol>

         <%
             }
         %>
     </table>
</div>

    <div id="content_footer"></div>
    <div id="footer">
      Copyright &copy; 2017 
    </div>
  </div>
</body>
</html>
