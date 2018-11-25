package buildings.net.client;

import buildings.interfaces.Building;
import buildings.utils.Buildings;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class SerialClient {
    public static void main(String[] args) {
        String host = "127.0.0.1";
        int port = 6666;
        try (
                Socket socket = new Socket(host, port);
                Scanner typesScan = new Scanner(new File("types.txt"));
                Scanner buildingsScan = new Scanner(new File("buildings.txt"));
                FileWriter fileWriter = new FileWriter("output.txt");
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ) {
            while (buildingsScan.hasNext()) {
                Building building = Buildings.readBuilding(buildingsScan);
                out.writeUTF(typesScan.nextLine());
                Buildings.serializeBuilding(building, out);
                out.flush();
                String cost = null;
                Object obj = null;
                if ((obj = in.readObject()) != null) {
                    if(obj instanceof Exception){
                        System.out.println("This building is arrested!");
                    } else {
                        cost = String.valueOf(obj);
                        System.out.println("Client received cost. It's " + cost + "$!");
                        fileWriter.write(cost + "\n");
                        fileWriter.flush();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
