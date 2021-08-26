import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class Nuevosclientes extends Thread {
    private final Socket clienteSocket;
    private final ArrayList<Nuevosclientes> lista_clientes;
    private PrintWriter out;
    private BufferedReader input;

    public Nuevosclientes(Socket socket, ArrayList<Nuevosclientes> lista_clientes){
        clienteSocket = socket;
        this.lista_clientes = lista_clientes;
    }

    private void init() throws IOException{
        input = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));
        out = new PrintWriter(clienteSocket.getOutputStream(), true);
    }

    private void enviar_clientes(int monto){
            for (Nuevosclientes user : lista_clientes){
                    user.out.println(monto);
            }
        }

    public void disconnect() throws IOException{
        input.close();
        out.close();
        clienteSocket.close();
    }
@Override
    public void run(){
        try{
            init();
            while (true){
                String mensaje = input.readLine();
                if (mensaje.equalsIgnoreCase("Finish")){break;}
                System.out.println("ESTE SAPO: "+ mensaje);
                //out.println(Integer.parseInt( mensaje) *2);
                enviar_clientes(23);
        }
            disconnect();
        }
        catch (IOException e){
            e.printStackTrace();
        }
}
}

