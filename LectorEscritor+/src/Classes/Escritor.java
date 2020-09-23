package Classes;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Escritor extends Thread{

    private int id;
    private int VALOR;
    private JLabel Lbl;
    private JLabel GlobalLabel;
    private boolean running = true;

    private Random random = new Random();
    private int MAXSleep = 5000;

    Sync syncInstance = new Sync();

    public Escritor(int id, JLabel Label, JLabel GlobalLabel, int valor){
        this.id = id;
        this.Lbl = Label;
        this.VALOR = valor;
        this.GlobalLabel = GlobalLabel;

        Lbl.setText("Escritor: " + id + " valor: " + this.VALOR);
        Lbl.setForeground(Color.BLACK);
    }

    public void terminate() {
        running = false;
    }

    public void run() {
        while(running){
            try {

                this.Sleep();

                this.Write();

                Thread.sleep( random.nextInt(MAXSleep) );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Lbl.setText("Muerto");
        Lbl.setForeground(Color.RED);
    }

    private void Sleep() throws InterruptedException{
        Thread.sleep( random.nextInt(MAXSleep) + 2000 );
    }

    private void Write() throws InterruptedException{

        if (Frame.permissions.tryAcquire()){
            System.out.println("tengo permiso: " + this.id);

            //syncInstance.Block();

            //Sync.Write(this.LabelPrrona, this.id, this.VALOR, this.GlobalLabel);
            syncInstance.Write(this.Lbl, this.id, this.VALOR, this.GlobalLabel);
            Thread.sleep( random.nextInt(MAXSleep) );

            Frame.permissions.release();
        }

        Lbl.setText("Escritor: " + this.id + " valor: " + this.VALOR );
        Lbl.setForeground(Color.BLACK);

    }
}
