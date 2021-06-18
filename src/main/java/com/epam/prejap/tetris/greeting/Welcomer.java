package com.epam.prejap.tetris.greeting;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * This thread has one task to do which is welcoming the player.
 * @author Michał Cwynar
 */
class Welcomer extends JFrame implements Runnable, KeyListener {
    private final Greeting greeting;
    private final long millis;

    Welcomer(Greeting greeting, long millis) {
        this.greeting = greeting;
        this.millis = millis;
    }

    /**
     * The task of this thread is to create a welcome screen using Java Swing and display it for a given number of
     * milliseconds and after that deleting this screen.
     */
    @Override
    public void run() {
        createTheWelcomeWindow();
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ignore) {}
        dispose();
    }

    private void createTheWelcomeWindow() {
        setLocationRelativeTo(null);
        setTitle("Tetris");
        setSize(325, 150);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(true);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        createTheLabels();
    }

    private void createTheLabels() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Welcome " + System.getProperty("user.name") + "!"), BorderLayout.CENTER);
        panel.add(new JLabel("Good luck!"), BorderLayout.CENTER);
        add(panel);
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    /**
     * There is a possibility to manually close the screen pressing the Esc key.
     * @param e the pressed key
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            greeting.finishTheWelcoming();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}
