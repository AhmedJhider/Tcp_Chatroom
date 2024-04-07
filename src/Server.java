import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class Server {
    private static DatabaseHandler Db = new DatabaseHandler();

    public static void main(String[] args) {
        try{
            Db = new DatabaseHandler();
            System.out.println("Waiting for clients ...");
            ServerSocket ss = new ServerSocket(9806);
            while (true){
                Socket soc = ss.accept();
                System.out.println("Connection established " + soc.getInetAddress());
                Thread clientHandler = new Thread(new ClientHandler(soc));
                clientHandler.start();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static boolean verifSignup(String username) throws SQLException {
        ResultSet result = Db.exeQuery("SELECT * FROM users WHERE name = '"+username+"'");
        return result.next();
    }
    public static void execSignup(String username) throws SQLException{
        Db.exeInsert("INSERT users(name) VALUES ('"+username+"')");
    }
    static class ClientHandler implements Runnable{
        public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
        private Socket clientSocket;
        private String username;
        private String message;
        private PrintWriter out;
        private BufferedReader in;

        public ClientHandler(Socket socket){
            try{
                this.clientSocket = socket;
                out = new PrintWriter(this.clientSocket.getOutputStream(),true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                clientHandlers.add(this);
                broadcastMessage(username+" has connected to chatroom");
            }catch(IOException e){
                // catch
            }
        }

        @Override
        public void run(){
            try{
                while ((message = in.readLine())!=null){
                broadcastMessage(username+" : "+message);
                }
            }catch (Exception e){
                broadcastMessage(username+" has disconnected");
            }
        }

        public void broadcastMessage(String message){
            for (ClientHandler clientHandler : clientHandlers){
                if (clientHandler.username != username){
                    clientHandler.out.println(message);
                }
            }
        }
    }
}