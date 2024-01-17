package lab;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;

public class Score implements  Visual{
    private int L;
    private int R;
    @Override
    public void draw(Canvas canvas) {
        GraphicsContext GC = canvas.getGraphicsContext2D();
        GC.setFont(Font.font(20));
        String LeftP = Integer.toString(L);
        String RightP = Integer.toString(R);
        GC.fillText("Goals: " + LeftP, 100, 30);
        GC.fillText("Score: " + RightP, 335, 30);
    }
    public void actuelize(int L, int R){
        this.L = L;
        this.R = R;
    }
}
