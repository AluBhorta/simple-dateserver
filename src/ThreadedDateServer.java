import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadedDateServer implements Runnable {
    Socket clientSocket;

    public ThreadedDateServer(Socket cSocket) {
        this.clientSocket = cSocket;
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        try {
            int PORT = 6013;
            ServerSocket socket = new ServerSocket(PORT);
            System.out.println("Listening on port " + PORT + "...");

            while (true) {
                Socket client = socket.accept();
                System.out.println("New client connected " + client.toString());

                executorService.execute(new ThreadedDateServer(client));
            }
        } catch (Exception ex) {
            System.err.println(ex);
        } finally {
            System.out.println("Socket closed.");
            executorService.shutdown();
        }
    }

    @Override
    public void run() {
        try {
            PrintWriter pout = new PrintWriter(this.clientSocket.getOutputStream(), true);
            pout.println("The Date is: " + new Date().toString());

            this.clientSocket.close();
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }
}
