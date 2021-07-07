package com.epam.prejap.tetris.block;

/**
 * Generates a "J" block.
 *
 * @author Michal Marek
 */
final class JBlock extends Block {

    private static final byte[][] IMAGE = {
            {0, 1},
            {0, 1},
            {1, 1},
    };

    public JBlock() {
        super(IMAGE);
    }
}
