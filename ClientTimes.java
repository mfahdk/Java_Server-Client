import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientTimes {

    public static void main(String[] args) throws IOException, InterruptedException{
        Socket clientSocket = new Socket("localhost",7772);                     //Creates client

        Scanner scan = new Scanner(System.in);                                            //Used for input
        BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream());

        String clients = reader.readLine();                                     //Gets list of clients and turns into string
        String text = String.join(" ", clients);
        String[] words = text.split(" ");
        for (String word : words){
            String client  = word.replaceAll("\\[|\\]|,", "");
            System.out.println(client);
        }

        String clientName = scan.nextLine();                                           //sends client name to ServerTimes
        printWriter.println(clientName);
        printWriter.flush();

        String time = reader.readLine();                                        //Gets back time taken for clients
        System.out.println(time); 
        scan.close();
        clientSocket.close();    
    }
}