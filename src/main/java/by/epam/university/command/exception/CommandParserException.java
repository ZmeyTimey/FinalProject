package by.epam.university.command.exception;

    public class CommandParserException extends Exception {

        public CommandParserException() {
        }

        public CommandParserException(String message) {
            super(message);
        }

        public CommandParserException(String message, Throwable cause) {
            super(message, cause);
        }

        public CommandParserException(Throwable cause) {
            super(cause);
        }
    }
