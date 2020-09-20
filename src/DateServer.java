import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class DateServer {
    public static void main(String[] args) {
        try {
            int PORT = 6013;
            ServerSocket socket = new ServerSocket(PORT);
            System.out.println("Listening on port " + PORT + "...");

            while (true) {
                Socket client = socket.accept();

                PrintWriter pout = new PrintWriter(client.getOutputStream(), true) ;
                pout.println("The Date is: " + new Date().toString());

                client.close();
            }
        } catch (Exception ex) {
            System.err.println(ex);
        } finally {
            System.out.println("Socket closed.");
        }
    }
}
