
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>

<head>
    <title>profile</title>
    <link rel="stylesheet" type="text/css" href="style/style.css" />
    <link rel="stylesheet" type="text/css" href="style/profilePageStyle.css">
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

        function getInfoFromServer(){
            if(xmlHttpRequest.readyState==4 && xmlHttpRequest.status==200){
                var xmlHttpResponse=xmlHttpRequest.responseText;
                var jsonResponse=JSON.parse(xmlHttpResponse);
                display(jsonResponse);
                console.log("after call select your lecture servlet ... ");
            }
        }

        function student_lecture(user_id){
            console.log("in student_lecture method and user_id = "+user_id);
            var url="selectProfessorWithLecture";
            var parameter="selectedProfessor="+user_id+"&lecture="+document.getElementById("lectureThatWillSelect").value;
            createXmlHttpRequest();
            try{
                xmlHttpRequest.open("POST",url,true);
                xmlHttpRequest.onreadystatechange=getResultFromServer;
                xmlHttpRequest.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
                xmlHttpRequest.overrideMimeType("text/plain;");
                xmlHttpRequest.send(parameter);
            }
            catch (e) {
                alert(e.message);
            }

        }

        function getResultFromServer() {
            if(xmlHttpRequest.readyState==4 && xmlHttpRequest.status==200){
                var xmlHttpResponse=xmlHttpRequest.responseText;
                alert(xmlHttpResponse);
            }
        }


        function display(array) {
            var out='<h3> Professors That Present '+document.getElementById("lectureThatWillSelect").value+'You Can Get Lecture with following Professors </h3>';
            out+='<form name="myForm" > ';
            out+='<table>'
            var i;

            for(i=0;i<array.length;i++){
                out+='<tr><td class="m">'
                out+=' <input type="button" id="'+i+'" value="get '+document.getElementById("lectureThatWillSelect").value+' Lecture With  '+array[i].user_fullName+
                    ' " onclick="student_lecture('+array[i].user_id+')" />';
                out+='</td></tr>';
            }
            out+='</table>';
            out+=" </form>";
            console.log(out);
            document.getElementById("newLectures").innerHTML=out;
        }

        function selectedLectures() {
            var url="selectedLecturesServlet";
            var parameter=null;
            createXmlHttpRequest();
            try{
                xmlHttpRequest.open("POST",url,true);
                xmlHttpRequest.onreadystatechange=getSelectedLecturesFromServer;
                xmlHttpRequest.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
                xmlHttpRequest.overrideMimeType("text/plain;");
                xmlHttpRequest.send(parameter);
            }
            catch (e) {
                alert(e.message);
            }
        }

        function getSelectedLecturesFromServer() {
            if(xmlHttpRequest.readyState==4 && xmlHttpRequest.status==200){
                var jsonResponse=JSON.parse(xmlHttpRequest.responseText);
                displaySelectedLectures(jsonResponse);
            }
        }

        function solvePractise(user_id,lecture) {
            var professor_id=user_id;
            var lecture_name=lecture;
            var url="forwardRequestToListOfPractiseThatWillSolveByStudentServlet";
            var parameters="professor_id="+professor_id+"&lecture_name="+lecture_name;
            createXmlHttpRequest();
            xmlHttpRequest.open("POST",url,true);
            xmlHttpRequest.onreadystatechange=handleResponse;
            xmlHttpRequest.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
            xmlHttpRequest.overrideMimeType("text/plain");
            xmlHttpRequest.send(parameters);
        }

        function handleResponse() {
            if(xmlHttpRequest.readyState==4 && xmlHttpRequest.status==200){
                window.location.href=xmlHttpRequest.getResponseHeader("Location");
            }
        }

        function displaySelectedLectures(array) {
            var out="";
            var i;
            out+="<h3> Selected Lectures </h3>";
            out+="<table>"
            for(i=0;i<array.length;i++){
                var professor_id=array[i].user_id;
                var full_name=array[i].user_fullName;
                var lecture=array[i].lectures[0];

                out+='<tr>';
                out+='<td class="m">'+full_name+'  '+lecture+'</td>';
                out+='<td class="m"><input type="button" '+'value="Solve Practises Of '+lecture+'"  onclick="solvePractise('+professor_id+',\''+lecture+'\''+')"/></td>';
                out+='</tr>';
            }
            out+="</table>";
            document.getElementById("selected_lectures").style.display='block';
            document.getElementById("selected_lectures").innerHTML=out;
        }

        function selectYourLecture(){
            var url="selectYourLectureServlet";
            var lecture=document.getElementById("lectureThatWillSelect").value;
            var parameter="p="+lecture;
            console.log(parameter);
            createXmlHttpRequest();
            try{
                xmlHttpRequest.open("POST",url,true);
                console.log("we call the select your lecture servlet ");
                xmlHttpRequest.onreadystatechange=getInfoFromServer;
                xmlHttpRequest.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
                xmlHttpRequest.overrideMimeType("text/plain;");
                xmlHttpRequest.send(parameter);
            }
            catch (e) {
                alert(e.message);
            }
        }

        function HideSelectedLectures() {
            document.getElementById("selected_lectures").style.display='none';
        }
    </script>




    <div id="main">
        <h1>Student Lectures</h1></div>
    <div id="fors">
        <form name="lectureANDpractise">
            <table>
                <tr>
                    <td class="m"><input type="button" class="m" value="Selected Lectures"onclick="selectedLectures()"></td>
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
            <form method="post" action="practisesOfLecture" id="selected_lectures">

            </form>
        </div>

        <div id="newLectures" class="m">

        </div>
    </div>
    <script>
        HideSelectedLectures();
    </script>
    <div id="content_footer"></div>
    <div id="footer">
        Copyright &copy; 2017
    </div>
</div>
</body>
</html>
