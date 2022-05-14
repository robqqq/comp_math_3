package ru.itmo.robq.comp_math_3.functions;

public class SecondFunction implements Function {

    @Override
    public double getValue(double x) {
        return x * Math.cos(x);
    }

    @Override
    public String toString() {
        return "x * cosx";
    }
}
