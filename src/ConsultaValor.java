import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConsultaValor {
    private String url = "https://v6.exchangerate-api.com/v6/e16bb3bdeb2d7d197a08bfc6/latest/USD";
    public Divisa[] obtenerMonedas() {
        Divisa[] monedas = new Divisa[5];

        try {
            String urlString = url;
            URL url = new URL(urlString);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            String jsonResponse = response.toString();
            String[] monedasCodigos = {"PEN", "ARS", "BRL", "COP", "VES"};
            for (int i = 0; i < monedasCodigos.length; i++) {
                double tasaDeCambio = parseTasaDeCambio(jsonResponse, monedasCodigos[i]);
                monedas[i] = new Divisa(monedasCodigos[i], tasaDeCambio);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return monedas;
    }

    private double parseTasaDeCambio(String jsonResponse, String moneda)
    {

        String key = "\"" + moneda + "\":";
        int index = jsonResponse.indexOf(key);
        if (index != -1) {

            int startIndex = index + key.length();
            int endIndex = jsonResponse.indexOf(",", startIndex);
            String tasaString = jsonResponse.substring(startIndex, endIndex);
            return Double.parseDouble(tasaString);
        }
        return 0;
    }
}
