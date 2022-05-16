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
    private ButtonGroup methodButtonGroup;
    private ButtonGroup functionButtonGroup;

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
        functionButtonGroup = new ButtonGroup();
        for (int i = 0; i < functions.length; i++) {
            JRadioButton button = new JRadioButton(functions[i].toString());
            button.setMnemonic(i);
            functionButtonGroup.add(button);
        }
        JLabel chooseMethodLabel = new JLabel("Выберите метод:");
        methodButtonGroup = new ButtonGroup();
        for (int i = 0; i < methods.length; i++) {
            JRadioButton button = new JRadioButton(methods[i].toString());
            button.setMnemonic(i);
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
        IntegrationMethod method = methods[methodButtonGroup.getSelection().getMnemonic()];
        Function function = functions[functionButtonGroup.getSelection().getMnemonic()];
        method.setFunction(function);
        chooseInputDataFrame.setMethod(method);
        chooseInputDataFrame.setVisible(true);
    }
}
