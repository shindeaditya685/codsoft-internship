package org.task.temp.task_four;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CurrencyConverter {

    public static void main(String[] args) {
        try {
            // Step 1: Allow the user to choose the base and target currencies
            String baseCurrency = getUserInput("Enter the base currency code (e.g., USD): ");
            String targetCurrency = getUserInput("Enter the target currency code (e.g., EUR): ");

            // Step 2: Fetch real-time exchange rates from a reliable API
            double exchangeRate = getExchangeRate(baseCurrency, targetCurrency);

            // Step 3: Take input from the user for the amount they want to convert
            double amountToConvert = Double.parseDouble(getUserInput("Enter the amount to convert: "));

            // Step 4: Convert the input amount using the fetched exchange rate
            double convertedAmount = amountToConvert * exchangeRate;

            // Step 5: Display the result to the user
            System.out.println(amountToConvert + " " + baseCurrency + " is equal to " + convertedAmount + " " + targetCurrency);
        } catch (IOException e) {
            System.err.println("Error: Unable to fetch exchange rates. Please try again later.");
        }
    }

    // Method to get user input
    private static String getUserInput(String prompt) throws IOException {
        System.out.print(prompt);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        return reader.readLine();
    }

    // Method to fetch real-time exchange rates
    private static double getExchangeRate(String baseCurrency, String targetCurrency) throws IOException {
        String apiKey = "YOUR_EXCHANGE_RATE_API_KEY";  // Replace with your actual API key
        String apiUrl = "https://open.er-api.com/v6/latest/" + baseCurrency + "?apikey=" + apiKey;

        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Set up the request
        connection.setRequestMethod("GET");

        // Get the response
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            // Parse JSON response to get the exchange rate for the target currency
            String jsonResponse = response.toString();
            double exchangeRate = parseExchangeRate(jsonResponse, targetCurrency);

            // Close resources
            reader.close();
            connection.disconnect();

            return exchangeRate;
        } else {
            throw new IOException("Failed to fetch exchange rates. Response Code: " + responseCode);
        }
    }

    // Method to parse exchange rates from the JSON response
    private static double parseExchangeRate(String jsonResponse, String targetCurrency) {
        // This is a simplified example; in a real-world scenario, you would use a JSON parsing library
        // such as Jackson or Gson for more robust handling.
        String targetRatePattern = "\"" + targetCurrency + "\":(\\d+\\.\\d+)";
        String targetRateString = jsonResponse.replaceAll(".*" + targetRatePattern + ".*", "$1");
        return Double.parseDouble(targetRateString);
    }
}

