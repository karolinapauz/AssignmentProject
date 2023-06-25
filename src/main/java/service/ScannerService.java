package service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class ScannerService {
    Scanner scanner = new Scanner(System.in);
    public void getInput() {
        System.out.println("Enter the ticker: ");
        String symbol = scanner.nextLine();

        System.out.println("Enter the start date (YYYY-MM-DD):");
        String startDateStr = scanner.nextLine();
        LocalDate startDate = LocalDate.parse(startDateStr);

        System.out.println("Enter the end date (YYYY-MM-DD):");
        String endDateStr = scanner.nextLine();
        LocalDate endDate = LocalDate.parse(endDateStr);

        PricingService pricingService = new PricingServiceImpl();
        try {
            String pricingData = pricingService.getPricingData(symbol, startDate, endDate);
            System.out.println(pricingData);
        } catch (
                IOException e) {
            System.out.println("An error occurred while fetching pricing data.");
            e.printStackTrace();
        }
    }
}
