package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PricingData {

    private String symbol;
    private String date;
    private double open;
    private double high;
    private double low;
    private double close;
    private long volume;
    @JsonIgnore
    private double movingAverage;


}
