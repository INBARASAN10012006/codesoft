import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONObject;

public class CurrencyConverter {

    // Replace with your own API key
    static final String API_KEY = "YOUR_API_KEY_HERE";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. Currency selection
        System.out.print("Enter base currency (e.g., USD): ");
        String baseCurrency = scanner.next().toUpperCase();

        System.out.print("Enter target currency (e.g., INR): ");
        String targetCurrency = scanner.next().toUpperCase();

        // 2. Amount input
        System.out.print("Enter amount to convert: ");
        double amount = scanner.nextDouble();

        try {
            // 3. API Call
            String urlStr = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/" + baseCurrency;
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();

            // 4. Read JSON response
            if (responseCode == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = in.readLine()) != null) {
                    response.append(line);
                }
                in.close();

                JSONObject jsonObject = new JSONObject(response.toString());
                JSONObject conversionRates = jsonObject.getJSONObject("conversion_rates");

                if (conversionRates.has(targetCurrency)) {
                    double rate = conversionRates.getDouble(targetCurrency);

                    // 5. Conversion and output
                    double convertedAmount = amount * rate;
                    System.out.printf("Converted Amount: %.2f %s%n", convertedAmount, targetCurrency);
                } else {
                    System.out.println("Invalid target currency.");
                }
            } else {
                System.out.println("Failed to fetch exchange rates. HTTP Response Code: " + responseCode);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        scanner.close();
    }
}
