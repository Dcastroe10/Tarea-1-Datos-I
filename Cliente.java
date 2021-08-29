import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente implements Runnable{
    private Socket clienteSocket;
    private PrintWriter out;
    private BufferedReader input;


    public void conectar(String IP, Integer port) throws IOException {
        clienteSocket = new Socket(IP, port);
        out = new PrintWriter(clienteSocket.getOutputStream(), true);
        input = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));

    }


    public static float calcular(String datos) {
        System.out.println("CALCULANDO MONTO");
        String Monto;
        String Peso;
        String Impuesto;
        String[] Montos;
        double result;
        Montos= datos.split("-");
        Monto=Montos[0];
        Peso = Montos[1];
        Impuesto = Montos[2];
        result = (Float.parseFloat(Monto)*(Float.parseFloat(Impuesto)/100))+(Float.parseFloat(Peso)*0.15);
        return (float) result;


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

    public void run(){  //Método run no se utiliza debido a que no se pudo crear el Thread correctamente
        try {
            while (true){
                System.out.println("esperando mensaje");
                String monto = input.readLine();
                System.out.println(monto);
                float resultado = calcular(monto);
                out.println(resultado);
            }
        }catch (IOException w){
            w.printStackTrace();
        }
    }
    }
