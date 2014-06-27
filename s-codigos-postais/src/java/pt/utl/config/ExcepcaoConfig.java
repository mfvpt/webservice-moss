package pt.utl.config;

/**
 *
 * @author gcota
 *
 */
public class ExcepcaoConfig extends Exception {

	private static final long serialVersionUID = 1L;

	public ExcepcaoConfig() {
	}

	public ExcepcaoConfig(String message) {
		super(message);
	}

	public ExcepcaoConfig(Throwable cause) {
		super(cause);
	}

	public ExcepcaoConfig(String message, Throwable cause) {
		super(message, cause);
	}
    
}
