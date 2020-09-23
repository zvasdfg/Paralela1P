package Classes;


import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.*;


public class Frame extends JFrame {
    public static JLabel countLabel1 = new JLabel("0");
    public JLabel ConsuLabel = new JLabel("Consumidores");
    public JLabel ProdLabel = new JLabel("Productores");
    public JButton startButton = new JButton("Start");
    public static JProgressBar bar;

    private static SpinnerModel valueConsu =new SpinnerNumberModel(2,1,10,1); //step
    private static JSpinner spinnerConsu = new JSpinner(valueConsu);

    private static SpinnerModel valueProd =new SpinnerNumberModel(2,1,10,1); //step
    private static JSpinner spinnerProd = new JSpinner(valueProd);


    private static int CANTIDADCONSUMIDORES = 0;
    private static int CANTIDADPRODUCTORES = 0;

    private static Thread [] productores;
    private static Thread [] consumidores;
    private Contenedor c;

    public Frame() {}
    public Frame(String title) {
        super(title);

        setLayout(new GridBagLayout());

        countLabel1.setFont(new Font("serif", Font.BOLD, 28));

        GridBagConstraints gc = new GridBagConstraints();

        gc.fill = GridBagConstraints.HORIZONTAL;

        gc.gridx = 0;
        gc.gridy = 0;
        gc.weightx = 1;
        gc.weighty = 1;
        bar = new JProgressBar();
        bar.setValue(0);
        bar.setStringPainted(true);
        add(bar, gc);

        gc.fill = GridBagConstraints.NONE;

        gc.gridx = 0;
        gc.gridy = 1;
        gc.weightx = 1;
        gc.weighty = 1;
        add(ConsuLabel, gc);

        gc.gridx = 1;
        gc.gridy = 1;
        gc.weightx = 1;
        gc.weighty = 1;
        spinnerConsu.setBounds(100,100,50,30);
        add(spinnerConsu, gc);




        gc.gridx = 0;
        gc.gridy = 2;
        gc.weightx = 1;
        gc.weighty = 1;
        add(ProdLabel, gc);

        gc.gridx = 1;
        gc.gridy = 2;
        gc.weightx = 1;
        gc.weighty = 1;
        spinnerProd.setBounds(100,100,50,30);
        add(spinnerProd, gc);


        gc.gridx = 0;
        gc.gridy = 3;
        gc.weightx = 1;
        gc.weighty = 1;
        add(startButton, gc);

        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                start();
            }
        });

        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }


    private void start() {
        c = new Contenedor();

        CANTIDADPRODUCTORES = (Integer) spinnerProd.getValue();
        CANTIDADCONSUMIDORES = (Integer) spinnerConsu.getValue();

        productores = new Thread[CANTIDADPRODUCTORES];
        consumidores = new Thread[CANTIDADCONSUMIDORES];

        for(int i = 0; i < CANTIDADPRODUCTORES; i++)
        {
            productores[i] = new Thread(new Productor(c, i));
            productores[i].start();
        }

        for(int i = 0; i < CANTIDADCONSUMIDORES; i++)
        {
            consumidores[i] = new Thread(new Consumidor(c, i));
            consumidores[i].start();
        }
    }


}