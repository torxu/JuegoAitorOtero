import javafx.scene.shape.Rectangle;
import javafx.scene.paint.*;


public class Pj extends Rectangle
{
    private int velocidadX;
    private int limiteDesplazamientoX;

    /**
     * Constructor for objects of class Plataforma
     */
    public Pj(int limiteEnX)
    {
        super();
        setWidth(25);
        setHeight(25);
        setTranslateX(225);
        setTranslateY(800);
        setFill(Color.RED);  
        velocidadX = 1;
        limiteDesplazamientoX = limiteEnX;
    }
    
    
    public void cambiarDireccionALaDerecha() 
    {
        if (getBoundsInParent().getMaxX() != limiteDesplazamientoX) {
           velocidadX = 1;
        }
    }

    
    public void cambiarDireccionALaIzquierda() 
    {
        if (getBoundsInParent().getMinX() != 0) {
            velocidadX = -1;
        }
    }
    
    
    public void mover()
    {
        setTranslateX(getTranslateX() + velocidadX);
        if (getBoundsInParent().getMinX() == 0 || getBoundsInParent().getMaxX() == limiteDesplazamientoX) {
            velocidadX = 0;
        }
        
    }
  
    
}