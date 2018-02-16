package atmatm6.midanmaku.game;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

public class Sprite {

    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected boolean vis;
    protected Image image;

    public Sprite(int x, int y) {
        this.x = x;
        this.y = y;
        vis = true;
    }

    protected void loadImage(String imageName) {
        URL imag = getClass().getResource("/"+imageName);
        ImageIcon ii = new ImageIcon(imag);
        image = ii.getImage();
    }

    protected void getImageDimensions() {
        width = image.getWidth(null);
        height = image.getHeight(null);
    }

    public Image getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isVisible() {
        return vis;
    }

    public void setVisible(Boolean visible) {
        vis = visible;
    }
}