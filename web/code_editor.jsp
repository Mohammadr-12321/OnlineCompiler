<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang='en'>
<head>
<link rel="SHORTCUT ICON" href="/image/Shortcuts-icon.png" />
<link rel="stylesheet" type="text/css" href="/style/style.css" />
<link rel="stylesheet" type="text/css" href="style/backgroundBootstrap.css" />
<!--
<link type="text/css" rel="stylesheet" href="/css/SyntaxHighlighter.css"/>
-->

<title>Online Java Compiler | Java Editor - Javatpoint</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<meta name="keywords" content="online, java, Compiler, editor, free, run"/>
<meta name="description" content="Online Java compile or java editor tool, free edit compile and run java program, get java output online."/>
<meta name="viewport" content="width=device-width">						
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<link rel="canonical" href="http://www.javatpoint.com/opr/onlinejavacompiler.jsp" />
<meta property="og:locale" content="en_US" />
<meta property="og:type" content="article" />
<meta name="twitter:title" property="og:title" content="Online Java Compiler | Java Editor - Javatpoint" />
<meta name="twitter:description" property="og:description" content="Online Java compile or java editor tool, free edit compile and run java program, get java output online." />
<meta property="og:url" content="http://www.javatpoint.com/opr/onlinejavacompiler.jsp" />
<meta property="og:site_name" content="www.javatpoint.com" />
<meta name="twitter:card" content="summary"/>
<meta name="twitter:site" content="@pagejavatpoint"/>
<meta name="twitter:domain" content="www.javatpoint.com"/>
<meta name="twitter:creator" content="@pagejavatpoint"/>

<!-- <script src="googleanalytics.js" type="text/javascript"></script> -->
</head>
<body>
<div class="bg" >
<div style="margin:0px;float:left">


<!-- <script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
 compilerheader
<ins class="adsbygoogle"
     style="display:inline-block;width:970px;height:90px"
     data-ad-client="ca-pub-4699858549023382"
     data-ad-slot="4515123113"></ins>

-->
<script>

    // wen send our request through the XMLHttpRequest
    var xmlHttpRequest;

    // *******************************************************
    // function for create xml http request
    function createXmlHttpRequest(){
        if(window.ActiveXObject){
            xmlHttpRequest=new ActiveXObject("Microsoft.XMLHTTP");
        }
        else if(window.XMLHttpRequest){
            xmlHttpRequest=new XMLHttpRequest();
        }
    }

    // ***********************************************************
    // function for get information from server side


    function getInfoFromServer(){
        if(xmlHttpRequest.readyState==4 && xmlHttpRequest.status==200){
            var val=xmlHttpRequest.responseText;
            document.getElementById('output').value=val;
        }
    }


    function provideProgrammingLanguageEnvironment() {
        document.getElementById('code').value="";
        if(programmingLanguage.toUpperCase()=="JAVA"){
        document.getElementById('code').value=
                "import java.util.*; "+"\n"+"import java.lang.*; "+"\n"+"import java.io.*; "+"\n\n"+"public class "+sourceCodeName+"{ "+"\n"+"public static void main(String args[]){ "+"\n} \n}";
        }
        else if(programmingLanguage.toUpperCase()=="C"){
            document.getElementById('code').value="#include <stdio.h>"+"\n"+"int main(){"+"\n"+"return 0;\n"+"}";
        }

        document.getElementById('code').removeAttribute("disabled");
        document.getElementById('bt2').removeAttribute("disabled");
        document.getElementById('bt3').removeAttribute("disabled");
        document.getElementById('bt4').removeAttribute("disabled");
        document.getElementById('bt5').removeAttribute("disabled");

    }

    // **************************************************

    var programmingLanguage;
    var sourceCodeName;
    function createNewSourceCode(){
    programmingLanguage=prompt("Please Enter Your Desired Programming Language : ....");
    sourceCodeName=prompt("Please Enter Your Program Name : ....");

    while(programmingLanguage==null){
        programmingLanguage=prompt("Please Enter Your Desired Programming Language : ....");

        while(sourceCodeName==null){
            sourceCodeName=prompt("Please Enter Your Program Name : ....");
        }
    }

    provideProgrammingLanguageEnvironment();
}

     // **********************************************************
     // function for compile Compiler Programming Languages Source Codes
    function compileSourceCode() {
        if(programmingLanguage.toUpperCase()=="PYTHON"){
            alert("Selected Programming Language have implemented using interpreter \n This means " +
                "that your source code  directly execute by interpreter , for execute your source code click on Run Button");
        }
        else{
            var sourceCode=encodeURIComponent(document.vinform.code.value);
            console.log(document.vinform.code.value);
            var url="CompilerServlet";
            var parameters="sourceCode="+sourceCode+"&sourceCodeName="+sourceCodeName+"&programmingLanguage="+programmingLanguage;
            createXmlHttpRequest();
            try{
                xmlHttpRequest.open("POST",url,true);
                xmlHttpRequest.onreadystatechange=getInfoFromServer;
                xmlHttpRequest.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
                xmlHttpRequest.overrideMimeType("text/plain;");
                xmlHttpRequest.send(parameters);
            }
            catch (e) {
                alert(e.message);
            }
        }
    }


    // ************************************************
    // function for execute source code

    function executeSourceCode() {
        // because of thiis error  org.apache.coyote.http11.AbstractHttp11Processor process
        // INFO: Error parsing HTTP request header
        //**************************************
        // we handle special characters in parameters with xmlhttprequest

        var sourceCode=encodeURIComponent(document.vinform.code.value);
        var url="executeServlet";
        var parameters="sourceCode="+sourceCode+"&sourceCodeName="+sourceCodeName+"&programmingLanguage="+programmingLanguage;
        createXmlHttpRequest();

        try{
            xmlHttpRequest.open("POST",url,true);
            xmlHttpRequest.onreadystatechange=getInfoFromServer;
            xmlHttpRequest.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
            xmlHttpRequest.overrideMimeType("text/plain;");
            xmlHttpRequest.send(parameters);
        }
        catch (e) {
            alert(e.message);
        }
    }

    // *********************************************************
    // following function is for store source code in the my sql data base

    function storeSourceCodeInDataBase() {
        var sourceCode=encodeURIComponent(document.vinform.code.value);

        if(sourceCodeName!=null){
            var url="storeServlet";
            var parameters="sourceCode="+sourceCode+"&sourceCodeName="+sourceCodeName+"&programmingLanguage="+programmingLanguage;
            createXmlHttpRequest();

            try{
                xmlHttpRequest.open("POST",url,true);
                xmlHttpRequest.onreadystatechange=getInfoFromServer;
                xmlHttpRequest.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
                xmlHttpRequest.overrideMimeType("text/plain;");
                xmlHttpRequest.send(parameters);
            }
            catch (e) {
                alert(e.message);
            }
        }

    }

    // **********************************************
    // following function for retrieve source code from data base
    function retrieveSourceCodeFromDataBase() {
        var source_code_name=prompt("Please Enter Your Program Name That Already Stored in Your User Account : ");
        if(source_code_name!=null){
            document.getElementById('code').removeAttribute("disabled");
            document.getElementById('bt2').removeAttribute("disabled");
            document.getElementById('bt3').removeAttribute("disabled");
            document.getElementById('bt4').removeAttribute("disabled");
            document.getElementById('bt5').removeAttribute("disabled");

            sourceCodeName=source_code_name;
            var url="retrieveServlet";
            var parameters="sourceCodeName="+sourceCodeName;
            createXmlHttpRequest();
            try{
                xmlHttpRequest.open("POST",url,true);
                xmlHttpRequest.onreadystatechange=getInfoOpen;
                xmlHttpRequest.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
                xmlHttpRequest.overrideMimeType("text/plain;");
                xmlHttpRequest.send(parameters);
            }
            catch (e) {
                alert(e.message);
                console.log(e.message);
            }
        }
    }

