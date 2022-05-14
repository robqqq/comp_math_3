package ru.itmo.robq.comp_math_3.methods;

import com.opencsv.bean.CsvBindByName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IntegrationResults {
    @CsvBindByName(column = "value", required = true)
    private double value;
    @CsvBindByName(column = "n", required = true)
    private double n;
    @CsvBindByName(column = "error", required = true)
    private double error;
}
