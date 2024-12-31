package swing;

import interfaces.Image;
import interfaces.ImageDisplay;
import model.Picture;
import model.ImageResizer;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SwingImageDisplay extends JPanel implements ImageDisplay {
    private Image image;
    private BufferedImage renderetBitmap;
    private Shift imageShift = Shift.Null;
    private Released releasedEvent = Released.Null;
    private int initShift;
    private List<Picture> paints = new ArrayList<>();
    private Pressed pressed = Pressed.Null;

    public SwingImageDisplay() {
        this.addMouseListener(mouseListener());
        this.addMouseMotionListener(mouseMotionListener());
    }

    private MouseListener mouseListener() {
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {
                initShift = e.getX();
                pressed.in(initShift);
            }

            @Override
            public void mouseReleased(MouseEvent e) {

                releasedEvent.at(e.getX() - initShift);

            }


            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) { }
        };
    }

    private MouseMotionListener mouseMotionListener() {
        return new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

                imageShift.to(e.getX() - initShift);
            }
            @Override
            public void mouseMoved(MouseEvent e) {}
        };
    }

    @Override
    public Image image() {
        return image;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        System.out.println(image.name());
        for (Picture paint : paints) {
            renderetBitmap = load(paint.image().name());
            if (paint.image() != null) {
                ImageResizer resizer = new ImageResizer(new Dimension(this.getWidth(), this.getHeight()));
                Dimension resized = resizer.resize(new Dimension(renderetBitmap.getWidth(), renderetBitmap.getHeight()));
                int x = (this.getWidth() - resized.width) / 2;
                int y = (this.getHeight() - resized.height) / 2;
                g.drawImage(renderetBitmap, paint.offset()+x, y, resized.width, resized.height, null);
            }
        }
    }

    @Override
    public void paint(Image Image, int offset) {
        this.image = Image;
        renderetBitmap = load(this.image.name());
        paints.add(new Picture(image, offset));
        this.repaint();
    }

    @Override
    public void clear() {
        paints.clear();
    }

    @Override
    public void on(Shift shift) {
        this.imageShift = shift != null ? shift : Shift.Null;
    }

    @Override
    public void on(Released released) {
        this.releasedEvent = released != null ? released : Released.Null;
    }

    @Override
    public void on(Pressed pressed) {
        this.pressed = pressed != null ? pressed : Pressed.Null;
    }

    private BufferedImage load(String name){
        try {
            return ImageIO.read(new File(name));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}