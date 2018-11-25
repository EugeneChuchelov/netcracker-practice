package buildings.net.server.parallel;

import java.io.IOException;
import java.net.ServerSocket;

public class ParallelSerialServer {
    public static void main(String[] args) {
        int port = 6666;
        try (ServerSocket server = new ServerSocket(port)
        ) {
            while (!server.isClosed()) {
                Thread client = new Thread(new SerialServerThread(server.accept()));
                client.start();
                System.out.println("accepted");
            }
        } catch (IOException e) {
        }
    }
}
