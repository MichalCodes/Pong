package lab;

import javafx.scene.canvas.Canvas;

public interface Simulable{
    void draw(Canvas canvas, int x, int y);
    int getX();
    int getY();
    void decY(int i);
    void incY(int i);
    void setPos(int y);
}
