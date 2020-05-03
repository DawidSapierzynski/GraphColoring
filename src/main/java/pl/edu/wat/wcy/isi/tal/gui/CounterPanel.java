package pl.edu.wat.wcy.isi.tal.gui;

import javax.swing.*;
import java.awt.*;

public class CounterPanel extends JPanel {
    private static CounterPanel INSTANCE = null;

    private int nodeCount = 0;
    private int edgeCount = 0;
    private int stepCount = 0;
    private JLabel nodeCountLabel;
    private JLabel edgeCountLabel;
    private JLabel stepCountLabel;

    private CounterPanel() {
        initUI();
        update();
    }

    private void initUI() {
        this.nodeCountLabel = new JLabel();
        this.edgeCountLabel = new JLabel();
        this.stepCountLabel = new JLabel();
        this.add(nodeCountLabel);
        this.add(edgeCountLabel);
        this.add(stepCountLabel);
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
        nodeCountLabel.setText("Nodes: " + nodeCount);
        edgeCountLabel.setText("Edges: " + edgeCount);
        stepCountLabel.setText("Steps: " + stepCount);
    }

    public void setNodeEdgeCount(int nodeCount, int edgeCount) {
        this.nodeCount = nodeCount;
        this.edgeCount = edgeCount;
        this.stepCount = 0;
        update();
    }

    public void setStepCount(int stepCount) {
        this.stepCount = stepCount;
        update();
    }
}
