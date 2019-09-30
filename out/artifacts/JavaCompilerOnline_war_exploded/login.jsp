<%-- 
    Document   : login
    Created on : 4 Apr, 2017, 6:30:12 PM
    Author     : Arpita
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE HTML>
<html>

<head>
  <title>login</title>
  <meta name="description" content="website description" />
  <meta name="keywords" content="website keywords, website keywords" />
  <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
  <link rel="stylesheet" type="text/css" href="style/style.css" />
  <style>


#m
{
	font-size:25px;
	margin-top:20px;
}

#main h1{
	font-size:40px;
	margin-left:400px;
}
#fors{margin-left:500px;
	height:600px;
	width:500px;
	//background-color:green;
}
</style>
</head>

<body>
  <div id="main">
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
          <li><a href="index.jsp">Home</a></li>
          <li><a href="code_editor.jsp">Practise</a></li>
          <li class="selected"><a href="login.jsp">Login</a></li>
          <li><a href="register.html">Register</a></li>
          <li><a href="contact.html">Contact Us</a></li>
        </ul>
      </div>
    </div>
	
	
	



<div class="main">
<h1>Login here</h1></div>
<div id="fors">
    <form action="LoginServlet" method="post">
<table>
<tr>
  <td class="m">UserName:</td><td><input type="text" name="user_name" placeholder="enter your username" required id="m"/></td></tr>

<td class="m">Email id:</td><td><input type="email" name="user_email" required id="m"/></td></tr>
<tr><td class="m">Password:</td><td><input type="password" name="user_password" required id="m"/></td></tr>

<tr><td><input type="submit" value="Login" class="m"/></td></tr>
</table>
      <p class="message" > Not Registered ? <a href="register.html"> Register</a></p>
      <table>
        <tr>
          <td> Are You Professor ? </td>
          <td><input type="checkbox" id="professor" name="isProfessor" value="yes" /></td>
        </tr>
      </table>
    </form>
	
	
	</div>
	
	
	
	
    
    <div id="content_footer"></div>
    <div id="footer">
      Copyright &copy;  
    </div>
  </div>
</body>
</html>

