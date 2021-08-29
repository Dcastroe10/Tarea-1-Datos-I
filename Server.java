import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private ServerSocket servidor;
    private Socket clienteSocket;
    private PrintWriter out;
    private BufferedReader input;


    public void recibirmensaje(int puerto) throws IOException {
        System.out.println("RECIBIR MENSAJE");
        servidor = new ServerSocket(puerto);
        clienteSocket = servidor.accept();
        out = new PrintWriter(clienteSocket.getOutputStream(), true);
        input = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));
        while (true) {
            String monto = input.readLine();
            int resultado = calcular(monto);
            out.println(resultado);
        }
    }

    public String calcular_monto(String Code) throws IOException{
        out.println(Code);
        System.out.println(Code);
        String Monto = input.readLine();
        System.out.println(Monto);
        return Monto;

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

    public void finalizar() throws IOException{
        input.close();
        out.close();
        clienteSocket.close();
        servidor.close();
    }

    public static void main(String[] args) {
        Server servidor = new Server();
        JFrame window = new JFrame();
        JButton botoncalcular = new JButton("Calcular");
        JTextField Valor = new JTextField();
        JTextField Peso = new JTextField();
        JTextField Impuestos = new JTextField();
        JTextArea calculos = new JTextArea();
        calculos.setBounds(1,1,450,300);
        Valor.setBounds(5, 420, 70, 35);
        Peso.setBounds(95, 420, 70, 35);
        Impuestos.setBounds(185, 420, 70, 35);
        botoncalcular.setBounds(275, 420, 80, 35);
        window.add(botoncalcular);
        window.add(Valor);
        window.add(Peso);
        window.add(Impuestos);
        window.add(calculos);
        window.setSize(380, 500);
        window.setResizable(false);
        window.setLayout(null);
        window.setVisible(true);
        botoncalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Code;
                try {
                    Code = Valor.getText()+"-"+Peso.getText()+"-"+Impuestos.getText();
                    servidor.calcular_monto(Code);
                } catch (IOException w){
                    w.printStackTrace();
                }

            }
        });
        try {
            servidor.recibirmensaje(12321);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }}

