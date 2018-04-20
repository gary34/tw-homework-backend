package io.gary;


import com.fasterxml.jackson.annotation.JsonView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

/**
 * Created by pugang on 2018/4/20.
 */
@RestController
@EnableAutoConfiguration
public class Hello {

    private GeoInfo getGeoInfo(String ip) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet("http://ip-api.com/json/" + ip);

        ResponseHandler<GeoInfo> rh = new ResponseHandler<GeoInfo>() {
            @Override
            public GeoInfo handleResponse(
                    final HttpResponse response) throws IOException {
                StatusLine statusLine = response.getStatusLine();
                HttpEntity entity = response.getEntity();
                if (statusLine.getStatusCode() >= 300) {
                    throw new HttpResponseException(
                            statusLine.getStatusCode(),
                            statusLine.getReasonPhrase());
                }
                if (entity == null) {
                    throw new ClientProtocolException("Response contains no content");
                }
                Gson gson = new GsonBuilder().create();
                ContentType contentType = ContentType.getOrDefault(entity);
                Charset charset = contentType.getCharset();
                Reader reader = new InputStreamReader(entity.getContent(), charset);
                return gson.fromJson(reader, GeoInfo.class);
            }
        };
        try {
            GeoInfo info = httpclient.execute(httpget, rh);
            info.setQuery(ip);
            return info;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new GeoInfo();
    }

    @GetMapping("/geo")
    @JsonView(GeoInfo.class)
    private GeoInfo geo(HttpServletRequest request) {
        String remoteIP = request.getRemoteAddr();
        GeoInfo info = getGeoInfo(remoteIP);
        return info;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Hello.class, args);
    }
}
