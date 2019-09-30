
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
    <link rel="stylesheet" type="text/css" href="style/popupStyle.css"/>
    <!-- i remove the style code of this html document  -->
</head>

<body>

<!-- HTLM Elements For popup window -->

<!-- Trigger/Open The Modal -->

<!-- The Modal -->
<div id="myModal" class="modal">

    <!-- Modal content -->
    <div class="modal-content">
        <div class="modal-header">
            <span class="close">&times;</span>
            <h2>Modal Header</h2>
        </div>
        <div class="modal-body">
            <p>Some text in the Modal Body</p>
            <p>Some other text...</p>
            <form>
                <table>
                    <tr>
                        <td class="m">
                            <select  id="aaaaa" name="mark" required="required">
                                <option>10</option>
                                <option>20</option>
                                <option>30</option>
                                <option>40</option>
                                <option>50</option>
                                <option>60</option>
                                <option>70</option>
                                <option>80</option>
                                <option>90</option>
                                <option>100</option>
                            </select>
                        </td>
                        <td class="m">
                            <input type="button"  value="set mark for this job" onclick="somthingF()">
                        </td>
                    </tr>

                </table>
            </form>
        </div>
        <div class="modal-footer">
            <h3>Modal Footer</h3>
        </div>
    </div>

</div>

<!-- HTML Element for pop up window  -->



<!-- HTLM Elements For Design Problem's popup window -->

<!-- Trigger/Open The Modal -->

<!-- The Modal -->
<div id="myModal1" class="modal">

    <!-- Modal content -->
    <div class="modal-content">
        <div class="modal-header">
            <span class="close">&times;</span>
            <h2>Programming Practise </h2>
        </div>
        <div class="modal-body">
            <p id="lecture_title"> </p>
            <form>
                <table>
                    <tr>
                        <td class="m">
                            <h6> Select Programming Language For This Problem </h6>
                        </td>
                        <td class="m">
                            <select  id="programming_language_item_list" name="prgramming_language" required="required">
                                <option>C</option>
                                <option>PYTHON</option>
                                <option>JAVA</option>
                                <option>ALL</option>
                            </select>
                        </td>
                    </tr>

                    <tr>
                        <td class="m">
                            Practise Title
                        </td>
                        <td class="m">
                            <input type="text" name="title" id="practise_title" placeholder="Enter Title For Practise " required="required"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="m">
                            <h6> Write Problem Description : </h6>
                        </td>
                        <td class="m">
                            <textarea name='problemDescription' id='problem_description' class='java' rows='13' style='width:97%;border: 1px solid pink;border-radius:5px;margin:1px;'  required="required"></textarea>
                        </td>
                    </tr>
                    <tr>
                        <td class="m">
                            <input type="button" value="Broadcast Practise For Your Students " onclick="designProblem()" />
                        </td>
                    </tr>
                </table>
            </form>
        </div>
        <div class="modal-footer">
            <h3>Modal Footer</h3>
        </div>
    </div>

</div>

