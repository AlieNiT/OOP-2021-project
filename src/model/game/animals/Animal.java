package model.game.animals;

import model.game.Mapable;

import java.util.Random;

public abstract class Animal implements Mapable {
    float x, y, v;

    public void move(int mapSize) {
        switch (new Random().nextInt() % 4) {
            case 0 -> {
                x += v;
                if (x >= mapSize) x -= 2 * (x - (mapSize - 1));
            }
            case 1 -> {
                x -= v;
                if (x < 0) x *= -1;
            }
            case 2 -> {
                y = (y + v);
                if (y >= mapSize) y -= 2 * (y - (mapSize - 1));
            }
            case 3 -> {
                y -= v;
                if (y < 0) y *= -1;
            }
        }
    }

    public Animal(float x, float y, float v) {
        this.x = x;
        this.y = y;
        this.v = v;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
