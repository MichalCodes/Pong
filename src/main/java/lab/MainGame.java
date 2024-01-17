package lab;

import javafx.scene.canvas.Canvas;

public interface MainGame {
    void draw(Canvas canvas);
    void mouseEvents(Canvas canvas);

    int getScore();
    void rstSore();
    int getGoals();
}
