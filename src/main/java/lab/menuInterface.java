package lab;

import javafx.scene.canvas.Canvas;

public interface menuInterface {
    void draw(Canvas canvas);
    boolean checkMouseEvents(Canvas canvas);
    void removeButton(Canvas canvas);
    String getUsername();
}
