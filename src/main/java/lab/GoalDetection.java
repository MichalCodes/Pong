package lab;

public class GoalDetection {
    private Simulable ball;

    GoalDetection(Ball ball){
        this.ball = ball;
    }
    public boolean isLeftGoal(){
        return ball.getX() <= 0;
    }
    public boolean isRightGoal(){
        return ball.getX() >= 580;
    }
}
