package buildings.net.server.sequential;

import buildings.exceptions.BuildingUnderArrestException;
import buildings.interfaces.Building;
import buildings.utils.Buildings;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class SerialServer {
    public static void main(String[] args) {
        int port = 6666;
        String type;
        try (ServerSocket server = new ServerSocket(port);
             Socket socket = server.accept();
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())
        ) {
            while (!server.isClosed()) {
                type = in.readUTF();
                out.writeObject(calculateCost(Buildings.deserializeBuilding(in), type));
            }
        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
        }
    }
    private static Object calculateCost(Building building, String type) {
        Random rand = new Random();
        if(rand.nextInt(100) <= 10){
            return new BuildingUnderArrestException("This building is arrested!");
        }
        float multiplier = 0;
        switch (type){
            case "dwelling": multiplier = 1000;
                break;
            case "office" : multiplier=1500;
                break;
            case "hotel" : multiplier = 2000;
                break;
        }
        return building.getAreaTotal() * multiplier;
    }
}
