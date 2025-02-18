package com.epam.prejap.tetris;

import com.epam.prejap.tetris.logger.Logger;

/**
 * Takes CLI parameters and transforms them into expected values
 *
 * @author Nikita Pochapynskyi
 */
public class Parameters {
    private static final Logger LOGGER = Logger.getLogger(Parameters.class);

    /**
     * Enum of input parameters that are used in program
     */
    private enum Args {
        ROWS(20, 10, 100),
        COLS(30, 10, 100),
        DELAY(500, 200, 2000);

        private static final Logger LOGGER_ARGS = Logger.getLogger(Args.class);

        private final int defaultValue;
        private final int minValue;
        private final int maxValue;
        private int actualValue = -1;

        Args(int defValue, int minValue, int maxValue) {
            this.defaultValue = defValue;
            this.minValue = minValue;
            this.maxValue = maxValue;
        }

        /**
         * If parameter was not set - return default value
         *
         * @return value of parameter
         */
        int value() {
            return actualValue == -1 ? defaultValue : actualValue;
        }

        /**
         * Sets actual value of parameter.
         * Corrects bad values if detects such
         *
         * @param cliValue CLI parameter's value
         */
        void setArg(String cliValue) {

            try {
                var value = Integer.parseInt(cliValue.trim());
                value = Math.max(value, minValue);
                value = Math.min(value, maxValue);
                actualValue = value;
            } catch (NumberFormatException ignored) {
                LOGGER_ARGS.warn("The provided argument {} cannot be parsed and will be replaced by default value {}",
                        cliValue, defaultValue);
                actualValue = defaultValue;
            }
        }
    }

    /**
     * Takes CLI parameter and corrects them if needed.
     * If there is not enough parameters default will be applied
     * It there is mote parameters - extra will be ignored
     *
     * @param args CLI arguments
     */
    public Parameters(String[] args) {
        LOGGER.info("Starting to parse arguments");
        setArgs(args);
        informUser();
        LOGGER.trace("New {} object is created", getClass().getSimpleName());
    }

    /**
     * Inform user which parameters game will start with
     */
    private void informUser() {
        var pattern = "The game will start with %d rows and %d columns and a delay of %d milliseconds" + System.lineSeparator();
        System.out.format(pattern, Args.ROWS.value(), Args.COLS.value(), Args.DELAY.value());
        LOGGER.info("The user was informed about starting parameters");
    }

    /**
     * Sets provided arguments
     *
     * @param args array of CLI arguments
     */
    private void setArgs(String[] args) {
        for (Args arg : Args.values()) {
            if ((arg.ordinal() + 1) <= args.length) {
                arg.setArg(args[arg.ordinal()]);
                LOGGER.debug("Setting the argument {}", arg.name());
            }
        }
        LOGGER.info("Finished parsing arguments");
    }

    /**
     * Returns value of <b>ROWS</b> parameter.
     * It might be corrected if CLI value was bad
     *
     * @return quantity of rows
     */
    public int getRows() {
        return Args.ROWS.value();
    }

    /**
     * Returns value of <b>COLUMNS</b> parameter.
     * It might be corrected if CLI value was bad
     *
     * @return quantity of columns
     */
    public int getCols() {
        return Args.COLS.value();
    }

    /**
     * Returns value of <b>DELAY</b> parameter.
     * It might be corrected if CLI value was bad
     *
     * @return delay in ms
     */
    public int getDelay() {
        return Args.DELAY.value();
    }
}
