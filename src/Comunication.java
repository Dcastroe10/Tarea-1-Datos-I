import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Comunication implements Runnable {
    private final BufferedReader input;

    public Comunication(InputStream ALGO){
        input= new BufferedReader(new InputStreamReader(ALGO));
    }

@Override
    public void run(){
        try{
            while (true){
                String datos = input.readLine();
                System.out.println(datos);

            }
        }catch (IOException e){
            e.printStackTrace();
        }
}



}