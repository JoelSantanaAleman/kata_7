package command;

import interfaces.ImageDisplay;

public class ShowPreviousImage implements Command{
    private final ImageDisplay imageDisplay;

    public ShowPreviousImage(ImageDisplay imageDisplay) {
        this.imageDisplay = imageDisplay;
    }

    @Override
    public void execute() {
        imageDisplay.clear();
        imageDisplay.paint(imageDisplay.image().previous(), 0);
    }
}
