import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/** Cliente el cual se comunica por medio de Sockets con la classe Server
 * @author Daniel Castro
 */

public class Cliente implements Runnable{
    private Socket clienteSocket;
    private PrintWriter out;
    private BufferedReader input;

    /**
     * Realiza la conección hacia el servidor por medio de sockets
     * @param IP
     * @param port
     * @throws IOException
     */
    public void conectar(String IP, Integer port) throws IOException {
        clienteSocket = new Socket(IP, port);
        out = new PrintWriter(clienteSocket.getOutputStream(), true);
        input = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));

    }

    /**
     * Calcula el monto del producto a partir de un código generado en la clase interfaz que
     * incluye el monto, el peso y el impuesto
     * @param datos
     * @return el monto ya calculado
     */

    public static float calcular(String datos) {
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

    /**
     * Envía la información ingresada por el usuario por medio del
     * socket y recibe el monto ya calculado
     * @param Monto
     * @return
     * @throws IOException
     */
    public String calcular_monto(String Monto) throws IOException{
        out.println(Monto);
        String answer = input.readLine();
        System.out.println(answer);
        return answer;
    }

    /**
     * Intenta recibir mensajes provenientes del otro cliente
     * Método run no se utiliza debido a que no se pudo crear el Thread correctamente
     */

    public void run(){  //
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
