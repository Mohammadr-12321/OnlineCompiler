package code.Listener;

import code.Model.DBConnection;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Connection;
import java.sql.SQLException;

public class ApplicationListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("in ContextInitialized Method.");
        Connection connection=DBConnection.getConnectionToDataBase();
        servletContextEvent.getServletContext().setAttribute("dbconnection",connection);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("in contextDestroyed method ");
        Connection connection= (Connection) servletContextEvent.getServletContext().getAttribute("dbconnection");
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
