package melencio.clark.john.pos;

public class Colors {
	private static final String DIM = "\033[2m"; 
	private static final String RESET = "\033[39m";

	public static String red(String text) {
		return DIM + "\033[31m" + text + RESET;
	} 
	
	public static String green(String text) {
		return DIM + "\033[32m" + text + RESET;
	} 

	public static String yellow(String text) {
		return DIM + "\033[33m" + text + RESET;
	}
	
	public static String blue(String text) {
		return "\033[34m" + text + RESET;
	} 
	
	public static String purple(String text) {
		return "\033[35m" + text + RESET;
	} 
	
	public static String cyan(String text) {
		return DIM + "\033[36m" + text + RESET;
	} 
}
