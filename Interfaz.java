import javax.swing.*;
import javax.swing.plaf.TableHeaderUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Interfaz extends Thread {

    public static void main(String[] args) throws  IOException{
        JFrame window = new JFrame("Cliente 1");
        JButton botoncalcular = new JButton("Calcular");
        //JButton ConectarB = new JButton("conectar");
        JTextField Valor = new JTextField();
        JTextField Peso = new JTextField();
        JTextField Impuestos = new JTextField();
        JTextArea calculos = new JTextArea();
        JLabel value = new JLabel("Valor");
        JLabel weight = new JLabel("Peso");
        JLabel tax = new JLabel("Impuesto");
        JLabel porcentaje = new JLabel("%");
        Cliente cliente = new Cliente();
        cliente.conectar("127.0.0.1",12321);
        //new Thread(cliente).start(); se crea el Thread que ocasioanba el error en el Cliente
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
        botoncalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Code;
                try {
                    Code = Valor.getText()+"-"+Peso.getText()+"-"+Impuestos.getText();
                    String respuesta = String.valueOf(cliente.calcular_monto(Code));
                    calculos.setText(respuesta);
                }  catch (IOException a) {
                    a.printStackTrace();
                }

            }
        });
    }
}