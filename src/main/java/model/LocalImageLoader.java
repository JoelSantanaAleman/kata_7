package model;

import interfaces.Image;
import interfaces.ImageLoader;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Set;

public class LocalImageLoader implements ImageLoader {

    private final File[] imageFile;

    public LocalImageLoader(File folder) {
        this.imageFile = folder.listFiles(isImage());
    }

    private static final Set<String> imageExtensions = Set.of(".jpg",".png", ".jpeg");

    private static FilenameFilter isImage() {
        return (dir, name) -> imageExtensions.stream().anyMatch(extension -> name.endsWith(extension));
    }

    @Override
    public Image load() {
        if(imageFile == null|| imageFile.length == 0){
            throw new RuntimeException("No images found");
        }
        return imageAt(0);
    }

    private Image imageAt(int i) {
        return new Image() {
            @Override
            public String name() {
                return imageFile[i].getAbsolutePath();
            }

            @Override
            public Image next() {
                return imageAt((i + 1) % imageFile.length);
            }

            @Override
            public Image previous() {
                return imageAt((i - 1 + imageFile.length) % imageFile.length);
            }
        };
    }

}
