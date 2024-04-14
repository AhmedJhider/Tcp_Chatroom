import java.sql.*;

public class DatabaseHandler {
    private Connection connection;
    private Statement statement;
    public DatabaseHandler(){
        String url="jdbc:mysql://localhost:3306/chatroom";
        String username="root";
        String password="";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(url,username,password);
            this.statement= connection.createStatement();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public ResultSet exeQuery(String query) throws SQLException {
        return statement.executeQuery(query);
    }
    public void exeInsert(String query) throws SQLException {
        statement.executeUpdate(query);
    }
    public void closeDb() throws SQLException {
        connection.close();
    }
}
