package buildings.net.server.sequential;

import buildings.exceptions.BuildingUnderArrestException;
import buildings.interfaces.Building;
import buildings.utils.Buildings;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class BinaryServer {
    public static void main(String[] args) {
        int port = 6666;
        String type;
        try (ServerSocket server = new ServerSocket(port);
             Socket socket = server.accept();
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
        ) {
            while ((type = reader.readLine()) != null) {
                String input = reader.readLine();
                Building building = Buildings.parseBuilding(input);
                out.println(calculateCost(building, type));
            }
        } catch (IOException e) {
        }
    }

    private static String calculateCost(Building building, String type) {
        try {
            Random rand = new Random();
            if (rand.nextInt(100) <= 10) {
                throw new BuildingUnderArrestException("This building is arrested!");
            }
        } catch (BuildingUnderArrestException e) {
            return "This building is arrested!";
        }

        float multiplier = 0;
        switch (type) {
            case "dwelling":
                multiplier = 1000;
                break;
            case "office":
                multiplier = 1500;
                break;
            case "hotel":
                multiplier = 2000;
                break;
        }
        return String.valueOf(building.getAreaTotal() * multiplier);
    }
}
