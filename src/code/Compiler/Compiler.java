package code.Compiler;

import java.io.*;

public class Compiler {

  String source_codes_path;

  public Compiler(String source_codes_path) {
    this.source_codes_path = source_codes_path;
  }

  public  String printLines(String name, InputStream ins) throws Exception {
    String line = null, output="";
    BufferedReader in = new BufferedReader(
        new InputStreamReader(ins));
    while ((line = in.readLine()) != null) {
        System.out.println(name + " " + line);
        output+=name + " " + line + "\n";
    }
    return output;
  }

  public  String runProcess(String command) throws Exception {
    String output="";
    Process pro = Runtime.getRuntime().exec(command);
    output+=printLines("", pro.getInputStream())+"\n";
    output+=printLines(command + " stderr:", pro.getErrorStream())+"\n";
    pro.waitFor();
    return(output);
    //System.out.println(command + " exitValue() " + pro.exitValue());
  }

  public String compileSourceCode(String programmingLanguage,String sourceCodeName,String sourceCode){


    if(programmingLanguage.equalsIgnoreCase("java")){
      return compileJavaSourceCode(sourceCodeName,sourceCode);
    }
    else {
      return compileCSourceCode(sourceCodeName,sourceCode);
    }
  }

  public String compileJavaSourceCode(String sourceCodeName,String sourceCode){
    String result="";
    try {
      PrintWriter writer=new PrintWriter(source_codes_path+"/"+sourceCodeName+".java");
      writer.println(sourceCode);
      writer.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    try {
      result= runProcess("javac "+source_codes_path+"/"+sourceCodeName+".java");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }

  public String executeJavaSourceCode(String sourceCodeName,String sourceCode){
    String result="";
    try {
      result=runProcess("java -cp "+source_codes_path+" "+sourceCodeName);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return  result;
  }

  public String compileCSourceCode(String sourceCodeName,String sourceCode){
    String result="";

    PrintWriter writer= null;
    try {
      writer = new PrintWriter(source_codes_path+"/"+sourceCodeName+".c");
      writer.println(sourceCode);
      writer.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    try {
      result=runProcess("gcc -o "+source_codes_path+"/"+sourceCodeName+" "+source_codes_path+"/"+sourceCodeName+".c");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }

  public String executeCSourceCode(String sourceCodeName,String sourceCode){
    String result="";
    try {
      result=runProcess(source_codes_path+"/"+sourceCodeName);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }

  public String executePythonSourceCode(String sourceCodeName,String sourceCode){
    String result="";

    try {
      PrintWriter writer=new PrintWriter(source_codes_path+"/"+sourceCodeName+".py");
      writer.println(sourceCode);
      writer.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    try {
      result=runProcess("python "+source_codes_path+"/"+sourceCodeName+".py");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }

  public String executeSourceCode(String programmingLanguage,String sourceCodeName,String sourceCode){
    if(programmingLanguage.equalsIgnoreCase("Java")){
      return executeJavaSourceCode(sourceCodeName,sourceCode);
    }
    else if(programmingLanguage.equalsIgnoreCase("C")){
      return executeCSourceCode(sourceCodeName,sourceCode);
    }
    else if(programmingLanguage.equalsIgnoreCase("Python")){
      return executePythonSourceCode(sourceCodeName,sourceCode);
    }
    else
      return "Selected Programming Language has Not Supported By US Excuse US";
  }

  public static void main(String[] args) {
    try {
      //runProcess("javac Simple.java");
      //runProcess("java Simple");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}