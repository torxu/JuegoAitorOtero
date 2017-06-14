import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.*;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.scene.input.KeyCode;
import javafx.scene.control.Label;
import java.util.TimerTask;
import java.util.Timer;
import java.util.ArrayList;
import javafx.scene.shape.Shape;

public class Base extends Application
{
    public final static int ALTO_VENTANA = 1000;
    public final static int ANCHO_VENTANA = 500;
    public final static int MALOS = 20;
    private ArrayList<Malo> malos;

    public static void main(String[] args)
    {
        launch(args);
    }

    public void start(Stage escenario)
    {
        //creamos la escena
        escenario.setTitle("juego Aitor");
        Group contenedor = new Group();
        Scene escena = new Scene(contenedor, ANCHO_VENTANA, ALTO_VENTANA);

        //creamos el fondo
        Rectangle fondo = new Rectangle();
        fondo.setWidth(500);
        fondo.setHeight(1000);
        fondo.setTranslateX(0);
        fondo.setTranslateY(0);
        fondo.setFill(Color.BLACK); 
        contenedor.getChildren().add(fondo);

        //añadimos un personaje
        Pj personaje = new Pj(500);
        contenedor.getChildren().add(personaje);
        
        //añadimos los malos
        malos = new ArrayList<>();
        int c = 0;
        while(c < MALOS){
                Malo malo = new Malo(ANCHO_VENTANA, ALTO_VENTANA);
                malos.add(malo);
                contenedor.getChildren().add(malo);
                c++;                        
        }



        escenario.setScene(escena);
        escenario.show();

        Timeline timeline = new Timeline();
        KeyFrame keyframe = new KeyFrame(Duration.seconds(0.01), event -> {
                    personaje.mover();
                    for(Malo malos: malos){
                        malos.mover();                        
                    }
                    for(int a = 0; a < malos.size(); a++){
                        if(personaje.getBoundsInParent().intersects(malos.get(a).getBoundsInParent())){
                            Label perder = new Label("Has perdido el juego");
                            perder.setTranslateX(200);
                            perder.setTranslateY(escena.getHeight() / 2);
                            perder.setTextFill(Color.WHITE);
                            perder.setScaleX(1.5);
                            perder.setScaleY(1.5);
                            contenedor.getChildren().add(perder);
                            timeline.stop();
                        }
                       
                        for(int b = 0; b < malos.size(); b++){
                            int ganar = 0;
                            if(malos.get(a).getBoundsInParent().getMaxY() == personaje.getBoundsInParent().getMaxX()){                           
                                ganar++;
                            }         
                            if(ganar == 19){
                                Label ganar2 = new Label("Has ganado!!");
                                Label ganar1 = new Label("pero no te creas importante");
                                ganar2.setTranslateX(200);
                                ganar2.setTranslateY(escena.getHeight() / 2);
                                ganar2.setTextFill(Color.WHITE);
                                ganar2.setScaleX(1.5);
                                ganar2.setScaleY(1.5);
                                contenedor.getChildren().add(ganar2);
                                ganar1.setTranslateX(180);
                                ganar1.setTranslateY(escena.getHeight() / 2 + 10);
                                ganar1.setTextFill(Color.WHITE);
                                ganar1.setScaleX(1.5);
                                ganar1.setScaleY(1.5);
                                contenedor.getChildren().add(ganar1);
                                timeline.stop();
                            }
                        }
                    }
                });
                
        timeline.getKeyFrames().add(keyframe);
        
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        
        escena.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.RIGHT) {
                    personaje.cambiarDireccionALaDerecha();
                }
                else if (event.getCode() == KeyCode.LEFT) {
                    personaje.cambiarDireccionALaIzquierda();
                }
            });
    }
}
