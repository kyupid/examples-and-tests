package example.exercise13_9;

class Ball {
    int x = 100;
    int y = 100;
    static final int SIZE = 30;
    final int SPEED = 5;
    int xStep = SPEED;
    int yStep = SPEED;

    Ball(int x, int y) {
        this.x = x;
        this.y = y;
    }
}