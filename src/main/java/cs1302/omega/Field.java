package cs1302.omega;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import java.util.ArrayList;

/**
 * Field that is the map of snake game that utilizes an ArrayList.
 */
public class Field extends Pane {
    private int w, h;

    ArrayList<Block> blocks = new ArrayList<Block>();
    int score = 0;
    Food f;
    Snake snake;

    /**
     * Field constructor.
     * @param width
     * @param height
     */
    public Field(int width, int height) {
        w = width;
        h = height;
        setMinSize(w * MainSnake.block_size, h * MainSnake.block_size);
        setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID
            , null, new BorderWidths(1))));
        addFood();
    } // Field()

    /**
     * Method that creates the snake by adding the necessary blocks.
     * @param s snake
     */
    public void addSnake(Snake s) {
        snake = s;
        for (Block b:s.blocks) {
            addBlock(b);
        }
    }

    /**
     * Updates the snakes tail if food is eaten.
     */
    public void update() {
        for (Block b:blocks) {
            b.update();
        }
        if (isEaten(f)) {
            score += 20;
            addFood();
            addNewBlock();
        }
    }

    /**
     * Checks whether or not if snake is dead.
     * @return false if snake is not dead
     */
    public boolean isDead() {
        for (Block b:blocks) {
            if (b != snake.head) {
                if (b.posX == snake.head.posX && b.posY == snake.head.posY) {
                    return true;
                } //if
            } //if
        } // for
        return false;
    } // isDead

    /**
     * Adds block at snakes tail coordinates.
     */
    public void addNewBlock() {
        Block b = new Block(snake.tail.oldPosX, snake.tail.oldPosY, snake.tail, this);
        snake.tail = b;
        addBlock(b);

    }

    /**
     * Adds blocks at snakes tail.
     * @param b adds a block
     */
    private void addBlock(Block b) {
        getChildren().add(b);
        blocks.add(b);
    }

    /**
     * Method that randomnly places Food object.
     */
    public void addFood() {
        int randomX = (int) (Math.random() * w);
        int randomY = (int) (Math.random() * h);
        Food food = new Food(randomX, randomY);
        getChildren().add(food);
        getChildren().remove(f);
        f = food;
    }

    /**
     * Determines if food has been eaten by snake's head.
     * @param f food
     * @return true if food is eaten
     */

    public boolean isEaten(Food f) {
        if (f == null) {
            return false;
        }
        return f.getPosX() == snake.head.posX && f.getPosY() == snake.head.posY;
    }

    /**
     * Gets the width.
     * @return w
     */
    public int getW() {
        return w;
    }

    /**
     * Gets the height.
     * @return h
     */
    public int getH() {
        return h;
    }

} // Field
