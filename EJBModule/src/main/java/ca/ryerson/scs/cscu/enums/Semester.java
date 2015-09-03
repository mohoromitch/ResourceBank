package ca.ryerson.scs.cscu.enums;

/**
 * Created by mitchellmohorovich on 15-08-21.
 * An enum that represents the semesters of the school.
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
