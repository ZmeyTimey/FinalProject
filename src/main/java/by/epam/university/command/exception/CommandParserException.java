package by.epam.university.command.exception;

    public class CommandParserException
            extends Exception {

        public CommandParserException() {
        }

        public CommandParserException(final String message) {
            super(message);
        }

        public CommandParserException(final String message,
                                      final Throwable cause) {
            super(message, cause);
        }

        public CommandParserException(final Throwable cause) {
            super(cause);
        }
    }
