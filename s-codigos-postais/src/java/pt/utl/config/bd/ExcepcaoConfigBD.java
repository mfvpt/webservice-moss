package pt.utl.config.bd;

/**
 *
 * @author gcota
 *
 */
public class ExcepcaoConfigBD extends Exception {

	private static final long serialVersionUID = 1L;

	public ExcepcaoConfigBD() {
	}

	public ExcepcaoConfigBD(String message) {
		super(message);
	}

	public ExcepcaoConfigBD(Throwable cause) {
		super(cause);
	}

	public ExcepcaoConfigBD(String message, Throwable cause) {
		super(message, cause);
	}
    
}
