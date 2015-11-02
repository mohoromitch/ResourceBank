package ca.ryerson.scs.cscu.enums;

/**
 * Created by mitchellmohorovich on 2015-11-01.
 */
public enum Title {
	mr("Mr."),
	mrs("Mrs."),
	ms("Ms."),
	dr("Dr."),
	other("");

	private String title;

	Title(String s) {
		this.title = s;
	}

	public String toString() {
		return title;
	}
}
