package ru.itmo.robq.comp_math_3.functions;

public class FirstFunction implements Function {

    @Override
    public double getValue(double x) {
        return 3 * x * x - x;
    }

    @Override
    public String toString() {
        return "3 * x^2 - x";
    }
}
