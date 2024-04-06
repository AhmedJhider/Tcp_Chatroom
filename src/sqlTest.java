import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class sqlTest {
    public static void main(String[] args){
        String url="jdbc:mysql://localhost:3306/chatroom";
        String username="root";
        String password="";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url,username,password);
            Statement statement= connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from test");
            while(resultSet.next()){
                System.out.println(resultSet.getInt(1) +" "+ resultSet.getString(2));
            }
            connection.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
