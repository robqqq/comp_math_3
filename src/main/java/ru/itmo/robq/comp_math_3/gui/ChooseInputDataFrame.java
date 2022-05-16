package ru.itmo.robq.comp_math_3.gui;


import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Setter;
import ru.itmo.robq.comp_math_3.methods.IntegrationInputData;
import ru.itmo.robq.comp_math_3.methods.IntegrationMethod;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

@Component
public class ChooseInputDataFrame extends CustomFrame {
    private final ResultsFrame resultsFrame;
    @Setter
    private IntegrationMethod method;
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
        double a;
        double b;
        double eps;
        try {
            a = Double.parseDouble(aTextField.getText().replace(',', '.').trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Значение а должно быть числом",
                    "Ошибка",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            b = Double.parseDouble(bTextField.getText().replace(',', '.').trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Значение b должно быть числом",
                    "Ошибка",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            eps = Math.abs(Double.parseDouble(epsTextField.getText().replace(',', '.').trim()));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Значение eps должно быть числом",
                    "Ошибка",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        IntegrationInputData inputData = IntegrationInputData.builder().a(a).b(b).eps(eps).build();
        method.setInputData(inputData);
        resultsFrame.setResults(method.calculate());
        setVisible(false);
        resultsFrame.setVisible(true);
    }

    private void onChooseFileButtonClick() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(false);
        fileChooser.setFileFilter(new FileNameExtensionFilter("CSV File", "csv"));
        int ret = fileChooser.showOpenDialog(this);
        if (ret == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                IntegrationInputData inputData = new CsvToBeanBuilder<IntegrationInputData>(new FileReader(file))
                        .withType(IntegrationInputData.class)
                        .build()
                        .parse()
                        .get(0);
                aTextField.setText(String.valueOf(inputData.getA()));
                bTextField.setText(String.valueOf(inputData.getB()));
                epsTextField.setText(String.valueOf(inputData.getEps()));
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(this,
                        "Файл не найден",
                        "Ошибка",
                        JOptionPane.ERROR_MESSAGE);
            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(this,
                        "Неверный CSV формат",
                        "Ошибка",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
