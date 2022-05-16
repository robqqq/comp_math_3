package ru.itmo.robq.comp_math_3.methods;

import com.opencsv.bean.CsvBindByName;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IntegrationInputData {
    @CsvBindByName(column = "a", required = true)
    private double a;
    @CsvBindByName(column = "b", required = true)
    private double b;
    @CsvBindByName(column = "eps", required = true)
    private double eps;
}
