package game;

import game.objects.Apple;
import game.objects.Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SnakeMain extends JPanel implements ActionListener {
    public static JFrame jframe;
    public static final int SCALE = 32;
    public static final int WIDTH = 20;
    public static final int HEIGHT = 20;

    Snake snake = new Snake(5, 6, 5, 5);
    Apple apple = new Apple(
            (int) (Math.random() * HEIGHT - 1),
            (int) (Math.random() * WIDTH - 1));

    public static int speed = 10;

    Timer timer = new Timer(1000 / speed, this);

    public SnakeMain() {
        timer.start();

        addKeyListener(new KeyBoard());
        setFocusable(true);
    }

    public void paint(Graphics graphics) {
        graphics.setColor(Color.YELLOW);
        graphics.fillRect(0, 0, WIDTH * SCALE, HEIGHT * SCALE);

        for (int x = 0; x < WIDTH * SCALE; x = x + SCALE) {
            graphics.setColor(Color.BLACK);
            graphics.drawLine(x, 0, x, HEIGHT * SCALE);
        }
        for (int y = 0; y < HEIGHT * SCALE; y = y + SCALE) {
            graphics.setColor(Color.BLACK);
            graphics.drawLine(0, y, WIDTH * SCALE, y);
        }

        for (int l = 0; l < snake.lenght; l++) {
            graphics.setColor(Color.BLUE);
            graphics.fillRect(snake.snakeX[l] * SCALE + 2, snake.snakeY[l] * SCALE + 2, SCALE - 3, SCALE - 3);

            graphics.setColor(Color.BLACK);
            graphics.fillRect(snake.snakeX[0] * SCALE + 2, snake.snakeY[0] * SCALE + 2, SCALE - 3, SCALE - 3);
        }

        graphics.setColor(Color.RED);
        graphics.fillRect(apple.posX * SCALE + 2, apple.posY + 2, SCALE - 4, SCALE - 4);
    }

    public static void main(String[] args) {

        jframe = new JFrame("Snake");
        jframe.setSize(WIDTH * SCALE + 15, HEIGHT * SCALE + 40);
        jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jframe.setResizable(false);
        jframe.setLocationRelativeTo(null);
        jframe.add(new SnakeMain());
        jframe.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        snake.move();
        repaint();

        if (snake.snakeX[0] == apple.posX && snake.snakeY[0] == apple.posY) {
            apple.randomPosition();
            snake.lenght++;
        }

        for (int i = 1; i < snake.lenght; i++) {
            if (snake.snakeX[i] == apple.posX && snake.snakeY[i] == apple.posY) {
                apple.randomPosition();
            }
            if (snake.snakeX[0] == snake.snakeX[i] && snake.snakeY[0] == snake.snakeY[i]) {
                timer.stop();
                JOptionPane.showMessageDialog(null, "Game Over! Start again?");
                jframe.setVisible(false);
                snake.lenght = 2;
                snake.direction = 0;
                apple.randomPosition();
                jframe.setVisible(true);
                timer.start();
            }

        }
    }

    public class KeyBoard extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent event) {
            int key = event.getKeyCode();

            if (key == KeyEvent.VK_UP && snake.direction != 2 || key == KeyEvent.VK_W && snake.direction != 2) {
                snake.direction = 0;
            }
            if (key == KeyEvent.VK_DOWN && snake.direction != 0 || key == KeyEvent.VK_S && snake.direction != 0) {
                snake.direction = 2;
            }
            if (key == KeyEvent.VK_RIGHT && snake.direction != 3 || key == KeyEvent.VK_D && snake.direction != 3) {
                snake.direction = 1;
            }
            if (key == KeyEvent.VK_LEFT && snake.direction != 1 || key == KeyEvent.VK_A && snake.direction != 1) {
                snake.direction = 3;
            }

        }
    }
}
