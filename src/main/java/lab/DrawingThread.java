package lab;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DrawingThread extends AnimationTimer {
	private final Canvas canvas;
	private final Game game;
	private final menuInterface menu;
	private boolean scChanger;
    private boolean block, block2;
	private final DBmanager database;
	private final Connection conection;
	private String username;
	public DrawingThread(Canvas canvas, Scene scene, Stage stage) {
		this.canvas = canvas;
		this.username = "No name";
		GraphicsContext gc = canvas.getGraphicsContext2D();
		this.game = new Game();
		this.menu = new Menu(canvas);
		this.scChanger = false;
		this.block = false;
		this.block2 = false;
		this.database = new DBmanager();
		try {
			this.conection = DriverManager.getConnection("jdbc:h2:./scoreDB;AUTO_SERVER=TRUE");
			database.createTable(conection);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	@Override
	public void handle(long now) {
		if(!scChanger){
			menu.draw(canvas);
			scChanger = menu.checkMouseEvents(canvas);
			if(block){
				block = false;
				int score = game.getScore();
				try {
					database.insertData(conection, username, score);
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
				game.rstSore();
			}
			try {
				database.selectData(conection, canvas);
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
			block2 = true;
		} else {
			menu.removeButton(canvas);
			username = menu.getUsername();
			game.draw(canvas);
			game.mouseEvents(canvas);
			if(game.getGoals() > 3) {
				scChanger = false;
				block = true;
				block2 = false;
			}
		}
		Routines.sleep(4);
	}
}