package com.epam.prejap.tetris.game;

public class Welcomer implements Runnable{
    private final Waiter waiter;

    public Welcomer(Waiter waiter) {
        this.waiter = waiter;
    }

    @Override
    public void run() {
        System.out.println("Welcome " + System.getProperty("user.name") + "!");
        waiter.waitForIt();
    }
}
