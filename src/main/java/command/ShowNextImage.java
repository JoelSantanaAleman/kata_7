package command;

import interfaces.ImageDisplay;

public class ShowNextImage implements Command{

    private final ImageDisplay imageDisplay;

    public ShowNextImage(ImageDisplay imageDisplay) {
        this.imageDisplay = imageDisplay;
    }

    @Override
    public void execute() {
        imageDisplay.clear();
        imageDisplay.paint(imageDisplay.image().next(), 0);
    }
}
