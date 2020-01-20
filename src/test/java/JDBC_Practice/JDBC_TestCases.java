package JDBC_Practice;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.*;

public class JDBC_TestCases {

    String url = "jdbc:oracle:thin:@18.207.248.47:1521:xe",
     username = "hr",
     password = "hr";
    Connection connection;
    Statement statement;


    @BeforeMethod
    public void setup() {
        try {
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();
        } catch (SQLException e) {

        }

    }

    @Test(description = "Verify that Seyfos salary is greater than Ibrohims" )
    public void test1() throws SQLException {
        int seyfoSalary = 0,
                ibrohimSalary = 0;

      String query = "select * from testers";
      ResultSet result = statement.executeQuery(query);

       while (result.next()){
             String name = result.getString(2);
             int salary = result.getInt(3);
             if(name.equalsIgnoreCase("Seyfo")){
                 seyfoSalary = salary;
             }
             if(name.equalsIgnoreCase("Ibrohim")){
                 ibrohimSalary = salary;
             }
       }
        System.out.println("Seyfo salary is "+seyfoSalary);
        System.out.println("Ibrohim salary is "+ibrohimSalary);
        Assert.assertTrue(seyfoSalary > ibrohimSalary);



    }
}
