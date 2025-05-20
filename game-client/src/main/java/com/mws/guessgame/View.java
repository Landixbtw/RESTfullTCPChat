package com.mws.guessgame;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 * modified by Markus Schäffter, 2024
 */
public class View extends VBox {

    private Controller controller;
    private String ref;

    public View(Controller controller) {
        this.controller = controller;

        int min = 1;
        int max = 100;

        var infoLabel = new Label();
        infoLabel.setFont(Font.font(14.0));
        infoLabel.setText("Bitte geben Sie eine Zahl zwischen "
                + String.valueOf(min) + " und "
                + String.valueOf(max) + " ein:");

        var fieldGuess = new TextField();
        fieldGuess.setFont(Font.font(20.0));

        var answer = new Text();
        answer.setFont(Font.font(20.0));

        var btnInit= new Button("Init");
        btnInit.setOnAction(e -> {
            CompletableFuture<String> cf = controller.onInit(min, max);
            try {
                  this.ref = (String) cf.get();
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            } catch (ExecutionException ex) {
                 throw new RuntimeException(ex);
            }

        });

        var btnGuess= new Button("Guess");
        btnGuess.setOnAction(e -> {
            int guess = 42;
            try{
                guess = Integer.parseInt(fieldGuess.getText());
            } catch (NumberFormatException ex){
                System.out.println("Invalid integer input");
            };

            CompletableFuture<String> cf = controller.onGuess(ref, guess);
            String result = null;

            try {
                result = cf.get();
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            } catch (ExecutionException ex) {
                throw new RuntimeException(ex);
            }
            answer.setText(result);

            if (result.contains("OK")){
            }
        });

        setPadding(new Insets(15));
        setSpacing(15);

        getChildren().addAll(infoLabel, fieldGuess, answer, btnInit, btnGuess);
    }
}
