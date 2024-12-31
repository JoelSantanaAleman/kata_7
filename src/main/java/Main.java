import command.ShowNextImage;
import command.ShowPreviousImage;
import interfaces.Image;
import model.LocalImageLoader;
import swing.MainFrame;

import java.io.File;

public class Main {
    private static String root = "C:\\Users\\26joe\\Documents\\UNI\\3_ano\\IS2_\\image_viewer_2\\imagenes";

    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        Image image = new LocalImageLoader(new File(root)).load();
        ImagePresenter presenter = new ImagePresenter(image, frame.imageDisplay());
        presenter.show();
        frame.addCommand("<", new ShowPreviousImage(frame.imageDisplay()));
        frame.addCommand(">", new ShowNextImage(frame.imageDisplay()));
        frame.setVisible(true);
    }
}