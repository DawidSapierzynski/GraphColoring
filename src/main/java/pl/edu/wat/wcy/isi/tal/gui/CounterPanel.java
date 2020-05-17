package pl.edu.wat.wcy.isi.tal.gui;

import javax.swing.*;
import java.awt.*;

public class CounterPanel extends JPanel {
    private int nodeCount = 0;
    private int edgeCount = 0;
    private int colorCount = 0;
    private long stepCount = 0;
    private long amountOfSpace = 0;

    private JLabel nodeCountLabel;
    private JLabel edgeCountLabel;
    private JLabel colorCountLabel;
    private JLabel stepCountLabel;
    private JLabel amountOfSpaceLabel;

    public CounterPanel() {
        initUI();
        update();
    }

    private void initUI() {
        this.nodeCountLabel = new JLabel();
        this.edgeCountLabel = new JLabel();
        this.colorCountLabel = new JLabel();
        this.stepCountLabel = new JLabel();
        this.amountOfSpaceLabel = new JLabel();

        this.add(this.nodeCountLabel);
        this.add(this.edgeCountLabel);
        this.add(this.colorCountLabel);
        this.add(this.stepCountLabel);
        this.add(this.amountOfSpaceLabel);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);
    }

    public void update() {
        this.nodeCountLabel.setText("Nodes: " + this.nodeCount);
        this.edgeCountLabel.setText("Edges: " + this.edgeCount);
        this.colorCountLabel.setText("Colors: " + this.colorCount);
        this.stepCountLabel.setText("Steps: " + this.stepCount);
        this.amountOfSpaceLabel.setText("Space: " + this.amountOfSpace);
    }

    public void setNodeEdgeCount(int nodeCount, int edgeCount) {
        this.nodeCount = nodeCount;
        this.edgeCount = edgeCount;
        this.colorCount = 0;
        this.stepCount = 0;
        this.amountOfSpace = 0;
        update();
    }

    public void setStepColorSpaceCount(long stepCount, int colorCount, long amountOfSpace) {
        this.stepCount = stepCount;
        this.colorCount = colorCount;
        this.amountOfSpace = amountOfSpace;
        update();
    }
}
