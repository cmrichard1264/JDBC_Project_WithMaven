package JDBC_Practice;

import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.utility;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TestCase_utility {
    @Test
    public void test1() throws SQLException {
        ResultSet result = utility.getResult("select * from employees");
        while (result.next()){
            String fullname = result.getString("first_name")+ " "+ result.getString("last_name");
            System.out.println(fullname);
        }
    }

    @Test(description = "Verify that Steven King has the highest salary")
    public void test2() throws SQLException {
        ResultSet result =utility.getResult("select * from employees");
        List<Integer> salaries = new ArrayList<>();
        for(int i =0; result.next();)
            salaries.add(result.getInt("salary"));
        Collections.sort(salaries);
        System.out.println(salaries);
        int maxSalary = salaries.get( salaries.size()-1 );
        System.out.println(maxSalary);
        String richestGuy = "";
        result = utility.getResult("select * from employees");
        while(result.next()){
            int money = result.getInt("salary");
            String fullname = result.getString(2)+" "+result.getString(3);
            if(money == maxSalary){
                richestGuy = fullname;
            }
        }
        System.out.println(richestGuy);
        Assert.assertEquals(richestGuy, "Steven King");
        result = utility.getResult("Select * from Locations");
        ResultSetMetaData rsm = result.getMetaData();
        System.out.println("CTotal Number of Columns: "+rsm.getColumnCount());
        System.out.println("Third colum Name: "+rsm.getColumnName(3));
        String[] ColumNames =new String[rsm.getColumnCount()];
        for(int i=0; i < ColumNames.length; i++){
            ColumNames[i]  = rsm.getColumnName(i+1 );
        }
        System.out.println(Arrays.toString(ColumNames));



    }
}
