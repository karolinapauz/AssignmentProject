package service;

import java.io.IOException;
import java.time.LocalDate;

public interface PricingService {
    String getPricingData(String ticker, LocalDate staDate, LocalDate endDate) throws IOException;
}