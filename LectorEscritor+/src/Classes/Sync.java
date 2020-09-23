package Classes;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.locks.ReentrantLock;

public class Sync extends ReentrantLock {

    private static int lectores = 0;
    private static int escritor = 0;

    public synchronized void Write(JLabel lbl3, int id, int VALOR, JLabel GlobalLabel) throws InterruptedException{

        while (this.lectores != 0) {
            wait();
        }
        Block();
        escritor++;
        lbl3.setText("Escritor: " + id + " valor: " + VALOR + " Escribiendo");
        lbl3.setForeground(Color.BLACK);

        Frame.VariableGlobalValor = VALOR;
        GlobalLabel.setText("Valor: " + VALOR);
        escritor--;
        if (escritor == 0)
            notifyAll();
        UnBlock();
    }

//    public static void Read(JLabel LabelPrrona, int id, int VALOR, JLabel GlobalLabel){ }

    public void Block() {
        this.lock();
    }

    public void UnBlock() {
        this.unlock();
    }

    public synchronized int ReadStart() throws InterruptedException{
        while (this.escritor != 0) {
            wait();
        }
        this.lectores++;
        int ret = Frame.VariableGlobalValor;
        this.ReadStop();
        return ret;
        //return Frame.VariableGlobalValor;
        //System.out.println(this.lectores);
    }
    public synchronized void ReadStop(){
        this.lectores--;
        if (this.lectores == 0)
            notifyAll();
        //System.out.println(this.lectores);
    }

    public static boolean IsBlock(){
        if ( Frame.permissions.availablePermits() >= 1)
            return true;
        else
            return false;
    }


}
