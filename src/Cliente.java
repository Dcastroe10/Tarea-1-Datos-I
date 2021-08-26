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
        //iniciar_socket(IP,port);
        clienteSocket = new Socket(IP, port);
        out = new PrintWriter(clienteSocket.getOutputStream(), true);
        input = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));
    }

    public String calcular_monto(String Valor,String Peso, String Impuesto) throws IOException {
        out.println(Valor);
        //String answer = input.readLine();
        //return input.readLine();
        return Valor;
    }
    ///public void endsession() throws IOException{
    // out.close();
    //input.close();
    //clienteSocket.close();
    //System.out.println("FINALIZOOO");
    //}
}
