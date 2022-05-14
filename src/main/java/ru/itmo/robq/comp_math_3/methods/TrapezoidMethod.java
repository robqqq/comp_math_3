package ru.itmo.robq.comp_math_3.methods;

import lombok.Setter;
import ru.itmo.robq.comp_math_3.functions.Function;

@Setter
public class TrapezoidMethod implements IntegrationMethod {
    private IntegrationInputData inputData;
    private Function function;

    @Override
    public IntegrationResults calculate() {
        int n = 4;
        double i0 = integrate(n);
        double i1;
        do {
            n *= 2;
            i1 = integrate(n);
        } while (Math.abs(i1 - i0) <= inputData.getEps());
        return IntegrationResults.builder().value(i1).n(n).error(Math.abs(i1 - i0)).build();
    }

    private double integrate(int n) {
        double result = (function.getValue(inputData.getA()) + function.getValue(inputData.getB())) / 2;
        double h = (inputData.getB() - inputData.getA()) / n;
        for (int i = 1; i < n; i++) {
            result += function.getValue(inputData.getA() + i * h);
        }
        result *= h;
        return result;
    }

    @Override
    public String toString() {
        return "Метод трапеций";
    }
}
