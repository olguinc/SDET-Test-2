package tools;

public final class ANSIDef {
	public static final String ANSI_GREEN = "\033[32m";
	public static final String ANSI_BLUE = "\033[34m";
	public static final String ANSI_YELLOW = "\033[33m";
	public static final String ANSI_ORANGE = "\033[38;2;255;165;0m";
	public static final String ANSI_GREEN_BOLD = "\033[32;1m";

	public static final String ANSI_CLOSE = "\033[0m";

	private ANSIDef() {
		throw new AssertionError();
	}
}
