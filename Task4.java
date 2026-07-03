import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Task4: Currency Converter Application
 * Professional conversion using BigDecimal and a simple CLI.
 */
public class Task4 {

    // Internal registry matching currency codes to their exchange rates relative to USD (Base)
    private final Map<String, BigDecimal> exchangeRatesToUsd = new HashMap<>();
    // Symbol mappings for console presentation
    private final Map<String, String> currencySymbols = new HashMap<>();

    public Task4() {
        initializeRates();
    }

    private void initializeRates() {
        // Rates relative to 1 USD (mocked)
        exchangeRatesToUsd.put("USD", new BigDecimal("1.0000"));
        exchangeRatesToUsd.put("EUR", new BigDecimal("0.9250"));
        exchangeRatesToUsd.put("GBP", new BigDecimal("0.7850"));
        exchangeRatesToUsd.put("INR", new BigDecimal("83.5000"));
        exchangeRatesToUsd.put("JPY", new BigDecimal("156.2000"));

        currencySymbols.put("USD", "$");
        currencySymbols.put("EUR", "€");
        currencySymbols.put("GBP", "£");
        currencySymbols.put("INR", "₹");
        currencySymbols.put("JPY", "¥");
    }

    public BigDecimal convert(String fromCurrency, String toCurrency, BigDecimal amount) {
        if (!exchangeRatesToUsd.containsKey(fromCurrency) || !exchangeRatesToUsd.containsKey(toCurrency)) {
            throw new IllegalArgumentException("Unsupported currency code provided.");
        }

        BigDecimal amountInUsd = amount.divide(exchangeRatesToUsd.get(fromCurrency), 6, RoundingMode.HALF_UP);
        return amountInUsd.multiply(exchangeRatesToUsd.get(toCurrency)).setScale(2, RoundingMode.HALF_UP);
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=========================================");
        System.out.println("    TASK 4: CURRENCY CONVERTER");
        System.out.println("=========================================");

        while (true) {
            displaySupportedCurrencies();

            String sourceCurrency = getValidCurrencyCode(scanner, "Select SOURCE (From) currency code: ");
            String targetCurrency = getValidCurrencyCode(scanner, "Select TARGET (To) currency code: ");
            BigDecimal amount = getValidAmount(scanner);

            try {
                BigDecimal result = convert(sourceCurrency, targetCurrency, amount);
                String srcSymbol = currencySymbols.get(sourceCurrency);
                String tgtSymbol = currencySymbols.get(targetCurrency);

                System.out.println("\n-----------------------------------------");
                System.out.printf(" %s%,.2f (%s) => %s%,.2f (%s)\n", srcSymbol, amount, sourceCurrency, tgtSymbol, result, targetCurrency);
                System.out.println("-----------------------------------------");

            } catch (Exception e) {
                System.out.println("[ERROR] " + e.getMessage());
            }

            System.out.print("\nPerform another conversion? (yes/no): ");
            String choice = scanner.next().trim().toLowerCase();
            if (!choice.startsWith("y")) {
                break;
            }
        }

        System.out.println("\nGoodbye.");
        scanner.close();
    }

    private void displaySupportedCurrencies() {
        System.out.println("\nSupported Currencies:");
        for (String code : exchangeRatesToUsd.keySet()) {
            System.out.printf(" • %s (%s)\n", code, currencySymbols.get(code));
        }
        System.out.println();
    }

    private String getValidCurrencyCode(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.next().trim().toUpperCase();
            if (exchangeRatesToUsd.containsKey(input)) {
                return input;
            }
            System.out.println("[INVALID] Currency code not supported.");
        }
    }

    private java.math.BigDecimal getValidAmount(Scanner scanner) {
        while (true) {
            System.out.print("Enter the amount to convert: ");
            try {
                java.math.BigDecimal amount = scanner.nextBigDecimal();
                if (amount.compareTo(java.math.BigDecimal.ZERO) < 0) {
                    System.out.println("[INVALID] Amount cannot be negative.");
                    continue;
                }
                return amount;
            } catch (Exception e) {
                System.out.println("[INVALID] Please enter a valid decimal number.");
                scanner.next();
            }
        }
    }

    public static void main(String[] args) {
        Task4 app = new Task4();
        app.start();
    }
}
