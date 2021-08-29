import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente extends Thread{
    private Socket clienteSocket;
    private PrintWriter out;
    private BufferedReader input;


    public void conectar(String IP, Integer port) throws IOException {
        clienteSocket = new Socket(IP, port);
        out = new PrintWriter(clienteSocket.getOutputStream(), true);
        input = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));

    }

    public void recibir_mensaje() throws IOException{
        while (true) {
            String monto = input.readLine();
            int resultado = calcular(monto);
            out.println(resultado);
        }
    }

    public static int calcular(String datos) {
        String Monto;
        String Peso;
        String Impuesto;
        String[] Montos;
        Montos= datos.split("-");
        Monto=Montos[0];
        Peso = Montos[1];
        Impuesto = Montos[2];

        return Integer.parseInt(Monto)*Integer.parseInt(Peso)*Integer.parseInt(Impuesto);

    }

    public String calcular_monto(String Monto) throws IOException{
        out.println(Monto);
        String answer = input.readLine();
        System.out.println(answer);
        return answer;
    }
    public void endsession() throws IOException{
        input.close();
        out.close();
        clienteSocket.close();
        System.out.println("FINALIZÓOOO");
    }
}