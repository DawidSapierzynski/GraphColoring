package pl.edu.wat.wcy.isi.tal.gui.menu.fileMenu;

import org.graphstream.graph.Graph;
import pl.edu.wat.wcy.isi.tal.gui.CounterPanel;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class FileMenu extends JMenu {
    public FileMenu(Graph graph, CounterPanel counterPanel) {
        super("File");
        initUI(graph, counterPanel);
    }

    private void initUI(Graph graph, CounterPanel counterPanel) {
        this.setMnemonic(KeyEvent.VK_F);

        JMenuItem generateMenuItem = new JMenuItem("Generate graph", KeyEvent.VK_G);
        generateMenuItem.setToolTipText("Generate new graph");

        JMenuItem exitMenuItem = new JMenuItem("Exit", KeyEvent.VK_E);
        exitMenuItem.setToolTipText("Exit application");

        generateMenuItem.addActionListener(new GenerateActionListener(graph, counterPanel));
        exitMenuItem.addActionListener((event) -> System.exit(0));

        this.add(generateMenuItem);
        this.addSeparator();
        this.add(exitMenuItem);
    }
}
