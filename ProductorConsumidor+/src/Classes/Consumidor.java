package Classes;

public class Consumidor extends Thread{
    private Contenedor contenedor;
    private int id;
    private int Dormir = 1000;

    public Consumidor(Contenedor c, int id){
        this.contenedor = c;
        this.id = id;
    }

    public void run(){
        while(Boolean.TRUE)
        {

            contenedor.GetPutSync(1, 0, id);

            Frame FrameInstance = new Frame();
            FrameInstance.bar.setValue( contenedor.GetCantidadInt() );

            try
            {
                Thread.sleep(Dormir);
            }
            catch (InterruptedException e)
            {
                System.err.println("Productor " + id + ": Error en thread: " + e.getMessage());
            }//*/
        }

    }
}