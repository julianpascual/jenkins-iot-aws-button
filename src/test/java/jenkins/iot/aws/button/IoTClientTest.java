package jenkins.iot.aws.button;

import org.junit.Test;

/**
 * Created by Julian on 30/4/17.
 */
public class IoTClientTest {


    @Test
    public void executeFailBuilds() throws Exception {
        IoTClient client = new IoTClient();
        String userName = System.getProperty("userName");
        String token = System.getProperty("token");
        String ciServerUri = System.getProperty("ciServerUri");
        client.executeFailBuilds(ciServerUri, userName, token);
    }

}