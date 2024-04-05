import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoClient {
    public static void main(String[] args) {
        try {
            System.out.println("client started");
            Socket soc = new Socket("localhost",9806);
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter out = new PrintWriter(soc.getOutputStream(),true);
            BufferedReader in = new BufferedReader(new InputStreamReader(soc.getInputStream()));

            System.out.println("Enter a username: ");
            String str = userInput.readLine();
            out.println(str);

            System.out.println("Username set to: "+str);

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}