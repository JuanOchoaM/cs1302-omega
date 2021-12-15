package cs1302.omega;

import java.util.ArrayList;
import javafx.scene.paint.Color;

/**
 * Snake object that is moving in game. Utilizes ArrayList to create its body.
 */
public class Snake {

    ArrayList<Block> blocks = new ArrayList<Block>();

    Block head;
    Block tail;

    /**
     * Snake constructor.
     * @param il the initial length of snake
     * @param f the field it is on
     */
    public Snake(int il, Field f) {
        int ipx = f.getW() / 2;
        int ipy = f.getH() / 2;
        head = new Block(ipx,ipy,null,f);
        blocks.add(head);
        head.setFill(Color.BLUE.desaturate());
        tail = head;
        for (int i = 1; i < il; i++) {
            Block b = new Block(ipx + i, ipy, tail, f);
            blocks.add(b);
            tail = b;
        }
    } // Snake

    /**
     * Gets direction.
     * @return head.direction integer corresponding to direction
     */
    public int getDirection() {
        return head.direction;
    }

    /**
     * Sets direction.
     *
     * @param d integer corresponding to direction
     */
    public void setDirection(int d) {
        head.direction = d;
    }
} // Snake
