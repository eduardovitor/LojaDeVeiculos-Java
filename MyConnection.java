
package lojadeveiculo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class MyConnection {
    private static Connection connection=null;
    public static Connection getConnection(){
        try{
            if(connection==null){
            connection=DriverManager.getConnection("jdbc:mysql://localhost/lojadeveiculos","root","mysqlvitor123");  
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return connection;
    }
}
