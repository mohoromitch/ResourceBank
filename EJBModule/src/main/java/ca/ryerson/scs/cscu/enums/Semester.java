package ca.ryerson.scs.cscu.enums;

/**
 * Created by mitchellmohorovich on 15-08-21.
 */
public enum Semester {
        fall("Fall"),
        winter("Winter"),
        spring("Spring"),
        summer("Summer"),;

        private final String label;

        Semester(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
}
