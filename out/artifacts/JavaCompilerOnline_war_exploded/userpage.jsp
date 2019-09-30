
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
<head>
  <title>userpage</title>
  <meta name="description" content="website description" />
  <meta name="keywords" content="website keywords, website keywords" />
  <link rel="stylesheet" type="text/css" href="style/style.css" />
  <link rel="stylesheet" type="text/css" href="style/userPageStyle.css" />
  <style>

</style>
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
<h1>USER PAGE</h1>
<!--a id="lin" href="LogoutSessionServlet">Logout</a-->
</div>
      <div id="fors">

          <form method="post" name="form" accept-charset="UTF-8">
              <table>

                  <tr>
                      <td><input type="image" name="profile" src="image/profile-vector-customer-1.png" style="height: 200px; width: 200px;" onclick="javascript: form.action='profile.jsp';"/></td>
                      <td><input type="image" name="edit" src="image/Edit_user-512.png" style="height: 200px; width: 200px;" onclick="javascript: form.action='edit.html';"/></td>
                  </tr>
                  <tr>
                      <td><input type="image" name="code_editor" src="image/screen_computer_development_display_web-512.png" style="height: 180px; width: 180px;" onclick="javascript: form.action='code_editor.jsp'" /></td>
                      <td><input type="image" name="userFilesServlet" src="image/file.png" style="height: 200px; width: 200px;" onclick="javascript: form.action='/userFilesServlet'"/></td>
                  </tr>

                  <tr>
                      <td><input type="image" name="LogOut" src="image/018_320_door_exit_logout-512.png" style="height: 180px; width: 180px;" onclick="javascript: form.action='/logoutSessionServlet'" /></td>
                      <td><input type="image" name="student_lecture_professor" src="image/presentation-512.png" style="height: 200px; width: 200px;" onclick="javascript: form.action='studentProfessorServlet'"/></td>
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

