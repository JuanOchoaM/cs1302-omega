package cs1302.omega;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.scene.control.Label;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Font;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.MenuBar;

/**
 * Snake arcade game that extends Java Application.
 */

public class MainSnake extends Application {

    static int block_size = 20;
    int width = 33, height = 20;
    int il = 5;
    long then = System.nanoTime();
    boolean changed = false;
    int nextUpdate;
    boolean hasNext = false;
    Field f;
    Stage stage;
    Menu exit;
    MenuBar menu;
    Label score;

    /**
     * Alert method to create alert. To save line space.
     * @return al alert
     */

    public Alert alert() {
        Alert al = new Alert(AlertType.INFORMATION);
        al.setHeaderText("You Lost… \nClick OK To Play Again.");
        al.setContentText("Your Score Is " + f.score);
        al.setResizable(true);
        al.setWidth(265.0);
        al.setHeight(265.0);
        return al;
    }

    /**
     * Start method for JavaFX application lifecycle.
     * @param ps stage
     * @inheritdoc
     */
    @Override
    public void start(Stage ps) {
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        f = new Field(width, height);
        f.addSnake(new Snake(il,f));
        Label score = new Label("Score: 0");
        score.setFont(Font.font("tahoma", 32));
        AnimationTimer timer = new AnimationTimer() { // timer to update snake dir. and score.
                public void handle(long now) {
                    if (now - then > 1000000000 / 12) {
                        f.update();
                        then = now;
                        score.setText("Score: " + f.score);
                        changed = false;
                        if (hasNext) {
                            setDirection(f.snake, nextUpdate);
                            hasNext = false;
                        }
                        if (f.isDead()) {  // checks if snake is dead and prompts to reset game
                            stop();
                            Alert al = alert();
                            Platform.runLater(al::showAndWait);
                            al.setOnHidden(e -> {  // initiates field/map after dialog is shown
                                root.getChildren().clear();
                                f = new Field(width, height);
                                f.addSnake(new Snake(il, f));
                                score.setText("Score: 0");
                                root.getChildren().addAll(addMenu(), f, score);
                                start();
                            });
                        }
                    }
                }
            };
        timer.start();
        root.getChildren().addAll(addMenu(), f, score);
        Scene scene = new Scene(root, 685, 550);
        scene.setOnKeyPressed(e -> {  // user input to control snake
            if (e.getCode().equals(KeyCode.UP) && f.snake.getDirection() != Block.DOWN) {
                setDirection(f.snake, Block.UP);
            }
            if (e.getCode().equals(KeyCode.DOWN) && f.snake.getDirection() != Block.UP) {
                setDirection(f.snake, Block.DOWN);
            }
            if (e.getCode().equals(KeyCode.RIGHT) && f.snake.getDirection() != Block.LEFT) {
                setDirection(f.snake, Block.RIGHT);
            }
            if (e.getCode().equals(KeyCode.LEFT) && f.snake.getDirection() != Block.RIGHT) {
                setDirection(f.snake, Block.LEFT);
            }
        });
        ps.setMaxWidth(685); // stage window
        ps.setMaxHeight(550);
        ps.setResizable(true);
        ps.setTitle("SNAKE!");
        ps.setScene(scene);
        ps.sizeToScene();
        ps.show();
    } // start

    /**
     * Method to create menu/exit.
     * @return menu MenuBar
     */
    public MenuBar addMenu() {
        // creating menu to gracefully exit
        Menu exit = new Menu("Exit");
        MenuBar menu = new MenuBar();
        menu.getMenus().add(exit);
        MenuItem exitItem = new MenuItem("Exit");
        exit.getItems().add(exitItem);
        exitItem.setOnAction(event -> System.exit(0));
        return menu;
    }

    /**
     * Method to limit and control snake movement to allow easier control.
     *
     * @param s the Snake object
     * @param d integer corresponding to direction
     */

    public void setDirection(Snake s, int d) {
        if (!changed) {
            s.setDirection(d);
            changed = true;
        } else {
            hasNext = true;
            nextUpdate = d;
        }
        s.setDirection(d);
    }

    /**
     * Main entry-point into the application.
     * @param args the command line arguments.
     */

    public static void main(String[] args) {
        try {
            Application.launch(MainSnake.class, args);
        } catch (UnsupportedOperationException e) {
            System.out.println(e);
            System.exit(1);
        }
    } // main
} // Main_UI
