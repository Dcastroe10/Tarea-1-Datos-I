import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente {
    private Socket clienteSocket;
    private PrintWriter out;
    private BufferedReader input;

    public void conectar(String IP, Integer port) throws IOException {
        clienteSocket = new Socket(IP, port);
        out = new PrintWriter(clienteSocket.getOutputStream(), true);
        input = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));
        System.out.println("conectado!!!");
    }
    public String enviarmensaje(String mensaje) throws IOException {
        out.println(mensaje);
        //String answer = input.readLine();
        return input.readLine();
    }
    public void endsession() throws IOException{
        input.close();
        out.close();
        clienteSocket.close();
        System.out.println("FINALIZÓOOO");
    }
}
