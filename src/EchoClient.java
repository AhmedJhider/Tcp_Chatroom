import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class EchoClient {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private String username;

    public static void main(String[] args) {
        try {
            System.out.println("client started");
            Socket socket = new Socket("localhost",9806);

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("write username: ");
            String username = userInput.readLine();
            EchoClient client = new EchoClient(socket,username);
            client.listenToMessage();
            String message;
            while((message = userInput.readLine())!=null){
                client.out.println(message);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public EchoClient(Socket socket,String username){
        try{
            this.socket = socket;
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.out = new PrintWriter(socket.getOutputStream(), true);
            this.username = username;
            out.println(username);
        }catch(IOException e){
            // catch
        }
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