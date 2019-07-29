package tools;

public final class ANSIDef {
	public static final String ANSI_GREEN = "\033[33m";
	public static final String ANSI_BLUE = "\033[34m";
	public static final String ANSI_YELLOW = "\033[36m";
	public static final String ANSI_ORANGE = "\033[48:2:255:165"; 
	public static final String ANSI_GREEN_BOLD = "\033[32;1;2m";

	public static final String ANSI_CLOSE = "\033[0m";

	/**
	 * The caller references the constants using <tt>Consts.EMPTY_STRING</tt>, and
	 * so on. Thus, the caller should be prevented from constructing objects of this
	 * class, by declaring this private constructor.
	 */
	private ANSIDef() {
		// this prevents even the native class from
		// calling this ctor as well :
		throw new AssertionError();
	}

}
