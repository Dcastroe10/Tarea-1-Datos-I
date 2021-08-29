import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread{
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
            System.out.println("waitinnnnnnnnnn");
            String monto = input.readLine();
            float resultado = calcular(monto);
            out.println(resultado);
            System.out.println("sigo waitin");
        }
    }

    public String calcular_monto(String Code) throws IOException{
        out.println(Code);
        String Monto = input.readLine();
        System.out.println(Monto);
        return Monto;

    }

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

    public static void main(String[] args) throws IOException{
        Server servidor = new Server();
        JFrame window = new JFrame("Cliente/Servidor");
        JButton botoncalcular = new JButton("Calcular");
        JLabel value = new JLabel("Valor");
        JLabel weight = new JLabel("Peso");
        JLabel tax = new JLabel("Impuesto");
        JLabel porcentaje = new JLabel("%");
        JTextField Valor = new JTextField();
        JTextField Peso = new JTextField();
        JTextField Impuestos = new JTextField();
        JTextArea calculos = new JTextArea();
        calculos.setBounds(1,1,450,100);
        Valor.setBounds(5, 120, 70, 35);
        Peso.setBounds(95, 120, 70, 35);
        Impuestos.setBounds(185, 120, 70, 35);
        botoncalcular.setBounds(275, 120, 80, 35);
        value.setBounds(5,95,70,35);
        weight.setBounds(95,95,70,35);
        tax.setBounds(185,95,70,35);
        porcentaje.setBounds(255,120,70,35);
        window.add(botoncalcular);
        window.add(Valor);
        window.add(Peso);
        window.add(Impuestos);
        window.add(calculos);
        window.add(value);
        window.add(weight);
        window.add(tax);
        window.add(porcentaje);
        window.setSize(380, 200);
        window.setResizable(false);
        window.setLayout(null);
        window.setVisible(true);
        servidor.recibirmensaje(12321);
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
    }}