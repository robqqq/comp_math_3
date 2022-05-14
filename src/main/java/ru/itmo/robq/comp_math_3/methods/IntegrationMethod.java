package ru.itmo.robq.comp_math_3.methods;

import ru.itmo.robq.comp_math_3.functions.Function;

public interface IntegrationMethod {

    void setInputData(IntegrationInputData inputData);
    void setFunction(Function function);
    IntegrationResults calculate();
}
