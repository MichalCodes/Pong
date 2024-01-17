package lab;

import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class Menu implements menuInterface{
    private final Button play = new Button("Play");
    TextField textField = new TextField();
    private final Group root;
    private boolean pom, block;
    private String username;
    Menu(Canvas canvas){
        this.pom = false;
        this.block = false;
        this.root = (Group) canvas.getScene().getRoot();
        this.username = "No name";
    }
    @Override
    public void draw(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0,0,600,400);
        gc.setFill(Color.WHITE);
        gc.setFont(Font.font(25));
        gc.fillText("Menu", 265, 50);
        gc.setFont(Font.font(12));
        gc.fillText("Add your username", 240, 280);
        gc.setFont(Font.font(15));
        gc.fillText("4 goals = game over", 230, 240);
        play.setPrefSize(100, 50);
        play.setLayoutX(250);
        play.setLayoutY(330);
        play.setStyle("-fx-background-color: yellow;");
        textField.setPrefSize(100, 20);
        textField.setLayoutX(250);
        textField.setLayoutY(290);
        if(!block) {
            root.getChildren().add(play);
            root.getChildren().add(textField);
            block = true;
        }
    }

    @Override
    public boolean checkMouseEvents(Canvas canvas) {
        boolean retVal = false;
        play.setOnAction(event -> {pom = true;});
        if(pom) retVal = true;
        pom = false;
        return retVal;
    }
    @Override
    public void removeButton(Canvas canvas) {
        if(block) {
            username = textField.getText();
            root.getChildren().remove(play);
            root.getChildren().remove(textField);
        }
        block = false;
    }
    @Override
    public String getUsername() {
        return this.username;
    }
}
