import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Objects;

public class Client {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private String username;

    public static boolean connectionChecker() throws IOException {
        try{
            Socket socket = new Socket("localhost",9806);
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out.println("PING");
            String message = in.readLine();
            socket.close();
            return Objects.equals(message, "PONG");
        }catch (IOException e){
            return false;
        }
    }
//    public static void main(String[] args) {
//        try {
//            System.out.println("client started");
//            Socket socket = new Socket("localhost",9806);
//            Client client = new Client(socket);
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//    }
    public Client(Socket socket){
        try{
            this.socket = socket;
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.out = new PrintWriter(socket.getOutputStream(), true);
        }catch(IOException e){
            // catch
        }
    }
    public void setUsername(String username){
        this.username = username;
        out.println(username);
    }

    public void listenToMessage(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String respons;
                while(true){
                    try{
                        respons = in.readLine();
                        System.out.println(respons);
                    }catch(IOException e){
                        // todo
                    }
                }
            }
        }).start();
    }
}