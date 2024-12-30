## Ipify Java

### A Java API wrapper for [Ipify](https://www.ipify.org/). Ipify lets you get your IP Address in Text or JSON format.

### Note: If you do not have a IPv6 address, getIPv6Address() will throw an exception

### Sample Use
```java
import ipify.IpifyClient;
import ipify.models.IPFormat;

import static ipify.models.IPFormat.IPFormatType.Text;
import static ipify.models.IPFormat.IPFormatType.JsonP;

public class APITestingGradle {
    public static void main(String[] args) {
        // Initialize IpifyClient client
        IpifyClient ipifyClient = new IpifyClient();

        try {
            // Get IPv4 address in Text format
            IPFormat ipFormatText = new IPFormat.IPFormatBuilder(Text).build();
            String ipAddressText = ipifyClient.getIPAddress(ipFormatText);
            System.out.println(ipAddressText); // Output: XXX.XXX.XXX.XXX

            // Get IPv4 address in JsonP format
            IPFormat ipFormatJsonP = new IPFormat.IPFormatBuilder(JsonP)
                    .setCallback("getIp")
                    .build();
            String ipAddressJsonP = ipifyClient.getIPAddress(ipFormatJsonP);
            System.out.println(ipAddressJsonP); // Output: getIp({"ip":"XXX.XXX.XXX.XXX"});
        } catch (Exception e) {
            System.out.printf("An error has occurred: %s", e.getMessage());
        }
    }
}
```