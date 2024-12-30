import ipify.IpifyClient;
import ipify.models.IPFormat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

import static ipify.models.IPFormat.IPFormatType.Text;

public class IpifyTest {
    private IpifyClient ipifyClient;

    @BeforeEach
    void setUp() {
        ipifyClient = new IpifyClient();
    }

    @Test
    void testGetIpAddress() throws ExecutionException, InterruptedException {
        IPFormat ipFormat = new IPFormat.IPFormatBuilder(Text).build();
        String ipAddress = ipifyClient.getIPAddress(ipFormat);

        Assertions.assertNotNull(ipAddress);
        Assertions.assertFalse(ipAddress.isEmpty());
    }
}
