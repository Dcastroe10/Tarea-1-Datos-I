import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket servidor;
    private Socket clienteSocket;
    private PrintWriter out;
    private BufferedReader input;

    public void recibirmensaje(int puerto) throws IOException{
        System.out.println("esperando");
        servidor = new ServerSocket(puerto);
        clienteSocket = servidor.accept();
        out = new PrintWriter(clienteSocket.getOutputStream(), true);
        input = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));
        String answer = input.readLine();
        ////logicaaaa

        System.out.println("Cliente:"+answer);
        out.println("Hola desde servidor");
    }
    public void finalizar() throws IOException{
        input.close();
        out.close();
        clienteSocket.close();
        servidor.close();
    }
    public static void main(String[] args) {
        Server servidor = new Server();
            try {
                servidor.recibirmensaje(12321);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

}
