package pt.utl.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Classe de configuração que simplifica o acesso ao ficheiro de propriedades.
 * As aplicações têm que fazer load para carregar os dados antes da primeira utilização
 *
 * @author gcota
 *
 */
public class Config {

    /**
     * Single instance created upon class loading.
     */
    private static final Config fINSTANCE = new Config();

    /**
     * Return singleton instance of config.
     * @return
     */
    public static synchronized Config getInstance() {
        return fINSTANCE;
    }

    
    /**
     * Properties object 
     */
    private Properties _props;
    
    /**
     * Private constructor prevents construction outside this class.
     */
    private Config() {
        _props = null;
    }

    /**
     * Load properties from resource stream.
     * @param propertyFileResourcePath
     * @throws ExcepcaoConfig
     */
    public void load(String propertyFileResourcePath) throws ExcepcaoConfig {
        InputStream is = null;
        try {
            is = Config.class.getResourceAsStream(propertyFileResourcePath);
            _props = new Properties();
            _props.load(is);
        } catch (IOException e) {
            System.err.println("caught exception in Config.load; wrapping and rethrowing");
            System.err.println(e.getClass().toString());
            System.err.println(e.getMessage());
            throw new ExcepcaoConfig("Erro no acesso ao ficheiro de configuracao", e);
        } finally {
            try {
                if(is != null) {
                    is.close();
                }
            } catch (IOException e) {
                System.err.println("caught exception in Config.load while closing InputStream; ignoring");
                System.err.println(e.getClass().toString());
                System.err.println(e.getMessage());
            }
        }
    }

    /**
     * Reads a configuration parameter.
     * @param key string that identifies the parameter
     * @return parameter value or null if parameter not found
     */
    public String getInitParameter(String key) {
        if(_props == null) {
            throw new IllegalStateException("Must invoke load() before getInitParameter()");
        }
        return _props.getProperty(key);
    }

}
