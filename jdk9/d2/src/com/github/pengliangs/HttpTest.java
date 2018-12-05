package com.github.pengliangs;

import jdk.incubator.http.HttpClient;
import jdk.incubator.http.HttpRequest;
import jdk.incubator.http.HttpResponse;

import java.io.IOException;
import java.net.URI;

/**
 * @author pengliang on 2018-12-05 14:25
 */
public class HttpTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create("http://www.baidu.com/")).GET().build();
        HttpResponse<String> response = null;
        response = httpClient.send(request, HttpResponse.BodyHandler.asString());
        System.out.println(response.statusCode());
        System.out.println(response.version().name());
        System.out.println(response.body());
    }
}
