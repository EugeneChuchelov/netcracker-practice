package buildings.net.client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class BinaryClient {
    public static void main(String[] args) {
        String host = "127.0.0.1";
        int port = 6666;
        try (
                Socket socket = new Socket(host, port);
                Scanner typesScan = new Scanner(new File("types.txt"));
                Scanner buildingsScan = new Scanner(new File("buildings.txt"));
                FileWriter fileWriter = new FileWriter("output.txt");
                PrintWriter serverWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
                BufferedReader serverReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {
            while (buildingsScan.hasNext()) {
                serverWriter.println(typesScan.nextLine());
                serverWriter.println(buildingsScan.nextLine());
                serverWriter.flush();
                String cost = null;
                if ((cost = serverReader.readLine()) != null) {
                    System.out.println("Client received cost. It's " + cost + "$!");
                    fileWriter.write(cost + "\n");
                    fileWriter.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
