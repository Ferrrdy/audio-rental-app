package Custom;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * @author nabil
 */

public class RoundedPanel extends JPanel {
    private int roundTopLeft = 0;
    private int roundTopRight = 0;
    private int roundBottomLeft = 0;
    private int roundBottomRight = 0;

    public RoundedPanel() {
        setOpaque(false);
    }

    public int getRoundTopLeft() {
        return roundTopLeft;
    }

    public void setRoundTopLeft(int roundTopLeft) {
        this.roundTopLeft = roundTopLeft;
        repaint();
    }

    public int getRoundTopRight() {
        return roundTopRight;
    }

    public void setRoundTopRight(int roundTopRight) {
        this.roundTopRight = roundTopRight;
        repaint();
    }

    public int getRoundBottomLeft() {
        return roundBottomLeft;
    }

    public void setRoundBottomLeft(int roundBottomLeft) {
        this.roundBottomLeft = roundBottomLeft;
        repaint();
    }

    public int getRoundBottomRight() {
        return roundBottomRight;
    }

    public void setRoundBottomRight(int roundBottomRight) {
        this.roundBottomRight = roundBottomRight;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics graphic) {
        Graphics2D g2 = (Graphics2D) graphic.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        Shape area = createRoundedShape();
        g2.fill(area);
        g2.dispose();
        super.paintComponent(graphic);
    }

    private Shape createRoundedShape() {
        int width = getWidth();
        int height = getHeight();
        int tl = roundTopLeft;
        int tr = roundTopRight;
        int bl = roundBottomLeft;
        int br = roundBottomRight;

        // Buat bentuk rounded pakai path
        Path2D.Float path = new Path2D.Float();
        path.moveTo(tl, 0);
        path.lineTo(width - tr, 0);
        path.quadTo(width, 0, width, tr);
        path.lineTo(width, height - br);
        path.quadTo(width, height, width - br, height);
        path.lineTo(bl, height);
        path.quadTo(0, height, 0, height - bl);
        path.lineTo(0, tl);
        path.quadTo(0, 0, tl, 0);
        path.closePath();
        return path;
    }
}
