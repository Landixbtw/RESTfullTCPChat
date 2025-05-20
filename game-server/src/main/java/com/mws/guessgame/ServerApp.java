package com.mws.guessgame;

import io.javalin.Javalin;

import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;

public class ServerApp {

    /**
     * @author Markus Schäffter, 2020
     * author of the underlying code Almas Baimagambetov (almaslvl@gmail.com)
     */



    public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException {
        Model model = new Model();

        var app = Javalin.create().start(55555);

        app.get("/gng/init", ctx -> {
            var min = ctx.req.getParameter("min");
            var max = ctx.req.getParameter("max");
            var result = model.init(Integer.parseInt(min), Integer.parseInt(max));
            ctx.result(result);
        });

        app.get("/gng/guess", ctx -> {
            var ref = ctx.req.getParameter("ref");
            var guess = ctx.req.getParameter("guess");

            var result = model.guess(ref, Integer.parseInt(guess));
            ctx.result(result);
        });

    }
}
