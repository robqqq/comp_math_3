package ru.itmo.robq.comp_math_3;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import ru.itmo.robq.comp_math_3.gui.ChooseFunctionAndMethodFrame;

import javax.swing.*;
import java.awt.*;

@SpringBootApplication
public class CompMath3Application {

    public static void main(String[] args) {
        ApplicationContext ctx = new SpringApplicationBuilder(CompMath3Application.class)
                .headless(false).run(args);

        EventQueue.invokeLater(() -> {
            JFrame mainFrame = ctx.getBean(ChooseFunctionAndMethodFrame.class);
            mainFrame.setVisible(true);
        });
    }

}
