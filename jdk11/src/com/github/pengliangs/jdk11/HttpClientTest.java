package com.github.pengliangs.jdk11;

import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.ExecutionException;

/**
 * @author pengliang
 * @since 2020/10/5 21:48
 */
public class HttpClientTest {

    @Test
    public void httpClient_test() throws IOException, InterruptedException {
        var response = HttpClient.newHttpClient().send(
                HttpRequest.newBuilder(URI.create("https://www.baidu.com")).build()
                , HttpResponse.BodyHandlers.ofString()
        );
        System.out.println(response.body());
    }

    @Test
    public void httpClient_test2() throws InterruptedException, ExecutionException {
        var response = HttpClient.newHttpClient().sendAsync(
                HttpRequest.newBuilder(URI.create("https://www.baidu.com")).build()
                , HttpResponse.BodyHandlers.ofString()
        );
        System.out.println(response.get().body());
    }
}
