package ed.mse.commons;

public class Logger {
	
	public final static int DEBUG = 0;
	public final static int PRODUCTION = 1;
	
	private static Logger instance;
	
	private int mode;
	
	private Logger(int state) {
		this.mode = state;
	}
	
	public static void instantiate(int mode) {
		instance = new Logger(mode);
	}
	
	public static Logger getLogger() {
		return instance;
	}
	
	public void log(String logmessage) {
		if(mode == DEBUG) {
			System.out.println(logmessage);
		}
	}
	
}
