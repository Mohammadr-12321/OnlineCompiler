
package code.Model;
import java.sql.*;

public class DBConnection {

    public  static Connection getConnectionToDataBase(){
        Connection myConnection=null;
        String data_base_url="jdbc:mysql://localhost:3306/CompilerDB";
        String data_base_user_name="root";
        String data_base_password="123456";

        try{
            Class.forName("org.gjt.mm.mysql.Driver");
            System.out.println("MySql JDBC Driver Registered ... ");
            myConnection=DriverManager.getConnection(data_base_url,data_base_user_name,data_base_password);
        }catch (SQLException e){
            System.out.println("Connection Failed . Check output console !!! ");
            e.printStackTrace();
        }
        catch(ClassNotFoundException e){
            System.out.println("Connection Failed . MySql JDBC Driver Not Found ... !!!");
            e.printStackTrace();
        }

        if(myConnection!=null){
            System.out.println("Connection to data base Successfully");
        }
        return myConnection;
    }
}

