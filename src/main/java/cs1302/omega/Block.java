package cs1302.omega;

import javafx.scene.shape.Rectangle;

/**
 * Block is a Rectangle which represents the blocks on the field its on.
 */

public class Block extends Rectangle {

    static final int UP = 0;
    static final int RIGHT = 1;
    static final int DOWN = 2;
    static final int LEFT = 3;
    int posX;
    int posY;
    int oldPosX;
    int oldPosY;
    Block previous;
    int direction = LEFT;
    int maxX;
    int maxY;

    /**
     * Block comnstructor.
     * @param x
     * @param y
     * @param p
     * @param f
     */

    public Block(int x, int y, Block p, Field f) {

        super(MainSnake.block_size, MainSnake.block_size);
        posX = x;
        posY = y;

        setTranslateX(posX * MainSnake.block_size);
        setTranslateY(posY * MainSnake.block_size);
        previous = p;

        maxX = f.getW();
        maxY = f.getH();
    } //Block

    /**
     * Method that updates the coordinates of snake when a given direction is inputed.
     */

    public void update() {
        oldPosX = posX;
        oldPosY = posY;

        if (previous == null) {
            switch (direction) {
            case UP:
                moveUp();
                break;
            case RIGHT:
                moveRight();
                break;
            case DOWN:
                moveDown();
                break;
            case LEFT:
                moveLeft();
                break;
            } // break
        } else {
            posX = previous.oldPosX;
            posY = previous.oldPosY;
        }
        updatePosition();
    } // update

    /**
     * Method to move up.
     */

    public void moveUp() {
        posY--;
        if (posY < 0) {
            posY = maxY - 1;
        }
    }

    /**
     * Method to move down.
     */
    public void moveDown() {
        posY++;
        if (posY >= maxY) {
            posY = 0;
        }
    }

    /**
     * Method to move left.
     */
    public void moveLeft() {
        posX--;
        if (posX < 0) {
            posX = maxX - 1;
        }
    }

    /**
     * Method to move right.
     */
    public void moveRight() {
        posX++;
        if (posX >= maxX) {
            posX = 0;
        }
    }

    /**
     * Updates the position of snake.
     */
    public void updatePosition() {
        setTranslateX(posX * MainSnake.block_size);
        setTranslateY(posY * MainSnake.block_size);
    }

} // Block
