import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Interfaz {

    public static void main(String[] args) {
        JFrame window = new JFrame();
        JButton botoncalcular = new JButton("Calcular");
        JTextField datos = new JTextField();
        JTextArea calculos = new JTextArea();
        Cliente cliente = new Cliente();
        calculos.setBounds(10,2,350,300);
        datos.setBounds(10, 420, 230, 35);
        botoncalcular.setBounds(250, 420, 80, 35);
        window.add(botoncalcular);
        window.add(datos);
        window.add(calculos);
        window.setSize(350, 500);
        window.setResizable(false);
        window.setLayout(null);
        window.setVisible(true);
        botoncalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String prueba;
                prueba = datos.getText();
                try {
                    cliente.conectar("127.0.0.1",12321);
                    String msg = cliente.enviarmensaje(prueba);
                    //System.out.println("Server: "+msg);
                    calculos.setText(msg);
                    //cliente.stopConnection();
                }  catch (IOException a) {
                    a.printStackTrace();
                }
                //System.out.println(prueba);
                //calculos.setText(prueba);

            }
        });
    }
}