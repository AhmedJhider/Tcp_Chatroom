import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

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
                PrintWriter out = new PrintWriter(soc.getOutputStream(),true);
                BufferedReader in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
                String message = in.readLine();
                if(Objects.equals(message, "PING")){
                    System.out.println("ponged it back");
                    out.println("PONG");
                    soc.close();
                }else{
                    System.out.println(message+ " in");
                    Thread clientHandler = new Thread(new ClientHandler(soc,message));
                    clientHandler.start();
                }
            }
        }catch (Exception e){
            System.out.println(e);
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

        public ClientHandler(Socket socket, String username){
            try{
                this.clientSocket = socket;
                this.username = username;
                out = new PrintWriter(this.clientSocket.getOutputStream(),true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            }catch(IOException e){
                System.out.println(e);
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
                if (clientHandler.username != this.username){
                    clientHandler.out.println(message);
                }
            }
        }
    }
}