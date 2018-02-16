package atmatm6.midanmaku.game;

import atmatm6.midanmaku.MidiDanmaku;

public class Missile extends Sprite {

    private final int MISSILE_SPEED = 8;

    public Missile(int x, int y) {
        super(x, y);
        initMissile();
    }

    private void initMissile() {
        loadImage("bullet.png");
        getImageDimensions();
    }


    public void move() {
        y -= MISSILE_SPEED;

        if (x > MidiDanmaku.size[0]) {
            vis = false;
        }
    }
}