package lab;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Ball implements Simulable{
    private int Xball, Yball;

    Ball(int x, int y){
        this.Xball = x;
        this.Yball = y;
    }
    @Override
    public int getX(){
        return Xball;
    }
    @Override
    public int getY(){
        return Yball;
    }
    @Override
    public void decY(int i) {
        this.Yball -= i;
    }
    @Override
    public void incY(int i) {
        this.Yball += i;
    }
    @Override
    public void setPos(int y) {
        this.Yball = y;
    }

    @Override
    public void draw(Canvas canvas, int x, int y){
        GraphicsContext GC = canvas.getGraphicsContext2D();
        GC.setFill(Color.RED);
        GC.fillOval(Xball, Yball, 20, 20);
    }
    public void direction(boolean updown, boolean leftright){
        if(updown){  //
            this.Yball--;
        }else {
            this.Yball++;
        }
        if(leftright){ //left 1, r 0
            this.Xball--;
        }else {
            this.Xball++;
        }
    }
}
