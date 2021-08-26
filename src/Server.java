import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private ServerSocket servidor;
    private final ArrayList<Nuevosclientes> lista_clientes;

    public Server(){
        lista_clientes = new ArrayList<Nuevosclientes>();
    }

    public void iniciar(int puerto) throws IOException {
        servidor = new ServerSocket(puerto);
        System.out.println("ESTAMOS EN EL PUERTOOOO");

        while (true){
            Socket clienteS = servidor.accept();
            Nuevosclientes nuevosclientes = new Nuevosclientes(clienteS, lista_clientes);
            nuevosclientes.start();
            System.out.println("SE CONECTO UN SAPO");
        }
    }


    public static void main(String[] args) {
        Server servidor = new Server();
            try {
                servidor.iniciar(12321);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

}
