import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Interfaz extends Thread {

    public static void main(String[] args) {
        JFrame window = new JFrame();
        JButton botoncalcular = new JButton("Calcular");
        JButton ConectarB = new JButton("conectar");
        JTextField Valor = new JTextField();
        JTextField Peso = new JTextField();
        JTextField Impuestos = new JTextField();
        JTextArea calculos = new JTextArea();
        Cliente cliente = new Cliente();
        ConectarB.setBounds(100,100,200,30);
        calculos.setBounds(1,1,450,300);
        Valor.setBounds(5, 420, 70, 35);
        Peso.setBounds(95, 420, 70, 35);
        Impuestos.setBounds(185, 420, 70, 35);
        botoncalcular.setBounds(275, 420, 80, 35);
        window.add(botoncalcular);
        window.add(ConectarB);
        window.add(Valor);
        window.add(Peso);
        window.add(Impuestos);
        window.add(calculos);
        window.setSize(380, 500);
        window.setResizable(false);
        window.setLayout(null);
        window.setVisible(true);
        ConectarB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    cliente.conectar("127.0.0.1",12321);
                }
                catch (IOException w){
                    w.printStackTrace();
                }
            }
        });

        botoncalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Code;
                try {
                    Code = Valor.getText()+"-"+Peso.getText()+"-"+Impuestos.getText();
                    cliente.calcular_monto(Code);
                }  catch (IOException a) {
                    a.printStackTrace();
                }

            }
        });
    }
}