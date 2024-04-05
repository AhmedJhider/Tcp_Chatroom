import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) {
        try{
            System.out.println("Waiting for clients ...");
            ServerSocket ss = new ServerSocket(9806);
            Socket soc = ss.accept();
            System.out.println("Connection established "+soc.getInetAddress());
            Thread clientHandler = new Thread(new ClientHandler(soc));
            clientHandler.start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    static class ClientHandler implements Runnable{
        private Socket clientSocket;
        private String userName;

        public ClientHandler(Socket socket){
            this.clientSocket = socket;
        }

        @Override
        public void run(){
            try{
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true);

                String userName = in.readLine();
                out.println(userName);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}