import javafx.scene.shape.Circle;
import javafx.scene.paint.*;
import java.util.Random;

public class Malo extends Circle
{
    private int velocidadY;
    private static int RADIO = 20;
    private static int DIAMENTRO = 40;
    
    public Malo(int anchoEscena, int altoEscena)
    {
        super();
        setFill(Color.BLUE);
        setRadius(RADIO);
        Random aleatorio = new Random();
        velocidadY = aleatorio.nextInt(velocidadY + 7);
        while(velocidadY < 4){
            velocidadY = velocidadY + 3;
        }
        int posXmalo = aleatorio.nextInt(anchoEscena - DIAMENTRO);
        int posYmalo = aleatorio.nextInt(altoEscena/4);
        setTranslateX(posXmalo);
        setTranslateY(posYmalo);
    }

    public void mover()
    {
        setTranslateY(getTranslateY() + velocidadY);
    }
}
