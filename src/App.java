import java.io.IOException;

public class App {
    public static void main (String [] args){
        Cliente cliente = new Cliente();
        try{
            cliente.conectar("192.168.0.107",8080);
            String answer = cliente.enviarmensaje("HOLA DESDE EL CLIENTE");
            System.out.println("servidor"+ answer);
            cliente.endsession();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
