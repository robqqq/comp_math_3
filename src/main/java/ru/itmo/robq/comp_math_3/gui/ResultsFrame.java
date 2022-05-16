package ru.itmo.robq.comp_math_3.gui;

import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import org.springframework.stereotype.Component;

import ru.itmo.robq.comp_math_3.methods.IntegrationResults;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.FileWriter;
import java.io.IOException;


@Component
public class ResultsFrame extends CustomFrame {
    private IntegrationResults results;
    private JLabel valueLabel;
    private JLabel nLabel;
    private JLabel errorLabel;

    public void setResults (IntegrationResults results) {
        this.results = results;
        valueLabel.setText("I = " + results.getValue());
        nLabel.setText("n = " + results.getN());
        errorLabel.setText("error = " + results.getError());
    }

    public ResultsFrame() {
        super();
        initUI();
    }

    private void initUI() {
        valueLabel = new JLabel("I = ");
        nLabel = new JLabel("n = ");
        errorLabel = new JLabel("error = ");
        JButton okButton = new JButton("OK");
        JButton saveToFileButton = new JButton("Сохранить в файл");
        okButton.addActionListener((ActionEvent) -> setVisible(false));
        saveToFileButton.addActionListener((ActionEvent) -> onSaveToFileButtonClick());
        addHorizontalComponents(valueLabel, nLabel, errorLabel);
        addHorizontalComponents(okButton, saveToFileButton);
    }

    private void onSaveToFileButtonClick() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("CSV File", "csv"));
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            if (!filePath.substring(filePath.lastIndexOf(".") + 1).equals("csv"))
                filePath += ".csv";
            try {
                FileWriter writer = new FileWriter(filePath);
                HeaderColumnNameMappingStrategy<IntegrationResults> strategy = new HeaderColumnNameMappingStrategy<>();
                strategy.setType(IntegrationResults.class);
                new StatefulBeanToCsvBuilder<IntegrationResults>(writer)
                        .withMappingStrategy(strategy)
                        .withApplyQuotesToAll(false)
                        .build()
                        .write(results);
                writer.close();
            } catch (IOException | CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e) {
                JOptionPane.showMessageDialog(this,
                        "Не удалось записать в файл",
                        "Ошибка",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