function getInfoOpen(){
    if(xmlHttpRequest.readyState==4 && xmlHttpRequest.status==200){
        var response=xmlHttpRequest.responseText;
        var jsonResponse=JSON.parse(xmlHttpRequest.responseText);
        document.getElementById('code').value=jsonResponse.sourceCode;
        programmingLanguage=jsonResponse.programmingLanguage;
        if(programmingLanguage=="C_Source_Code"){
            programmingLanguage="c";
        }
        else if(programmingLanguage=="JAVA_Source_Code"){
            programmingLanguage="java";
            console.log("programming language is : "+programmingLanguage);
        }
        else if(programmingLanguage=="PYTHON_source_code"){
            programmingLanguage="python";
        }
        console.log("programming language is : "+programmingLanguage);
    }

}


</script>
</div>
<hr/>
<h1 class> Online Java Compiler | Java Editor</h1>
<p>Online java Compiler or java editor tool compiles and executes given java code online.</p>

<div style='width:100%'><div style='float:left;width:50%'>
        <form name='vinform' class='vinform'>
            <textarea name='code' id='code' class='java' rows='13' style='width:97%;border: 1px solid pink;border-radius:5px;margin:1px;' disabled="disabled"></textarea>
            <br/>
            <input type='hidden' name='filename' value='Simple'/>
<input type='text' name='args' style='width:97%;padding:10px;border: 1px solid pink;border-radius:5px;margin:1px;' placeholder='enter command line arguments if any (optional)'/>
<br>
<input type='text' name='classname' style='border-radius:2px;margin:1px;width:97%;padding:10px;border: 1px solid pink;border-radius:5px;margin:1px;' placeholder='enter class name if you change class (optional)'/>
<br>
<input type='button' id='bt6' class='subbutton' style='margin-top:4px' value='New' onCLick='createNewSourceCode()'>
<input type='button' id='bt2' class='subbutton' style='margin-top:4px' value='Compile' onCLick='compileSourceCode()' disabled="disabled">
<input type='button' id='bt3' class='subbutton' style='margin-top:4px' value='Run' onCLick='executeSourceCode()' disabled="disabled">
<input type='button' id='bt4' class='subbutton' style='margin-top:4px' value='Save' onCLick='storeSourceCodeInDataBase()' disabled="disabled">
<input type='button' id='bt5' class='subbutton' style='margin-top:4px' value='Open' onCLick='retrieveSourceCodeFromDataBase()' >
        </form></div>
    <div style='float:left;width:45%;margin-left:30px'>
    <p style='text-align:center;padding:4px;color:white;background-color:#6666CC'> Output </p>
    <textarea id='output' name='output' class='java' rows='13' style='width:97%;border: 1px solid pink;border-radius:5px;margin:1px;' disabled="disabled"></textarea>
    <div style="" id="javacodeoutput"></div>
</div>
</div>


<div style="clear:both"></div>
<br/><br/><br/><br/>

<h2 class="h2">Online Java Compiler</h2>
<p>Write you java code and compile it online. The java Compiler or java editor tool helps you to create, compile and run java program. Our aim is to provide you a free online java Compiler so you can test you java code anywhere and anytime.</p>
</div>
</body>
</html>