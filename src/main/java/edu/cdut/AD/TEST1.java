package edu.cdut.AD;
import java.sql.*;

public class TEST1<sql> {
    private static String className="com.mysql.jdbc.Driver";

    private static String url = "jdbc:mysql://127.0.0.1:3306/oa";

    private static String username = "root";

    private static String password = "050422";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName(className); //java反射机制，注册驱动

        Connection connection = DriverManager.getConnection(url, username, password);

        System.out.println(connection);

//System.out.println(connection);

        Statement statement = connection.createStatement(); //创建mysql的状态
//
  //      String sql = "select * from employee";
        //执行上述sql语句
  //      ResultSet resultSet = statement.executeQuery(sql); //需要返回数据，加上Query

    //    while (resultSet.next()) {
  //          System.out.println(resultSet.getString("emp_name"));

    String sql = "update employee set emp_name='罗永浩' where e_id=1";
        statement.execute(sql); //实时同步修改

        }
    }
