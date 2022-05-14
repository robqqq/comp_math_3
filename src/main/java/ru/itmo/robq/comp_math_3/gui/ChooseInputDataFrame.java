package ru.itmo.robq.comp_math_3.gui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itmo.robq.comp_math_3.functions.Function;
import ru.itmo.robq.comp_math_3.methods.IntegrationMethod;

import javax.swing.*;
import java.awt.*;

@Component
public class ChooseInputDataFrame extends CustomFrame {
    private final ResultsFrame resultsFrame;
    private IntegrationMethod method;
    private Function function;
    private JTextField aTextField;
    private JTextField bTextField;
    private JTextField epsTextField;

    @Autowired
    public ChooseInputDataFrame(ResultsFrame resultsFrame) {
        super();
        this.resultsFrame = resultsFrame;
        initUI();
    }

    private void initUI() {
        JLabel inputIntegrationLimitsLabel = new JLabel("Введите пределы интегрирования");
        JLabel aLabel = new JLabel("a =");
        JLabel bLabel = new JLabel("b =");
        aTextField = new JTextField();
        bTextField = new JTextField();
        aTextField.setMaximumSize(new Dimension(300, 30));
        bTextField.setMaximumSize(new Dimension(300, 30));
        JLabel inputAccuracyLabel = new JLabel("Введите точность:");
        JLabel epsLabel = new JLabel("eps =");
        epsTextField = new JTextField();
        epsTextField.setMaximumSize(new Dimension(300,30 ));
        JButton okButton = new JButton("OK");
        okButton.addActionListener((ActionEvent) -> onOkButtonClick());
        JButton chooseFileButton = new JButton("Выбрать файл");
        chooseFileButton.addActionListener((ActionEvent) -> onChooseFileButtonClick());
        JButton cancelButton = new JButton("Отмена");
        cancelButton.addActionListener((ActionEvent) -> setVisible(false));
        addComponent(inputIntegrationLimitsLabel);
        addHorizontalComponents(aLabel, aTextField, bLabel, bTextField);
        addComponent(inputAccuracyLabel);
        addHorizontalComponents(epsLabel, epsTextField);
        addHorizontalComponents(okButton, chooseFileButton, cancelButton);
    }

    private void onOkButtonClick() {
        setVisible(false);
        resultsFrame.setVisible(true);
    }

    private void onChooseFileButtonClick() {

    }
}
