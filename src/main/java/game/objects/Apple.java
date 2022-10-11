package game.objects;

import game.SnakeMain;

public class Apple {
    public int posX;
    public int posY;

    public Apple(int x, int y) {
        this.posX = x;
        this.posY = y;
    }

    public void randomPosition() {
        posX = (int) (Math.random() * SnakeMain.HEIGHT - 1);
        posY = (int) (Math.random() * SnakeMain.WIDTH - 1);
    }
}
