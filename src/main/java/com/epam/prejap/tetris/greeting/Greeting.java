package com.epam.prejap.tetris.greeting;

/**
 * The Greeting class manage the Welcomer thread.
 * @author Michał Cwynar
 */
public class Greeting {
    private final Thread welcomer;

    /**
     * The constructor creates the thread.
     */
    public Greeting(long milis) {
        this.welcomer = new Thread(new Welcomer(this, milis));
    }

    /**
     * This method runs the thread (start()) and waits for him (join()).
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
     * This method wakes the thread.
     */
    void finishTheWelcoming() {
        welcomer.interrupt();
    }
}
