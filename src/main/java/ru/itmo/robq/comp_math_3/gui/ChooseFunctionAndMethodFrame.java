package ru.itmo.robq.comp_math_3.gui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itmo.robq.comp_math_3.functions.FirstFunction;
import ru.itmo.robq.comp_math_3.functions.Function;
import ru.itmo.robq.comp_math_3.functions.SecondFunction;
import ru.itmo.robq.comp_math_3.functions.ThirdFunction;
import ru.itmo.robq.comp_math_3.methods.IntegrationMethod;
import ru.itmo.robq.comp_math_3.methods.SimpsonsMethod;
import ru.itmo.robq.comp_math_3.methods.TrapezoidMethod;

import javax.swing.*;

@Component
public class ChooseFunctionAndMethodFrame extends CustomFrame {
    private final ChooseInputDataFrame chooseInputDataFrame;
    private final Function[] functions;
    private final IntegrationMethod[] methods;

    @Autowired
    public ChooseFunctionAndMethodFrame(ChooseInputDataFrame chooseInputDataFrame) {
        super();
        this.chooseInputDataFrame = chooseInputDataFrame;
        functions = new Function[3];
        functions[0] = new FirstFunction();
        functions[1] = new SecondFunction();
        functions[2] = new ThirdFunction();
        methods = new IntegrationMethod[2];
        methods[0] = new SimpsonsMethod();
        methods[1] = new TrapezoidMethod();
        initUI();
    }

    private void initUI(){
        JLabel chooseFunctionLabel = new JLabel("Выберите функцию:");
        ButtonGroup functionButtonGroup = new ButtonGroup();
        for (Function function : functions) {
            JRadioButton button = new JRadioButton(function.toString());
            functionButtonGroup.add(button);
        }
        JLabel chooseMethodLabel = new JLabel("Выберите метод:");
        ButtonGroup methodButtonGroup = new ButtonGroup();
        for (IntegrationMethod method : methods) {
            JRadioButton button = new JRadioButton(method.toString());
            methodButtonGroup.add(button);
        }
        JButton okButton = new JButton("OK");
        okButton.addActionListener((ActionEvent) -> onOkButtonClick());
        JButton exitButton = new JButton("Выход");
        exitButton.addActionListener((ActionEvent) -> System.exit(0));
        addComponent(chooseFunctionLabel);
        addButtonGroup(functionButtonGroup);
        addComponent(chooseMethodLabel);
        addButtonGroup(methodButtonGroup);
        addHorizontalComponents(okButton, exitButton);
    }
    private void onOkButtonClick() {
        chooseInputDataFrame.setVisible(true);
    }
}
