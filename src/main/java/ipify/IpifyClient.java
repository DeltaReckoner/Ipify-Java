package ipify;

import ipify.models.IPFormat;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * The Java client to make requests to Ipify's web API
 */
public class IpifyClient {
    private HttpClient httpClient;
    private final String BASE_V4_URL = "https://api.ipify.org";
    private final String BASE_V6_URL = "https://api6.ipify.org";

    /**
     * Default constructor
     */
    public IpifyClient() {
        httpClient = HttpClient.newHttpClient();
    }

    /**
     * Gets your IPv4 address. Supports Text, Json, or JsonP format.
     * @param format IPFormat
     * @return String representing IPv4 address.
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public String getIPAddress(IPFormat format) throws ExecutionException, InterruptedException {
        String requestUrl = BASE_V4_URL +
                "?format=" + format.getIpFormatType().toString().toLowerCase(Locale.ROOT);

        return fetchIPAddress(format, requestUrl);
    }

    /**
     * Gets your IPv6 address. Supports Text, Json, or JsonP format.
     * Throws a ExecutionException if your ISP does not have a IPv6 address.
     * @param format IPFormat
     * @return String representing IPv4 address.
     * @throws ExecutionException Can occur if your ISP does not have a IPv6 address.
     * @throws InterruptedException
     */
    public String getIPv6Address(IPFormat format) throws ExecutionException, InterruptedException {
        String requestUrl = BASE_V6_URL +
                "?format=" + format.getIpFormatType().toString().toLowerCase(Locale.ROOT);

        return fetchIPAddress(format, requestUrl);
    }

    private String fetchIPAddress(IPFormat format, String requestUrl) throws ExecutionException, InterruptedException {
        if (format.getCallback() != null) {
            requestUrl = requestUrl + "&callback=" + format.getCallback();
        }

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(requestUrl))
                .header("User-Agent", "Ipify-Java")
                .GET()
                .build();

        CompletableFuture<HttpResponse<String>> response = httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());
        return response.thenApply(HttpResponse::body).get();
    }
}