<!-- HTML Element for Design Problem's  Pop Up Window  -->


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
        <h1>Student List And Practise List</h1></div>

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

        function listOfStudent() {
            var url="listOfStudentServlet";
            var parameter=null;
            createXmlHttpRequest();
            try{
                xmlHttpRequest.open("POST",url,true);
                xmlHttpRequest.onreadystatechange=getJsonObjectResponseFromServer;
                xmlHttpRequest.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
                xmlHttpRequest.overrideMimeType("text/plain;");
                xmlHttpRequest.send(parameter);
            }
            catch (e) {
                alert(e.message);
            }
        }

        function getJsonObjectResponseFromServer() {
            if(xmlHttpRequest.readyState==4 && xmlHttpRequest.status==200){
                var jsonResponse=JSON.parse(xmlHttpRequest.responseText);
                displayStudentList(jsonResponse);
            }
        }

        function displayStudentList(studentList) {
            if(studentList.length!=0){
                var out="";
                var i;
                out+='<h3> List Of Students That Have  This Lecture : </h3>';
                out+='<table>';
                for(i=0;i<studentList.length;i++){
                    var student_id=studentList[i].user_id;
                    var student_full_name=studentList[i].user_fullName;
                    var student_college=studentList[i].user_college;
                    out+='<tr>';
                    out+='<td> User Id '+student_id+'</td>';
                    out+='<td> Full Name '+student_full_name+'</td>';
                    out+='<td> College '+student_college+'</td>';
                    out+='<td><input type="button" value="view practises that solved " onclick="practisesThatSolved('+student_id+')"/></td>';
                    out+='</tr>';
                }
                document.getElementById("studentList").style.display='block';
                document.getElementById("studentList").innerHTML=out;
            }
            else{
                document.getElementById("studentList").style.display='block';
                document.getElementById("studentList").innerHTML='<h3> There Are No Students That Have Lecture with This Professor </h3>';
            }
        }

        function practisesThatSolved(student_id) {
            var url="getPractisesThatSolvedByStudentServlet";
            var parameter="student_id="+student_id;
            createXmlHttpRequest();
            try{
                xmlHttpRequest.open("POST",url,true);
                xmlHttpRequest.onreadystatechange=getPractisesFromServer;
                xmlHttpRequest.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
                xmlHttpRequest.overrideMimeType("text/plain;");
                xmlHttpRequest.send(parameter);
            }
            catch (e) {
                alert(e.message);
            }
        }
        function getPractisesFromServer() {
            if(xmlHttpRequest.readyState==4 && xmlHttpRequest.status==200){
                var jsonResponse=JSON.parse(xmlHttpRequest.responseText);
                displayPractises(jsonResponse);
            }
        }

        function hideStudentList() {
            document.getElementById("studentList").style.display='none';
        }
        function hidePractiseList() {
            document.getElementById("practiseList").style.display='none';
        }

        function displayPractises(practiseList) {
            if(practiseList.length!=0){
                var out='';
                var i;
                out+='Practise List ';
                out+='<table>';
                for(i=0;i<practiseList.length;i++){
                    var practise_id=practiseList[i].practise_id;
                    var practise_title=practiseList[i].practise_title;
                    var practise_description=practiseList[i].practise_description;
                    var programming_languages=practiseList[i].programming_languages;
                    var lecture_name=practiseList[i].lecture_name;
                    out+='<tr>';
                    out+='<td class="m"> Lecture </td>'+lecture_name;
                    out+='<td> Practise Title </td>'+practise_title;
                    out+='<td><input type="button" value="View Practise Description" onclick="viewPractise('+practise_id+',\''+programming_languages+'\''+',\''+practise_description+'\''+')'+'"/></td>';
                    out+='<td><input type="button" value="View Solution And Evaluate " onclick="viewSolution('+practise_id+')"/></td>'
                }
                out+='</table>';
                document.getElementById("practiseList").style.display='block';
                document.getElementById("practiseList").innerHTML=out;
            }
            else{
                document.getElementById("practiseList").style.display='block';
                document.getElementById("practiseList").innerHTML='<h3> There Are Not Practises That Designed By Professor </h3>';
            }
        }

        function popupWindow() {
            var modal=document.getElementById("myModal");
            // Get the <span> element that closes the modal
            var span = document.getElementsByClassName("close")[0];

            // When the user clicks the button, open the modal
            modal.style.display = "block";

            // When the user clicks on <span> (x), close the modal
            span.onclick = function() {
                modal.style.display = "none";
            }

            // When the user clicks anywhere outside of the modal, close it
            window.onclick = function(event) {
                if (event.target == modal) {
                    modal.style.display = "none";
                }
            }
        }

        function designProblem() {
            var practise_title=document.getElementById("practise_title").value;
            var programming_languages=document.getElementById("programming_language_item_list").value;
            var practise_description=document.getElementById("problem_description").value;
            var url="designProblemServlet";
            var parameters="practise_title="+practise_title+"&programming_languages="+programming_languages+"&practise_description="+practise_description;
            createXmlHttpRequest();
            try{
                xmlHttpRequest.open("POST",url,true);
                //document.getElementById("myModal1").style.display='none';
                xmlHttpRequest.onreadystatechange=getInfoAboutDesignProblem;
                xmlHttpRequest.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
                xmlHttpRequest.overrideMimeType("text/plain;");
                xmlHttpRequest.send(parameters);
            }
            catch (e) {
                alert(e.message);
            }
        }

        function showDesignProblemPopUpWindow(lecture_name) {

            document.getElementById("lecture_title").innerHTML="<h2>Lecture "+lecture_name+"</h2>";
            var modal=document.getElementById("myModal1");
            // Get the <span> element that closes the modal
            var span = document.getElementsByClassName("close")[0];

            // When the user clicks the button, open the modal
            modal.style.display = "block";

            // When the user clicks on <span> (x), close the modal
            span.onclick = function() {
                modal.style.display = "none";
            }

            // When the user clicks anywhere outside of the modal, close it
            window.onclick = function(event) {
                if (event.target == modal) {
                    modal.style.display = "none";
                }
            }
        }

        function getInfoAboutDesignProblem() {
            if(xmlHttpRequest.readyState==4 && xmlHttpRequest.status==200){
                alert("Problem That Designed By Professor Is Added To Data Base Successfully ");
                console.log("operation was : "+xmlHttpRequest.responseText);
            }
        }

    </script>
    <div id="fors">
        <form method="post">
            <table>
                <tr>
                    <td class="m"><input type="button" value="View List Of Students Of This Lecture" onclick="listOfStudent()"></td>
                </tr>
                <tr>
                    <td class="m">
                        <input type="button"  value="Design Problem and give practise to students " onclick="showDesignProblemPopUpWindow('<%=(String)request.getSession().getAttribute("lecture_name")%>')">
                    </td>
                </tr>
            </table>
        </form>

        <div calss="m">
            <form id="studentList">

            </form>
        </div>

        <div class="m">
            <form id="practiseList">

            </form>
        </div>

        <script>
            hideStudentList();
            hidePractiseList();
        </script>

    </div>

    <div id="content_footer"></div>
    <div id="footer">
        Copyright &copy; 2017
    </div>
</div>
</body>
</html>
