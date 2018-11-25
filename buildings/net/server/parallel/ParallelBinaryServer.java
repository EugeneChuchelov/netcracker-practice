package buildings.net.server.parallel;

import java.io.IOException;
import java.net.ServerSocket;

public class ParallelBinaryServer {
    public static void main(String[] args) {
        int port = 6666;
        try (ServerSocket server = new ServerSocket(port)
        ) {
            while (!server.isClosed()) {
                Thread client = new Thread(new BinaryServerThread(server.accept()));
                client.start();
            }
        } catch (IOException e) {
        }
    }
}
