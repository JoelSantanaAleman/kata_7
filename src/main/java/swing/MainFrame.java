package swing;

import command.Command;
import interfaces.ImageDisplay;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class MainFrame extends JFrame {

    private ImageDisplay imageDisplay;
    private final Map<String, Command> imageCommands;

    public MainFrame() throws HeadlessException {
        this.imageCommands = new HashMap<>();
        setTitle("Image Viewer");
        //setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setSize(800, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        add(createImageDisplay());
        add(createButtons(), BorderLayout.SOUTH);
    }

    private Component createButtons() {
        JPanel panel = new JPanel();
        panel.add(createButton("<"));
        panel.add(createButton(">"));
        return panel;
    }

    private Component createButton(String option) {
        JButton button = new JButton(option);
        button.addActionListener(e -> imageCommands.get(option).execute());
        return button;
    }

    public void addCommand(String name, Command command)  {
        imageCommands.put(name, command);
    }

    private Component createImageDisplay() {
        SwingImageDisplay display = new SwingImageDisplay();
        this.imageDisplay = display;
        return display;
    }

    public ImageDisplay imageDisplay() {
        return imageDisplay;
    }
}

