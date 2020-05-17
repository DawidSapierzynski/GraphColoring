package pl.edu.wat.wcy.isi.tal.gui;

import javax.swing.*;
import java.awt.*;

public class CounterPanel extends JPanel {
    private static CounterPanel INSTANCE = null;

    private int nodeCount = 0;
    private int edgeCount = 0;
    private int colorCount = 0;
    private long stepCount = 0;
    private long memoryCount = 0;

    private JLabel nodeCountLabel;
    private JLabel edgeCountLabel;
    private JLabel colorCountLabel;
    private JLabel stepCountLabel;
    private JLabel memoryCountLabel;


    private CounterPanel() {
        initUI();
        update();
    }

    private void initUI() {
        this.nodeCountLabel = new JLabel();
        this.edgeCountLabel = new JLabel();
        this.colorCountLabel = new JLabel();
        this.stepCountLabel = new JLabel();
        this.memoryCountLabel = new JLabel();

        this.add(this.nodeCountLabel);
        this.add(this.edgeCountLabel);
        this.add(this.colorCountLabel);
        this.add(this.stepCountLabel);
        this.add(this.memoryCountLabel);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);
    }

    public static CounterPanel getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CounterPanel();
        }
        return INSTANCE;
    }

    public void update() {
        this.nodeCountLabel.setText("Nodes: " + this.nodeCount);
        this.edgeCountLabel.setText("Edges: " + this.edgeCount);
        this.colorCountLabel.setText("Colors: " + this.colorCount);
        this.stepCountLabel.setText("Steps: " + this.stepCount);
        this.memoryCountLabel.setText("Memories: " + this.memoryCount);
    }

    public void setNodeEdgeCount(int nodeCount, int edgeCount) {
        this.nodeCount = nodeCount;
        this.edgeCount = edgeCount;
        this.colorCount = 0;
        this.stepCount = 0;
        this.memoryCount = 0;
        update();
    }

    public void setStepColorMemoryCount(long stepCount, int colorCount, long memoryCount) {
        this.stepCount = stepCount;
        this.colorCount = colorCount;
        this.memoryCount = memoryCount;
        update();
    }
}
