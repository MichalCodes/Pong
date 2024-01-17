package lab;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class Bat implements Simulable{
    private int posX;
    private int posY;

    Bat(int x, int y){
        this.posY = y;
        this.posX = x;
    }
    @Override
    public void draw(Canvas canvas, int x, int y){
        GraphicsContext GC = canvas.getGraphicsContext2D();
        GC.fillRect(posX, y, 10, 50);
    }
    @Override
    public int getX() {
        return posX;
    }
    @Override
    public int getY() {
        return posY;
    }
    @Override
    public void decY(int i) {
        this.posY -= i;
    }
    @Override
    public void incY(int i) {
        this.posY +=i;
    }
    @Override
    public void setPos(int y) {
        this.posY = y;
    }
}
