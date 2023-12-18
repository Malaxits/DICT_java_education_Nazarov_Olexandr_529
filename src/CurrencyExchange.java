import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class CurrencyConverter {
    private static Map<String, Map<String, Double>> cache = new HashMap<>();

    private static Map<String, Double> getExchangeRate(String currency) {
        if (cache.containsKey(currency)) {
            System.out.println("Checking the cache...");
            return cache.get(currency);
        } else {
            System.out.println("Checking the website...");
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(new URL("http://www.floatrates.com/daily/" + currency + ".json"));

                Map<String, Double> exchangeRates = parseExchangeRateJson(jsonNode);
                cache.put(currency, exchangeRates);
                return exchangeRates;
            } catch (IOException e) {
                e.printStackTrace();
                return new HashMap<>();
            }
        }
    }

    private static Map<String, Double> parseExchangeRateJson(JsonNode jsonNode) {
        Map<String, Double> exchangeRates = new HashMap<>();
        Iterator<Map.Entry<String, JsonNode>> fields = jsonNode.fields();
        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> entry = fields.next();
            String code = entry.getKey();
            double rate = entry.getValue().get("rate").asDouble();
            exchangeRates.put(code, rate);
        }
        return exchangeRates;
    }

    private static void exchangeMoney() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the currency code you have (or press Enter to exit): ");
        String inputCurrency = scanner.nextLine();

        while (!inputCurrency.isEmpty()) {
            System.out.print("Enter the currency code you want to exchange to: ");
            String exchangeCurrency = scanner.nextLine();
            System.out.print(String.format("Enter the amount of %s you want to exchange: ", inputCurrency));
            double amount = Double.parseDouble(scanner.nextLine());

            Map<String, Double> exchangeRates = getExchangeRate(inputCurrency);

            if (exchangeRates.containsKey(exchangeCurrency)) {
                double exchangeRate = exchangeRates.get(exchangeCurrency);
                double result = Math.round(amount * exchangeRate * 100.0) / 100.0;

                System.out.println("Checking the cache...");
                if (cache.containsKey(exchangeCurrency)) {
                    System.out.println("It is in the cache!");
                } else {
                    System.out.println("Sorry, but it is not in the cache!");
                }

                System.out.println(String.format("You received %.2f %s.", result, exchangeCurrency));
            } else {
                System.out.println("Exchange rate not available for the specified currency pair.");
            }

            System.out.print("Enter the currency code you have (or press Enter to exit): ");
            inputCurrency = scanner.nextLine();
        }
    }

    public static void main(String[] args) {
        System.out.println("Product by Frodo\nHello");
        System.out.println("--------------------------------------------------------------------------");

        exchangeMoney();

        System.out.println("--------------------------------------------------------------------------");
        System.out.println("Have a nice day!");
    }
}
