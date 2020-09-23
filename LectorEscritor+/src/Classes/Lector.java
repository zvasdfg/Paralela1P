package Classes;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Lector extends Thread{
    private int id;
    private int ValorLeido;
    private JLabel lbl2;
    private boolean running = true;

    private Random random = new Random();
    private int MAXSleep = 2000;

    Sync syncInstance = new Sync();

    public Lector(int id, JLabel Label){
        this.id = id;
        this.lbl2 = Label;
    }

    public void terminate() {
        running = false;
    }

    public void run() {
        while(running){
            try {

                if ( Sync.IsBlock() )
                    this.leer();
                //else
                 //   this.wait();

                Thread.sleep( random.nextInt(MAXSleep) );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        lbl2.setText("Muerto");
        lbl2.setForeground(Color.RED);
    }

    private void leer() throws InterruptedException{

        try {
            ValorLeido = syncInstance.ReadStart();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("tengo lectura: " + this.id);


        lbl2.setText("Lector: " + id + " Leyo: " + ValorLeido);
        lbl2.setForeground(Color.BLACK);
    }

}
