package buildings.net.server.parallel;

import buildings.exceptions.BuildingUnderArrestException;
import buildings.interfaces.Building;
import buildings.utils.Buildings;

import java.io.*;
import java.net.Socket;
import java.util.Random;

public class BinaryServerThread implements Runnable {
    private Socket socket;

    public BinaryServerThread(Socket socket) {
        this.socket = socket;
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

    @Override
    public void run() {
        int port = 6666;
        String type;
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true)
        ) {
            while ((type = reader.readLine()) != null) {
                String input = reader.readLine();
                Building building = Buildings.parseBuilding(input);
                out.println(calculateCost(building, type));
            }
        } catch (IOException e) {
        }
    }
}
