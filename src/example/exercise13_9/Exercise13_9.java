package example.exercise13_9;

import java.util.*;
import java.awt.*;
import java.awt.event.*;

class BouncingBall2 extends Frame {
   static final int FRAME_WIDTH = 400;
    final int FRAME_HEIGHT = 300;

    final int TOP;
    final int BOTTOM;
    final int LEFT;
    final int RIGHT;

    ArrayList balls = new ArrayList();

    BouncingBall2(String title) {
        super(title);
        setBounds(300, 200, FRAME_WIDTH, FRAME_HEIGHT);
        setResizable(false);
        setVisible(true);

        Insets insets = getInsets();
        TOP = insets.top;
        LEFT = insets.left;
        BOTTOM = FRAME_HEIGHT - insets.bottom;
        RIGHT = FRAME_WIDTH - insets.right;

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    void start() {
        new BallGenerator().start(); // 쓰레드가 한개 추가되고
        while (true) { // 여기는 메인쓰레드이니까 무한반복이 가능
            int size = balls.size();
            for (int i = 0; i < size; i++) { // for문을 통해서 n번째 ball을 계속 굴려줌.
                Ball b = (Ball) balls.get(i);
                b.x += b.xStep;
                b.y += b.yStep;

                if (b.x <= LEFT) {
                    b.x = LEFT;
                    b.xStep += b.SPEED;
                }

                if (b.x >= RIGHT - b.SIZE) {
                    b.x = RIGHT - b.SIZE;
                    b.xStep -= b.SPEED;
                }

                if (b.y <= TOP) {
                    b.y = TOP;
                    b.yStep += b.SPEED;
                }

                if (b.y >= BOTTOM - b.SIZE) {
                    b.y = BOTTOM - b.SIZE;
                    b.yStep -= b.SPEED;
                }


            }
            repaint();
            try {
                Thread.sleep(100);
            } catch (Exception e) {
            }
        }
    } // start()

    public void paint(Graphics g) {
        g.drawString("Number of balls :" + balls.size(), 20, 50);
        g.setColor(Color.RED);
        int size = balls.size();
        for (int i = 0; i < size; i++) {
            Ball b = (Ball) balls.get(i); // (Ball) 부분을 모르겠다
            g.fillOval(b.x, b.y, Ball.SIZE, Ball.SIZE); // ballgenerator에서 값을 3초마다하나씩 넣어서 생성한다
        }
    }

    class BallGenerator extends Thread {
        public void run() {
            int x, y;
            while (true) {
                x = (int)(Math.random() * (RIGHT - Ball.SIZE)) + 1;
                y = (int)(Math.random() * (BOTTOM - Ball.SIZE)) + 1;
                balls.add(new Ball(x, y));

                try {
                    Thread.sleep(3000);
                } catch (Exception e) {
                }

                //3초마다 Frame의 임의의 위치에 공을 생성해서 balls에 추가한다
            }
        }
    }


} // class BouncingBall

class Exercise13_9 {
    public static void main(String[] args) {
        new BouncingBall2("Bouncing Ball2").start();
    }
}