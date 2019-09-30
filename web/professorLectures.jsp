
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
                <li><a href="code_editor.html">Practise</a></li>
                <li><a href="login.html">Login</a></li>
                <li><a href="register.html">Register</a></li>
                <li><a href="contact.html">Contact Us</a></li>
            </ul>
        </div>
    </div>

    <script>
        var xmlHttpRequest;

        // function for create xml http request
        function createXmlHttpRequest(){
            if(window.ActiveXObject){
                xmlHttpRequest=new ActiveXObject("Microsoft.XMLHTTP");
            }
            else if(window.XMLHttpRequest){
                xmlHttpRequest=new XMLHttpRequest();
            }
        }

        function presentedLectures() {
            var url="presentedLecturesServlet";
            var parameter=null;
            createXmlHttpRequest();
            try{
                xmlHttpRequest.open("POST",url,true);
                xmlHttpRequest.onreadystatechange=getPresentedLecturesFromServer;
                xmlHttpRequest.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
                xmlHttpRequest.overrideMimeType("text/plain;");
                xmlHttpRequest.send(parameter);
            }
            catch (e) {
                alert(e.message);
            }
        }

        function getPresentedLecturesFromServer() {
            if(xmlHttpRequest.readyState==4 && xmlHttpRequest.status==200){
                var jsonResponse=JSON.parse(xmlHttpRequest.responseText);
                displayPresentedLectures(jsonResponse);
            }
        }

        function displayPresentedLectures(lectures) {
            var out="";
            var i;
            out+="<h3> Presented Lectures </h3>";
            out+="<table>"
            for(i=0;i<lectures.length;i++){
                var lecture_name=lectures[i];
                out+='<tr>';
                out+='<td class="m"><input type="button" '+'value="'+lecture_name+'"  onclick="Lecture(\''+lecture_name+'\')"/></td>';
                out+='</tr>';
            }
            out+="</table>";
            document.getElementById("presented_lectures").style.display='block';
            document.getElementById("presented_lectures").innerHTML=out;
        }

        function Lecture(lecture_name) {
            var url="choosedLectureServlet";
            var parameter="lecture="+lecture_name;
            createXmlHttpRequest();
            try{
                xmlHttpRequest.open("POST",url,true);
                xmlHttpRequest.onreadystatechange=getInfoFromServerSide();
                xmlHttpRequest.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
                xmlHttpRequest.overrideMimeType("text/plain;");
                xmlHttpRequest.send(parameter);
            }
            catch (e) {
                alert(e.message);
            }
        }

        function hidePresentedLectures() {
            document.getElementById("presented_lectures").style.display='none';
        }

        function selectYourLecture() {
            var url="presentDesiredLectureServlet";
            var lecture_name=document.getElementById("lectureThatWillSelect").value;
            var parameter="lecture="+lecture_name;
            createXmlHttpRequest();
            try{
                xmlHttpRequest.open("POST",url,true);
                xmlHttpRequest.onreadystatechange=getInfoFromServerSide;
                xmlHttpRequest.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
                xmlHttpRequest.overrideMimeType("text/plain;");
                xmlHttpRequest.send(parameter);
            }
            catch (e) {
                alert(e.message);
            }
        }

        function getInfoFromServerSide() {
            if(xmlHttpRequest.readyState==4 && xmlHttpRequest.status==200){
                var xmlHttpResponse=xmlHttpRequest.responseText;
                alert(xmlHttpResponse);
            }
        }
    </script>




    <div id="main">
        <h1>Professor Lectures</h1>
        <!--a id="lin" href="LogoutSessionServlet">Logout</a-->
    </div>
    <div id="fors">
        <form>
            <table>
                <tr>
                    <td class="m"><input type="button" value="Presented Lectures " onclick="presentedLectures()"></td>
                </tr>
                <tr>
                    <td class="m">
                        <select  id="lectureThatWillSelect" name="lecture" required="required">
                            <option>DataStructure</option>
                            <option>DesignAlgorithm</option>
                            <option>AdvancedProgramming</option>
                            <option>FundamentalProgramming</option>
                        </select>
                    </td>
                    <td class="m">
                        <input type="button"  value="Select Your Lecture " onclick="selectYourLecture()">
                    </td>
                </tr>
            </table>
        </form>

        <div class="m" >
            <form method="post" action="professorLecture" id="presented_lectures">

            </form>
        </div>

    </div>
    <script>
        hidePresentedLectures();
    </script>

    <div id="content_footer"></div>
    <div id="footer">
        Copyright &copy;
    </div>
</div>
</body>
</html>

