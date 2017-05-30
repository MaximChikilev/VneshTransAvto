package ui.newrecord;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public abstract class NewDBRecordFrame extends JFrame {
    protected List<JComboBox> comboBoxes;
    protected JButton yesButton;
    protected JButton noButton;
    private JPanel labelTextFieldPanel;
    private List<JLabel> labelArrayList;
    private List<JTextField> textFieldArrayList;


    public NewDBRecordFrame(int amount) {
        labelTextFieldPanel = new JPanel();
        comboBoxes = new ArrayList<>();
        setLayout(new BorderLayout());
        setSize(250, amount > 1 ? amount * 90 : amount * 90 + 50);
        setLocationRelativeTo(null);
        add(labelTFCreator(amount), BorderLayout.NORTH);
        add(buttonPanelCreator(), BorderLayout.SOUTH);
        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setVisible(true);

    }

    private JPanel labelTFCreator(int amount) {
        labelTextFieldPanel.setLayout(new BoxLayout(labelTextFieldPanel, BoxLayout.Y_AXIS));
        labelArrayList = new ArrayList<>();
        textFieldArrayList = new ArrayList<>();
        for (int i = 0; i <= amount - 1; i++) {
            labelArrayList.add(new JLabel());
            textFieldArrayList.add(new JTextField());
            labelArrayList.get(i).setPreferredSize(new Dimension(200, 30));
            textFieldArrayList.get(i).setPreferredSize(new Dimension(200, 30));
            labelTextFieldPanel.add(labelArrayList.get(i));
            labelTextFieldPanel.add(textFieldArrayList.get(i));
        }
        labelTextFieldPanel.setOpaque(true);
        labelTextFieldPanel.setVisible(true);
        return labelTextFieldPanel;
    }

    private JPanel buttonPanelCreator() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        yesButton = new JButton("Записать");
        noButton = new JButton("Отмена");
        buttonPanel.add(yesButton);
        buttonPanel.add(noButton);
        buttonPanel.setVisible(true);
        return buttonPanel;
    }

    public void setLabelTextFieldName(int arrayIndex, String labelName) {
        labelArrayList.get(arrayIndex).setText(labelName);
    }

    public String getTextFieldValue(int arrayIndex) {
        return textFieldArrayList.get(arrayIndex).getText();
    }

    public void changeTextField(int textFieldIndex, List<JComboBox> comboBox) {
        int arrayComboBoxSize = comboBox.size();
        int useArrayComboBoxElem = 0;

        int elementCount = textFieldArrayList.size() - 1;
        labelTextFieldPanel.removeAll();
        for (int currentElement = 0; currentElement <= elementCount; currentElement++) {
            labelTextFieldPanel.add(labelArrayList.get(currentElement));
            if (currentElement == textFieldIndex) {
                comboBox.get(useArrayComboBoxElem).setPreferredSize(new Dimension(200, 30));
                labelTextFieldPanel.add(comboBox.get(useArrayComboBoxElem));
                if (useArrayComboBoxElem != arrayComboBoxSize - 1) {
                    useArrayComboBoxElem++;
                    textFieldIndex++;
                }
            } else {
                labelTextFieldPanel.add(textFieldArrayList.get(currentElement));
            }
        }
    }

    public abstract boolean checkNewDBRecord();

}
