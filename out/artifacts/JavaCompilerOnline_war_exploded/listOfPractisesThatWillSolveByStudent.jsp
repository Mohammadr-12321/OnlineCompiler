<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
<head>
    <title>userpage</title>
    <meta name="description" content="website description" />
    <meta name="keywords" content="website keywords, website keywords" />
    <link rel="stylesheet" type="text/css" href="style/style.css" />
    <link rel="stylesheet" type="text/css" href="style/userPageStyle.css" />
    <link rel="stylesheet" type="text/css" href="style/popupStyle.css"/>

</head>

<body>

<!-- HTLM Elements For Design Problem's popup window -->

<!-- Trigger/Open The Modal -->

<!-- The Modal -->
<div id="practisePopUpWindow" class="modal">

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
                            <h6 id="programming_languages"> </h6>
                        </td>
                    </tr>

                    <tr>
                        <td class="m">
                            <h6 id="practise_title"></h6>
                        </td>
                    </tr>
                    <tr>
                        <td class="m">
                            <h6 id="problem_description"></h6>
                        </td>
                    </tr>
                    <tr>
                        <td class="m">
                            <input type="button" value="Solve The Practise " onclick="toSolvePractise()" />
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


<script>
    var xmlHttpRequest;
    var practise_id;
    // function for create xml http request
    function createXmlHttpRequest(){
        if(window.ActiveXObject){
            xmlHttpRequest=new ActiveXObject("Microsoft.XMLHTTP");
        }
        else if(window.XMLHttpRequest){
            xmlHttpRequest=new XMLHttpRequest();
        }
    }

    function getListOfPractisesThatWillSolvedByStudent() {
        var url="practisesOfLectureServlet";
        var parameters=null;
        createXmlHttpRequest();
            try{
                xmlHttpRequest.open("POST",url,true);
                xmlHttpRequest.onreadystatechange=getListOfPractisesThatWillSolvedByStudentFromServer;
                xmlHttpRequest.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
                xmlHttpRequest.overrideMimeType("text/plain;");
                xmlHttpRequest.send(parameters);
            }
            catch (e) {
                alert(e.message);
            }
    }

    function getListOfPractisesThatWillSolvedByStudentFromServer() {
        if(xmlHttpRequest.readyState==4 && xmlHttpRequest.status==200){
            var jsonRespnse=JSON.parse(xmlHttpRequest.responseText);
            displayListOfPractises(jsonRespnse);
        }
    }

    function displayListOfPractises(practiseList) {
        if(practiseList.length!=0){
            var i;
            var out='';
            var lecture_name=practiseList[0].lecture_name;
            out+='<h2> Practises Of '+lecture_name+'</h2>';
            out+='<table>';
            for(i=0;i<practiseList.length;i++){
                out+='<tr>';
                out+='<td> Practise Title '+practiseList[i].practise_title+' </td>';
                out+='<td>' +
                    ' <input type="button" value="View Practise Description " ' +
                    'onclick="showPractisePopUpWindow('+practiseList[i].practise_id+',\''+practiseList[i].practise_title+'\''+',\''+
                    practiseList[i].programming_languages+'\''+',\''+practiseList[i].practise_description+'\''+',\''+practiseList[i].lecture_name+'\''+')"/>' +
                    '</td>';
                out+='</tr>';
            }
            out+='</table>';
            document.getElementById("practisesThatWillSolvedByStudent").innerHTML=out;
        }
    }


    function showPractisePopUpWindow(practiseId,practise_title,programming_languages,practise_description,lecture_name) {
        document.getElementById("lecture_title").innerHTML="<h2>Lecture "+lecture_name+"</h2>";
        document.getElementById("programming_languages").innerHTML=programming_languages;
        document.getElementById("practise_title").innerHTML=practise_title;
        document.getElementById("problem_description").innerHTML=practise_description;
        practise_id=practiseId;
        var modal=document.getElementById("practisePopUpWindow");
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

    function toSolvePractise() {
        var url="goToEditorForSolveProblemServlet";
        var parameter="practise_id="+practise_id;
        createXmlHttpRequest();
        try{
            xmlHttpRequest.open("POST",url,true);
            xmlHttpRequest.onreadystatechange=result;
            xmlHttpRequest.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
            xmlHttpRequest.send(parameter);
        }
        catch(e){
            alert(e.message);
        }
    }

    function result() {
        if(xmlHttpRequest.readyState==4 && xmlHttpRequest.status==200){
            window.location.href=xmlHttpRequest.getResponseHeader("Location");
        }
    }

</script>
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
        <h1>Practises Of Student </h1>
        <!--a id="lin" href="LogoutSessionServlet">Logout</a-->
    </div>
    <div id="fors">

        <form method="post" name="form" accept-charset="UTF-8">
            <div id="practisesThatWillSolvedByStudent">

            </div>
        </form>

        <script>
            getListOfPractisesThatWillSolvedByStudent();
        </script>

    </div>

    <div id="content_footer"></div>
    <div id="footer">
        Copyright &copy;
    </div>
</div>
</body>
</html>

