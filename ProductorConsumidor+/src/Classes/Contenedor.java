package Classes;
import java.util.Random;

public class Contenedor {
    private static int CantidadAlmacen = 50;
    private static boolean Contenedorlleno = Boolean.FALSE;

    public int GetCantidadInt() {
        return CantidadAlmacen;
    }

    public synchronized int GetPutSync(int place, int valor, int id){
        if (place == 1){
            return GetCantidad(id);
        }
        else{
            return PutCatidad(valor, id);
        }
    }

    public synchronized int GetCantidad(int id){
        int darContenido=0;
        while(!Contenedorlleno){
            try
            {
                if (CantidadAlmacen < 1){
                    System.out.println("Consumidor "+ id +"  no hay nada para consumir \n");
                    wait();
                }else{
                    Contenedorlleno = Boolean.TRUE;
                }

            }
            catch (InterruptedException e)
            {
                System.err.println("Contenedor: Error en GetCantidad: " + e.getMessage());
            }
        }

        Contenedorlleno = Boolean.FALSE;
        if(CantidadAlmacen>0){
            notifyAll();
            darContenido = (int) (Math.random() * (CantidadAlmacen)) + 1;
            CantidadAlmacen = CantidadAlmacen - darContenido;
            System.out.println("Consumidor "+ id +" Consumio => " + darContenido );
            System.out.println("Cantidad Almacen consu "+ id +" => " + CantidadAlmacen + "\n" );
        }
        return CantidadAlmacen;
    }



    public synchronized int PutCatidad(int valor, int id){
        while (Contenedorlleno)
        {
            try
            {
                if(CantidadAlmacen >= 100){
                    System.out.println("Productor" + id + " no tiene que producir: " + CantidadAlmacen + "\n" );
                    wait();
                }else{
                    Contenedorlleno = Boolean.FALSE;
                    PutCatidad(valor, id);
                }
            }
            catch (InterruptedException e)
            {
                System.err.println("Contenedor: Error en PutCatidad => " + e.getMessage());
            }
        }

        if (CantidadAlmacen + valor > 100)
        {
            System.out.println("VALOR VALIDO: " + (100 - CantidadAlmacen));
            if( 100 - CantidadAlmacen == 0) // si 100 - 100 ponemos 0
            {
                PutCatidad(0, id);
                Contenedorlleno = Boolean.TRUE;
                System.out.println("Prouctor "+ id +" Produjo => " + valor );
                System.out.println("Cantidad Almacen: " + id + " => " + CantidadAlmacen + "\n" );
            }else{
                int ponerContenido=(int) (Math.random() * (100 - CantidadAlmacen)) + 1;
                PutCatidad(ponerContenido, id);
            }
        }else if (CantidadAlmacen + valor == 100){
            CantidadAlmacen = valor + CantidadAlmacen;
            Contenedorlleno = Boolean.TRUE;
            System.out.println("Prouctor "+ id +" Produjo => " + valor );
            System.out.println("Cantidad Almacen: " + id + " => " + CantidadAlmacen + "\n" );
            notifyAll();
        }else{
            CantidadAlmacen = valor + CantidadAlmacen;
            Contenedorlleno = Boolean.TRUE;
            System.out.println("Prouctor "+ id +" Produjo => " + valor );
            System.out.println("Cantidad Almacen: " + id + " => " + CantidadAlmacen + "\n" );
            notifyAll();
        }
        return valor;
    }

}