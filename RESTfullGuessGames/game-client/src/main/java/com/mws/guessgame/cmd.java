package com.mws.guessgame;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * @author Markus Schäffter
 */

public class cmd extends Application {

    public static void main(String[] args) {
        // Controller remcont = new LocalController();
        // String ref="42";

        Controller remcont = new RemoteController();
        String ref = "CaGvt+/jbnqg/vpwJDXPG6f2Xc1x3KKapAUkCDOZ0/E=";
        int min =1;
        int max = 100;
        int guess=0;

        CompletableFuture<String> cf = null;

        try {
            cf = remcont.onInit(min, max);
            ref = (String) cf.get();


            System.out.print("Enter a guess:\n");
            guess = Integer.parseInt(System.console().readLine());
            cf = remcont.onGuess(String.valueOf(ref), guess);
            String answer = (String) cf.get();
            System.out.println("Result: " +answer);

        } catch (NumberFormatException e) {
            System.out.println("Invalid integer input");
        }
          catch (Exception e) {
            e.printStackTrace();
        }

   }

}

