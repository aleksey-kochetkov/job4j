package pingpong;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class PingPong extends Application {
    private static final String JOB4J = "Пинг-понг www.job4j.ru";
    static final int LIMIT_X = 300;
    static final int LIMIT_Y = 300;
    static final int WIDTH = 10;
    static final int HEIGHT = 10;
    private Thread logic;

    @Override
    public void start(Stage stage) {
        Group group = new Group();
        Rectangle rect = new Rectangle(50, 100, WIDTH, HEIGHT);
        group.getChildren().add(rect);
        this.logic = new Thread(new RectangleMove(rect));
        this.logic.start();
        stage.setScene(new Scene(group, LIMIT_X, LIMIT_Y));
        stage.setTitle(JOB4J);
        stage.setResizable(false);
        stage.setOnCloseRequest(event -> this.logic.interrupt());
        stage.show();
    }
}