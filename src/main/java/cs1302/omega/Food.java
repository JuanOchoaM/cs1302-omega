package cs1302.omega;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Food object that is consumed by snake.
 */

public class Food extends Rectangle {

    int posX, posY;

    /**
     * Food object constructor.
     * @param x coordinate x
     * @param y coordinate y
     */

    public Food(int x, int y) {
        super(MainSnake.block_size, MainSnake.block_size);
        posX = x;
        posY = y;
        setTranslateX(posX * MainSnake.block_size);
        setTranslateY(posY * MainSnake.block_size);
        setFill(Color.LIGHTGREEN);
        setStroke(Color.GREEN);

    }

    /**
     * Returns X-coordinate  of food.
     * @return posX int
     */
    public int getPosX() {
        return posX;
    }

    /**
     * Returns Y-coordinate  of food.
     * @return posY int
     */
    public int getPosY() {
        return posY;
    }

}
