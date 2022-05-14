package ru.itmo.robq.comp_math_3.methods;

import lombok.Setter;
import ru.itmo.robq.comp_math_3.functions.Function;

@Setter
public class SimpsonsMethod implements IntegrationMethod {
    private IntegrationInputData inputData;
    private Function function;

    @Override
    public IntegrationResults calculate() {
        return null;
    }

    @Override
    public String toString() {
        return "Метод Симпсона";
    }
}
