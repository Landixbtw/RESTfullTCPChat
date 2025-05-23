package com.mws.guessgame;

import java.util.concurrent.CompletableFuture;

/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 * modified by Markus Schäffter, 2024
 */
public class LocalController implements Controller {
    @Override
    public CompletableFuture<String> onInit(int min, int max) {
        var future = new CompletableFuture<String>();
        future.complete("42");
        return future;
    }

    @Override
    public CompletableFuture<String> onGuess(String ref, int guess) {
        String answer = "";
        int refint = Integer.parseInt(ref);
        if (refint == guess) {answer = "OK";}
        if (refint > guess) {answer = "Guess larger";}
        if (refint < guess) {answer = "Guess smaller";}
        var future = new CompletableFuture<String>();

        future.complete(answer);
        return future;
    }
}
