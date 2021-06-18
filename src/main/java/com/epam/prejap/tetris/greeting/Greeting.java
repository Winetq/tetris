package com.epam.prejap.tetris.greeting;

/**
 * Manages the Welcomer thread.
 * @author Michał Cwynar
 * @see Welcomer
 */
public class Greeting {
    private final Thread welcomer;

    /**
     * Creates the thread.
     */
    public Greeting(long millis) {
        this.welcomer = new Thread(new Welcomer(this, millis));
    }

    /**
     * Runs the thread (start()) and waits for him (join()).
     */
    public void welcomeThePlayer() {
        welcomer.start();
        try {
            welcomer.join();
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Wakes the thread.
     */
    void finishTheWelcoming() {
        welcomer.interrupt();
    }
}
