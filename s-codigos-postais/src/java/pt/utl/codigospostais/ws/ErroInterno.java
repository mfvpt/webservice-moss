package pt.utl.codigospostais.ws;

/*
 * @author gcota
 *
 */
public class ErroInterno extends java.lang.Exception {

    private static final long serialVersionUID = 1L;
    
    /**
     * Creates a new instance of <code>ErroValidarFormatoCartao</code> without detail message.
     */
    public ErroInterno() {
    }
    
    
    /**
     * Constructs an instance of <code>ErroValidarFormatoCartao</code> with the specified detail message.
     * @param msg the detail message.
     */
    public ErroInterno(String msg) {
        super(msg);
    }
}
