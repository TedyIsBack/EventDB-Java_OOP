import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    static Connection connection = null;

    static  Connection getConnection(){
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager
                    .getConnection("jdbc:h2:tcp://localhost/C:\\Users\\User\\IdeaProjects\\OOP_DB_JavaProject\\EventSystem","sa","1234");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
