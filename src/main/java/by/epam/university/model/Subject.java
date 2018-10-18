package by.epam.university.model;

/**
 * Enum of subjects for exams.
 */
public enum Subject {
    /**
     * Physics.
     */
    PHYSICS {
//        @Override
//        public String toString() {
//            return "физика";
//        }
    },

    /**
     * Mathematics.
     */
    MATHEMATICS {
//        @Override
//        public String toString() {
//            return "математика";
//        }
    },

    /**
     * Russian language.
     */
    RUSSIAN {
//        @Override
//        public String toString() {
//            return "русский язык";
//        }
    },

    /**
     * Biology.
     */
    BIOLOGY {
        @Override
        public String toString() {
            return "биология";
        }
    },

    /**
     * English language.
     */
    ENGLISH {
        @Override
        public String toString() {
            return "английский язык";
        }
    },

    /**
     * Chemistry.
     */
    CHEMISTRY {
        @Override
        public String toString() {
            return "химия";
        }
    },

    /**
     * Social science.
     */
    SOCIAL_SCIENCE {
        @Override
        public String toString() {
            return "обществоведение";
        }
    },

    /**
     * History.
     */
    HISTORY {
        @Override
        public String toString() {
            return "история";
        }
    }
}
