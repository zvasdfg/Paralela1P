package Classes;

//import "../main.*";
//import main.*;


public class Productor extends Thread{
    private Contenedor contenedor;
    private int id;
    private int Espera = 10;
    private int Dormir = 1000;


    public Productor(Contenedor c, int id){
        this.contenedor = c;
        this.id = id;
    }

    public void run(){

        while(Boolean.TRUE)
        {
            int poner = (int) (Math.random() * (100 - contenedor.GetCantidadInt()) ) + 1;
            int puso = contenedor.GetPutSync(2 , poner, id);
            Frame FrameInstance = new Frame();
            FrameInstance.bar.setValue( contenedor.GetCantidadInt() );

            try
            {
                Thread.sleep(Dormir);
                Thread.sleep(Espera);
            }
            catch (InterruptedException e)
            {
                System.err.println("Productor " + id + ": Error en thread: " + e.getMessage());
            }
        }

    }
}