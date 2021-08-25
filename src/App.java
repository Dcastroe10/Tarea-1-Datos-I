import java.io.IOException;

public class App {
    public static void main (String [] args){
        Cliente client = new Cliente();

        try{
            client.conectar ("127.0.0.1",8080);
            String msg = client.enviarmensaje("Hello from client!");
            System.out.println("Server: "+msg);
            client.endsession();
        }

        catch (IOException e){
            e.printStackTrace();
        }
    }
}
