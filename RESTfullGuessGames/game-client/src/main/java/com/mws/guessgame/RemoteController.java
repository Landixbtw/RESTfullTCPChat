package com.mws.guessgame;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 * modified by Markus Schäffter, 2024
 */
public class RemoteController implements Controller {

    private HttpClient client = HttpClient.newHttpClient();

    @Override
    public CompletableFuture<String> onInit(int min, int max) {
        var request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:55555/gng/init?min=" + min + "&max=" + max))
                .GET()
                .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(res -> {
                    return res;
                })
                .thenApply(HttpResponse::body)
                .thenApply(body -> {
                    return body;
                });
    }

    @Override
    public CompletableFuture<String> onGuess(String ref, int guess) {
        System.out.println("...RemoteController.onGuess: ref =" + ref);
        var request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:55555/gng/guess?ref=" + ref + "&guess=" + guess))
                .GET()
                .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(body -> {
                   return body;
                 });

    }
}
