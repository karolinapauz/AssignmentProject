package service;

import entity.PricingData;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class CalculationService {
    public void calculateMovingAverage(List<PricingData> data) {
        final int period = 5;

        for (int i = 0; i < data.size(); i++) {
            if (i >= period) {
                double sum = 0.0;
                for (int j = i - period; j < i; j++) {
                    sum += data.get(j).getClose();
                }
                double average = sum / period;

                BigDecimal bd = new BigDecimal(Double.toString(average));
                bd = bd.setScale(2, RoundingMode.HALF_UP);
                average = bd.doubleValue();

                data.get(i).setMovingAverage(average);
            }
        }
    }


}
