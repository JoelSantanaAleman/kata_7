package model;

import java.awt.*;

public class ImageResizer {
    private final Dimension dimension;

    public ImageResizer(Dimension dimension) {
        this.dimension = dimension;
    }

    public Dimension resize(Dimension original) {
        int newWidth = dimension.width;
        int newHeight = dimension.height;

        double widthRatio = (double) newWidth / original.width;
        double heightRatio = (double) newHeight / original.height;

        double ratio = Math.min(widthRatio, heightRatio);

        return new Dimension((int) (original.width * ratio), (int) (original.height * ratio));
    }
}