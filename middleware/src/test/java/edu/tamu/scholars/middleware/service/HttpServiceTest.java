package edu.tamu.scholars.middleware.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.test.util.ReflectionTestUtils;

import edu.tamu.scholars.middleware.service.request.HttpRequest;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public final class HttpServiceTest {
    private final static String MOCK_URL_VALID = "http://localhost:9100/mock";
    private final static String MOCK_URL_INVALID = "\\/\\/localhost@9100 /mock";
    private final static String MOCK_RESPONSE_CONTENT = "mock content";
    private final static String MOCK_RESPONSE_CONTENT_NORMALIZATION = "\u2013mock norm\u0308\u0041\u0301lization\u2014";

    private final static List<NameValuePair> mockParameters = new ArrayList<NameValuePair>();
    private final static List<Header> mockHeaders = new ArrayList<Header>();

    private HttpService service;

    private HttpRequest mockRequest;

    @Mock
    private CloseableHttpResponse mockResponse;

    @Mock
    private CloseableHttpClient mockHttpClient;

    @BeforeEach
    public void beforeEach() throws ClientProtocolException, IOException {
        service = new HttpService();

        mockRequest = new HttpRequest();
        mockRequest.setHeaders(mockHeaders);
        mockRequest.setParameters(mockParameters);
        mockRequest.setUrl(MOCK_URL_VALID);

        ReflectionTestUtils.setField(HttpService.class, "httpClient", mockHttpClient);

        when(mockHttpClient.execute(any(HttpRequestBase.class))).thenReturn(mockResponse);
    }

    @Test
    public void testGetSuccess() throws UnsupportedOperationException, IOException {
        mockHttpResponse(200, MOCK_RESPONSE_CONTENT);
        assertEquals(MOCK_RESPONSE_CONTENT, service.get(mockRequest));
    }

    @Test
    public void testGetWithNormalization() throws UnsupportedOperationException, IOException {
        mockHttpResponse(200, MOCK_RESPONSE_CONTENT_NORMALIZATION);
        String response = service.get(mockRequest);
        assertEquals(Normalizer.normalize(MOCK_RESPONSE_CONTENT_NORMALIZATION, Normalizer.Form.NFC), response);
        assertNotEquals(MOCK_RESPONSE_CONTENT_NORMALIZATION, response);
    }

    @Test
    public void testGetFailure() throws UnsupportedOperationException, IOException {
        mockHttpResponse(500, MOCK_RESPONSE_CONTENT);
        assertNull(service.get(mockRequest));
    }

    @Test
    public void testUriSyntaxException() throws UnsupportedOperationException, IOException {
        mockRequest.setUrl(MOCK_URL_INVALID);
        mockHttpResponse(200, MOCK_RESPONSE_CONTENT);
        assertNull(service.get(mockRequest));
    }

    @Test
    public void testIllegalStateException() throws UnsupportedOperationException, IOException, IllegalStateException {
        when(mockHttpClient.execute(any(HttpRequestBase.class))).thenThrow(new IllegalStateException());
        mockHttpResponse(200, MOCK_RESPONSE_CONTENT);
        assertNull(service.get(mockRequest));
    }

    private void mockHttpResponse(int code, String content) throws UnsupportedOperationException, IOException {
        StatusLine statusLine = mock(StatusLine.class);
        HttpEntity mockHttpEntity = mock(HttpEntity.class);
        InputStream inputStream = new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8));

        when(mockResponse.getStatusLine()).thenReturn(statusLine);
        when(mockResponse.getEntity()).thenReturn(mockHttpEntity);
        when(statusLine.getStatusCode()).thenReturn(code);
        when(mockHttpEntity.getContent()).thenReturn(inputStream);
    }
}
