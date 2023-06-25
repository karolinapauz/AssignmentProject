package service;



import entity.PricingData;

import java.util.List;

public class ConvertToCsv {
    public String convertToCSV(List<PricingData> data) {
        StringBuilder csvBuilder = new StringBuilder();
        csvBuilder.append("ticker,date,open,high,low,close,volume,movingAverage\n");
        for (PricingData pricingData : data) {
            csvBuilder.append(pricingData.getSymbol()).append(",");
            csvBuilder.append(pricingData.getDate()).append(",");
            csvBuilder.append(pricingData.getOpen()).append(",");
            csvBuilder.append(pricingData.getHigh()).append(",");
            csvBuilder.append(pricingData.getLow()).append(",");
            csvBuilder.append(pricingData.getClose()).append(",");
            csvBuilder.append(pricingData.getVolume()).append(",");
            csvBuilder.append(pricingData.getMovingAverage()).append("\n");
        }
        return csvBuilder.toString();
    }
}
