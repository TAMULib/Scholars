package edu.tamu.scholars.middleware.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import edu.tamu.scholars.middleware.service.request.HttpRequest;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public final class HttpServiceTest {
    private final static String MOCK_URL_VALID = "http://localhost:9100/mock";
    private final static String MOCK_URL_INVALID = "\\/\\/localhost@9100 /mock";
    private final static String MOCK_RESPONSE_CONTENT = "mock content";
    private final static String MOCK_RESPONSE_CONTENT_NORMALIZATION = " \u2013 mock norm\u0308\u0041\u0301lization \u2014 ";

    private final static List<NameValuePair> mockParameters = new ArrayList<NameValuePair>();
    private final static List<Header> mockHeaders = new ArrayList<Header>();

    private HttpService service;

    private HttpRequest mockRequest;

    @Mock
    private CloseableHttpResponse mockResponse;

    @Mock
    private CloseableHttpClient mockHttpClient;

    @BeforeEach
    public void beforeEach() throws Throwable {
        MockitoAnnotations.initMocks(this);

        service = new HttpService();

        mockRequest = new HttpRequest();
        mockRequest.setHeaders(mockHeaders);
        mockRequest.setParameters(mockParameters);
        mockRequest.setUrl(MOCK_URL_VALID);

        Field field = service.getClass().getDeclaredField("httpClient");
        field.setAccessible(true);
        field.set(service, mockHttpClient);

        when(mockHttpClient.execute(any(HttpRequestBase.class))).thenReturn(mockResponse);
    }

    @Test
    public void testGetSuccess() throws Throwable {
        mockHttpResponse(200, MOCK_RESPONSE_CONTENT);
        assertEquals(MOCK_RESPONSE_CONTENT, service.get(mockRequest));
    }

    @Test
    public void testGetWithNormalization() throws Throwable {
        mockHttpResponse(200, MOCK_RESPONSE_CONTENT_NORMALIZATION);
        String response = service.get(mockRequest);
        assertEquals(Normalizer.normalize(MOCK_RESPONSE_CONTENT_NORMALIZATION, Normalizer.Form.NFC), response);
        assertNotEquals(MOCK_RESPONSE_CONTENT_NORMALIZATION, response);
    }

    @Test
    public void testGetFailure() throws Throwable {
        mockHttpResponse(500, MOCK_RESPONSE_CONTENT);
        assertEquals(null, service.get(mockRequest));
    }

    @Test
    public void testUriSyntaxException() throws Throwable {
        mockRequest.setUrl(MOCK_URL_INVALID);
        mockHttpResponse(200, MOCK_RESPONSE_CONTENT);
        assertEquals(null, service.get(mockRequest));
    }

    @Test
    public void testIllegalStateException() throws Throwable {
        when(mockHttpClient.execute(any(HttpRequestBase.class))).thenThrow(new IllegalStateException());
        mockHttpResponse(200, MOCK_RESPONSE_CONTENT);
        assertEquals(null, service.get(mockRequest));
    }

    private void mockHttpResponse(int code, String content) throws Throwable {
        StatusLine statusLine = mock(StatusLine.class);
        HttpEntity mockHttpEntity = mock(HttpEntity.class);
        InputStream inputStream = new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8));

        when(mockResponse.getStatusLine()).thenReturn(statusLine);
        when(mockResponse.getEntity()).thenReturn(mockHttpEntity);
        when(statusLine.getStatusCode()).thenReturn(code);
        when(mockHttpEntity.getContent()).thenReturn(inputStream);
    }
}
