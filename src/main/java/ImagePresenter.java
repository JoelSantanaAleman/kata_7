import interfaces.Image;
import interfaces.ImageDisplay;

public class ImagePresenter {

    private final ImageDisplay imageDisplay;
    private Image image;

    public ImagePresenter(Image image, ImageDisplay display) {
        this.imageDisplay = display;
        this.image = image;
        this.imageDisplay.on((ImageDisplay.Shift) offset -> shift(offset));
        this.imageDisplay.on((ImageDisplay.Released) offset -> released(offset));
        this.imageDisplay.on((ImageDisplay.Pressed) offset -> pressed(offset));
        this.imageDisplay.clear();
    }

    private void pressed(int offset) {
        setImage();
    }

    public void setImage() {
        this.image = this.imageDisplay.image();
    }

    private void shift(int offset) {
        imageDisplay.clear();
        imageDisplay.paint(image, offset);
        if (offset > 0)
            imageDisplay.paint(image.previous(), offset - imageDisplay.getWidth());
        else
            imageDisplay.paint(image.next(), imageDisplay.getWidth() + offset);

    }

    private void released(int offset) {
        if (Math.abs(offset) >= imageDisplay.getWidth() / 2)
            image = offset > 0 ? image.previous() : image.next();
        repaint();
    }


    public void show() {
        repaint();
    }

    private void repaint() {
        this.imageDisplay.clear();
        this.imageDisplay.paint(image, 0);
    }
}