package com.mws.guessgame;

import java.util.concurrent.CompletableFuture;

/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 * modified by Markus Schäffter, 2024
 */
public interface Controller {

    CompletableFuture<String> onInit(int min, int max);

    CompletableFuture<String> onGuess(String ref, int guess);
}
