package pl.edu.wat.wcy.isi.tal.gui.menu.fileMenu;

import org.graphstream.graph.Graph;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class FileMenu extends JMenu {

    public FileMenu(Graph graph) {
        super("File");
        initUI(graph);
    }

    private void initUI(Graph graph) {
        this.setMnemonic(KeyEvent.VK_F);

        JMenuItem generateMenuItem = new JMenuItem("Generate", KeyEvent.VK_G);
        generateMenuItem.setToolTipText("Generate new graph");

        JMenuItem exitMenuItem = new JMenuItem("Exit", KeyEvent.VK_E);
        exitMenuItem.setToolTipText("Exit application");

        generateMenuItem.addActionListener(new GenerateActionListener(graph));
        exitMenuItem.addActionListener((event) -> System.exit(0));

        this.add(generateMenuItem);
        this.addSeparator();
        this.add(exitMenuItem);
    }
}
