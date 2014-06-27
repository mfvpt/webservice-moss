package pt.utl.config.web;

import javax.servlet.ServletContextEvent;
import pt.utl.config.Config;
import pt.utl.config.ExcepcaoConfig;

/**
 * Para que este objecto seja executado quando a aplicação Web é instalada
 * é necessário configurá-lo como Context Listener no ficheiro web.xml
 *
 * @author gcota
 *
 */
public class ConfigContextListener implements javax.servlet.ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {
        System.out.print("A iniciar a configuração de aplicação Web... ");
        String configPath = "/config.properties";
        try {
            Config.getInstance().load(configPath);
        } catch(ExcepcaoConfig e) {
            System.out.println("erro!");
            throw new RuntimeException("Erro ao tentar iniciar a configuração lendo o ficheiro de recursos em " + configPath, e);
        }
        System.out.println("ok!");
    }

    public void contextDestroyed(ServletContextEvent sce) {
    }

}
