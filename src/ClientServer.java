import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientServer {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 6013);

            InputStream inputStream = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(inputStream)
            );

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
            socket.close();
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }
}
