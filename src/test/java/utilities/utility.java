package utilities;
import java.sql.*;
import java.util.ArrayList;
public class utility {

    private final static String username = ConfigurationReader.getProperty("JDBC_UserName"),
            password = ConfigurationReader.getProperty("JDBC_PassWord"),
            url = ConfigurationReader.getProperty("JDBC_URL");

    /*private final  static String url = "jdbc:oracle:thin:@18.207.248.47:1521:xe",
            username ="hr",
            password = "hr";*/

    private static Connection connection;
    private static Statement statement;

    public static DatabaseMetaData metadata;

    static {
        try {
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();
            metadata = connection.getMetaData();
        } catch (SQLException e) {
        }
    }



    //Retrieves the query from SQL
    public static ResultSet getResult(String sql) {
        ResultSet result = null;
        try {
            result = statement.executeQuery(sql);
        } catch (SQLException e) {

        }
        return result;
    }


    //to close connection
    public static void tearDown() {
        try {
            connection.close();
        } catch (SQLException e) {
        }
    }


    //update sql from eclipse // INSERT, UPDATE, , DELETE, ALTER, TRUNCATE, DROP
    public static void UpdateQuery(String sql) {
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {

        }
    }


    //custom method
    public static ArrayList<String> selectFrom(String queryName) {
        ResultSet result = utility.getResult("select * from "+queryName);
        ArrayList<String> fullName = new ArrayList<>();
        try {
            while(result.next()) {
                String firstname = result.getString("first_name");
                String lastname = result.getString("last_name");
                fullName.add(firstname +" "+lastname);
            }
        } catch (SQLException e) {

        }
        return fullName;

    }
}
