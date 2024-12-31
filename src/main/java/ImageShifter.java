import interfaces.Image;
import interfaces.ImageDisplay;

public class ImageShifter {
    private final ImageDisplay display;

    public ImageShifter(ImageDisplay display) {
        this.display = display;
    }

    public void shiftImage(Image image, int offset) {
        display.clear();
        display.paint(image, offset);
        if (offset > 0) {
            display.paint(image.previous(), offset - display.getWidth());
        } else {
            display.paint(image.next(), display.getWidth() + offset);
        }
    }
}

