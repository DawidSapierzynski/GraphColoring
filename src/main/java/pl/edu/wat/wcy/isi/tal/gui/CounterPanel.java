package pl.edu.wat.wcy.isi.tal.gui;

import javax.swing.*;
import java.awt.*;

public class CounterPanel extends JPanel {
    private static CounterPanel INSTANCE = null;

    private int nodeCount = 0;
    private int edgeCount = 0;
    private int colorCount = 0;
    private long stepCount = 0;

    private JLabel nodeCountLabel;
    private JLabel edgeCountLabel;
    private JLabel colorCountLabel;
    private JLabel stepCountLabel;


    private CounterPanel() {
        initUI();
        update();
    }

    private void initUI() {
        this.nodeCountLabel = new JLabel();
        this.edgeCountLabel = new JLabel();
        this.colorCountLabel = new JLabel();
        this.stepCountLabel = new JLabel();

        this.add(nodeCountLabel);
        this.add(edgeCountLabel);
        this.add(colorCountLabel);
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
        colorCountLabel.setText("Colors: " + colorCount);
        stepCountLabel.setText("Steps: " + stepCount);
    }

    public void setNodeEdgeCount(int nodeCount, int edgeCount) {
        this.nodeCount = nodeCount;
        this.edgeCount = edgeCount;
        this.colorCount = 0;
        this.stepCount = 0;
        update();
    }

    public void setStepColorCount(long stepCount, int colorCount) {
        this.stepCount = stepCount;
        this.colorCount = colorCount;
        update();
    }
}
