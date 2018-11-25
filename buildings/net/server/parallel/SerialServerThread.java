package buildings.net.server.parallel;

import buildings.exceptions.BuildingUnderArrestException;
import buildings.interfaces.Building;
import buildings.utils.Buildings;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class SerialServerThread implements Runnable {
    Socket socket;

    public SerialServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        System.out.println("Started");
        int port = 6666;
        String type = null;
        try (
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())
        ) {
            while (!socket.isClosed()) {
                System.out.println("Connected");
                type = in.readUTF();
                System.out.println("Read type");
                out.writeObject(calculateCost(Buildings.deserializeBuilding(in), type));
                System.out.println("Sent cost");
            }
        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
        }
    }
    private static Object calculateCost(Building building, String type) {
        System.out.println("Read building");
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
