package service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.ApiResult;
import entity.PricingData;
import lombok.RequiredArgsConstructor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@RequiredArgsConstructor
public class PricingServiceImpl implements PricingService {
    private final CalculationService calculator = new CalculationService();
    private final ConvertToCsv converter = new ConvertToCsv();
    private final String apiKey = "ec9f5fbd4392951d6a40d8021708a683";

    @Override
    public String getPricingData(String symbol, LocalDate startDate, LocalDate endDate) throws IOException {
        URL url = buildURL(symbol, startDate, endDate);

        ObjectMapper jsonMapper = new ObjectMapper();
        jsonMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {

            ApiResult result = jsonMapper.readValue(reader, ApiResult.class);
            result.getHistorical().sort(Comparator.comparing(PricingData::getDate));

            List<PricingData> data = result.getHistorical();
            for (PricingData pricingData : data) {
                pricingData.setSymbol(result.getSymbol());
            }

            calculator.calculateMovingAverage(data);

            return converter.convertToCSV(data);
        }
    }

    private URL buildURL(String symbol, LocalDate startDate, LocalDate endDate) throws IOException {
        return new URL("https://financialmodelingprep.com/api/v3/historical-price-full/" + symbol + "?from=" 
                        + startDate + "&to=" + endDate + "&apikey=" + apiKey);
    }
}
