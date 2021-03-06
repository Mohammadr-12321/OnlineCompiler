<%--
  Created by IntelliJ IDEA.
  User: ubuntu
  Date: 3/7/19
  Time: 3:42 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
  <title>Home Page</title>

  <meta name="description" content="website description" />
  <meta name="keywords" content="website keywords, website keywords" />
  <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
  <link rel="stylesheet" type="text/css" href="style/style.css" />
  <link rel="stylesheet" type="text/css" href="style/indexPageStyle.css"/>
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
        <li class="selected"><a href="index.jsp">Home</a></li>
        <li><a href="code_editor.jsp">Practise</a></li>
        <li><a href="login.jsp">Login</a></li>
        <li><a href="register.html">Register</a></li>
        <li><a href="contact.html">Contact Us</a></li>
      </ul>
    </div>
  </div>
  <div id="content_header"></div>
  <div id="site_content">
    <div class="sidebar">
      <!-- insert your sidebar items here -->
      <h3 style="color:purple "><strong>Announcements</strong></h3>
      <h4>Upcoming quizzes</h4>
      <p>Unveiling your star studded gift!
        Go check your profile and know more about the newly launched rating mechanism<br /><a href="#">here</a></p>
      <p></p>
      <h4>Recent Blog Posts</h4>
      <p><a href="#">CampDown 2017 is here and this time its truly open!! </a></p>
      <h3>RESOURCES</h3>
      <ul>
        <li><a href="#">API</a></li>
        <li><a href="#">Guides</a></li>
        <li><a href="#">Videos</a></li>
        <li><a href="#">Events</a></li>
        <li><a href="#">News</a></li>

      </ul>
      <h3>Search</h3>
      <form method="post" action="#" id="search_form">
        <p>
          <input class="search" type="text" name="search_field" value="Enter keywords....." />
          <input name="search" type="image" style="border: 0; margin: 0 0 -9px 5px;" src="image/search.png" alt="Search" title="Search" />
        </p>
      </form>
    </div>
    <div id="content">
      <!-- insert the page content here -->
      <h1 class="pink">Welcome to CODE CAMP</h1>
      <p class="green">A Platform for Aspiring Programmers, CodeCamp was created as a platform to help programmers make it big in the world of algorithms, computer programming and programming contests. At CodeCamp we work hard to revive the geek in you by hosting a programming contest at the start of the month and another smaller programming challenge in the middle of the month. We also aim to have training sessions and discussions related to algorithms, binary search, technicalities like array size and the likes. Apart from providing a platform for programming competitions, CodeCamp also has various algorithm tutorials and forum discussions to help those who are new to the world of computer programming.
      <h2>Browser Compatibility</h2>
      <p class="green">This template has been tested in the following browsers:</p>
      <ul class="green">
        <li>Internet Explorer 8</li>
        <li>Internet Explorer 7</li>
        <li>FireFox 3.5</li>
        <li>Google Chrome 6</li>
        <li>Safari 4</li>
      </ul>
      <p class="orange"><u>Practice Section</u> - A Place to hone your 'Computer Programming Skills'</u></p>
      <p class="green">Try your hand at one of our many practice problems and submit your solution in a language of your choice. Our programming contest judge accepts solutions in over 5+ programming languages. Preparing for coding contests were never this much fun! Receive points, and move up through the CodeCamp ranks. Use our practice section to better prepare yourself for the multiple programming challenges that take place through-out the month on CodeCamp</p>
      <p class="orange"><u>Compete - Monthly Programming Contests</u></p>
      <p class="green">Here is where you can show off your computer programming skills. Take part in our 10 day long monthly coding contest and the shorter format Cook-off coding contest. Put yourself up for recognition and win great prizes. Our programming contests have prizes worth up to INR 20,000 (for Indian Community), $700 (for Global Community) and lots more CodeChef goodies up for grabs.

    </div>
  </div>
  <div id="content_footer"></div>
  <div id="footer">
    Copyright &copy;
  </div>
</div>
</body>
</html>
