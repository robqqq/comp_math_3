package ru.itmo.robq.comp_math_3.functions;

public class ThirdFunction implements Function {
    @Override
    public double getValue(double x) {
        return 5 * x * x * x - 3 * x * x + 0.5 * x - 1;
    }

    @Override
    public String toString() {
        return "5 * x^3 - 3 * x^2 + 0.5 * x - 1";
    }
}
