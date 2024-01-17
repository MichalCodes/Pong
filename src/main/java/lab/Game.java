package lab;

import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;

public class Game implements MainGame{
    private final Visual background = new Background(600, 400);
    private final Simulable ball = new Ball(230, 100);
    private final Simulable leftPlaierB = new Bat(20, 100);
    private final Simulable rightPlayerB = new Bat(570, 180);
    private Visual score;
    private GoalDetection g;
    private boolean LR, UpDwn;
    private int L, R, scGametime;
    Game(){
        this.score = new Score();
        this.g = new GoalDetection((Ball)ball);
        this.scGametime = 0;
    }
    private void directions(Canvas canvas){        //ODRAZY
        //vrch a spodek
        if (ball.getY() <= 0) UpDwn = false;
        if (ball.getY() >= 380) UpDwn = true;
        //palky předek
        if (ball.getX() == 30 && ball.getY() >= leftPlaierB.getY() && ball.getY() <= leftPlaierB.getY() + 50)
            LR = false;
        if (ball.getX() == 550 && ball.getY() >= rightPlayerB.getY() && ball.getY() <= rightPlayerB.getY() + 50)
            LR = true;
        //palky spodek
        if ((ball.getX() >= 20 && ball.getX() <= 30) && ball.getY() == leftPlaierB.getY() + 50) UpDwn = false;
        if ((ball.getX() >= 560 && ball.getX() <= 580) && ball.getY() == rightPlayerB.getY() + 50) UpDwn = false;
        //palky vrch
        if ((ball.getX() >= 20 && ball.getX() <= 30) && ball.getY() == leftPlaierB.getY()) UpDwn = true;
        if ((ball.getX() >= 570 && ball.getX() <= 580) && ball.getY() == rightPlayerB.getY()) UpDwn = false;
        //prava strana
        if (g.isRightGoal()) {
            L++;
            score.draw(canvas);
            LR = true;
        }
    }
    @Override
    public void draw(Canvas canvas) {
        scGametime++;
        R = scGametime / 500;
        background.draw(canvas);
        Score sc = new Score();
        sc.actuelize(L, R);
        score = sc;
        directions(canvas);
        leftPlaierB.setPos(ball.getY() - 10);
        //volani funkci
        score.draw(canvas);
        leftPlaierB.draw(canvas, 0, leftPlaierB.getY());
        rightPlayerB.draw(canvas, 0, rightPlayerB.getY());
        Ball bal = (Ball) ball;
        bal.direction(UpDwn, LR);
        ball.draw(canvas, ball.getX(), ball.getY());
    }
    @Override
    public void mouseEvents(Canvas canvas) {
        //eventy na sipky
        canvas.getScene().setOnKeyPressed(event -> {
            KeyCode key = event.getCode();
            if (key == KeyCode.UP && rightPlayerB.getY() > 0) {
                rightPlayerB.decY(10);
            } else if (key == KeyCode.DOWN && rightPlayerB.getY() < 350) {
                rightPlayerB.incY(10);
            }
        });
    }
    @Override
    public int getScore() {
        return this.scGametime / 500;
    }
    @Override
    public void rstSore() {
        scGametime = 0;
        L = 0;
    }
    @Override
    public int getGoals() {
        return this.L;
    }
}
