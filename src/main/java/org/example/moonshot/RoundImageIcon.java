package org.example.moonshot;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class RoundImageIcon extends ImageIcon {
    private final int cornerRadius;

    public RoundImageIcon(Image image, int cornerRadius) {
        super(image);
        this.cornerRadius = cornerRadius;
    }

    @Override
    public synchronized void paintIcon(Component c, Graphics g, int x, int y) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Shape clip = g2d.getClip();
        g2d.setClip(new RoundRectangle2D.Float(x, y, getIconWidth(), getIconHeight(), cornerRadius, cornerRadius));
        super.paintIcon(c, g2d, x, y);
        g2d.setClip(clip);
        g2d.dispose();
    }
}