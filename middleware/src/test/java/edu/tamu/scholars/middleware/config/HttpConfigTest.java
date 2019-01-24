package edu.tamu.scholars.middleware.config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class HttpConfigTest {

    @Test
    public void testDefaultConstructor() {
        HttpConfig httpConfig = new HttpConfig();
        assertNotNull(httpConfig);
        assertEquals(60000, httpConfig.getTimeout());
        assertEquals(60000, httpConfig.getTimeToLive());
        assertEquals(30000, httpConfig.getRequestTimeout());
        assertEquals(60000, httpConfig.getSocketTimeout());
    }

    @Test
    public void testGettersAndSetters() {
        HttpConfig httpConfig = new HttpConfig();
        httpConfig.setTimeout(120000);
        httpConfig.setTimeToLive(90000);
        httpConfig.setRequestTimeout(15000);
        httpConfig.setSocketTimeout(450000);
        assertEquals(120000, httpConfig.getTimeout());
        assertEquals(90000, httpConfig.getTimeToLive());
        assertEquals(15000, httpConfig.getRequestTimeout());
        assertEquals(450000, httpConfig.getSocketTimeout());
    }

}
