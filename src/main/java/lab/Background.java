package lab;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Background implements Visual{
    Background(int width, int height){
    }
    private void midline(Canvas canvas){
        GraphicsContext GC = canvas.getGraphicsContext2D();
        for(int i = 0; i < 20; i++){
            GC.fillRect(295, 20 * i, 10, 10);
        }
    }
    @Override
    public void draw(Canvas canvas){
        GraphicsContext GC = canvas.getGraphicsContext2D();
        GC.setFill(Color.BLACK);
        GC.fillRect(0, 0, 600, 400);
        GC.setFill(Color.WHITE);
        midline(canvas);
    }

}
