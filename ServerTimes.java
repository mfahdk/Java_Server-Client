import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class ServerTimes {
    private static Long startTime = System.currentTimeMillis();                     //Calculate startTime of server
    private static LinkedList<String> connectedClients = new LinkedList<>();        //List to store clients

    public static void main(String[] args) throws IOException{
        ServerSocket server = new ServerSocket(7772);                           //Creating server socket
        System.out.println("Server connected\n");   

        while(true){
            Socket clientSocket = server.accept();                                  //Accepts all clients
            Thread clientThread = new Thread(() -> {
                try {
                    handleClient(clientSocket);
                } catch (IOException e) {
                    System.err.println("Error handling client: " + e.getMessage());
                }
            });
            clientThread.start();
        }
    }

    public static synchronized void handleClient(Socket clientSocket) throws IOException{
        long connectedTime = System.currentTimeMillis();                  
        BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream());

        printWriter.println(connectedClients);                                      //sends array of clients to ClientTimes
        printWriter.flush();

        String client = reader.readLine();                                          //Reads client name sent from ClienTimes   
        connectedClients.add(client);

        System.out.println("Added: "+client);                                       //Client has been added
        printWriter.println("Server has been running for "+((connectedTime-startTime)/1000)+" seconds");        //Calculates time taken for clients
        printWriter.flush();
    }
}
