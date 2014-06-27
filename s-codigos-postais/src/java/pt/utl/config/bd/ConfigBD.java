package pt.utl.config.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import pt.utl.config.Config;

/**
 * Classe que permite a obtenção e fecho de ligação à base de dados,
 * usando os dados de configuração.
 *
 * @author gcota
 *
 */
public class ConfigBD {

    /* Não é possível criar instâncias desta classe */
    private ConfigBD() {
    }

    /* Estabelece uma ligação à base de dados */
    public static Connection obterLigacao() throws ExcepcaoConfigBD, SQLException {

        /* ler a configuração */
        Config cfg = Config.getInstance();

        /* verificar opção de escrita de mensagens de ajuda */
        String dbDebugString = cfg.getInitParameter("db.debug");
        boolean dbDebug = false;
        if(dbDebugString != null && dbDebugString.equalsIgnoreCase("true")) {
            dbDebug = true;
        }
        
        /* opções principais - driver e endereço */
        String dbDriverName = cfg.getInitParameter("db.driver");
        String dbUrl = cfg.getInitParameter("db.url");

        if(dbUrl == null) {
            /* Ler propriedades individuais para construir endereço da BD */
            String dbServer = cfg.getInitParameter("db.server");
            String dbPort = cfg.getInitParameter("db.port");
            String dbName = cfg.getInitParameter("db.name");
            String dbIntegratedSecurity = cfg.getInitParameter("db.integrated-security");
            dbUrl = "jdbc:sqlserver://" +
                    dbServer + ":" + dbPort + ";" +
                    "databasename=" + dbName + ";" +
                    "SelectMethod=cursor;" +
                    "IntegratedSecurity=" + dbIntegratedSecurity.toLowerCase() + ";";
            if(dbIntegratedSecurity.equalsIgnoreCase("false")) {
                String dbUsername = cfg.getInitParameter("db.username");
                String dbPassword = cfg.getInitParameter("db.password");
                dbUrl += "user=" + dbUsername + ";" +
                         "password=" + dbPassword + ";";
            }
        }
        /* imprimir opções principais */
        if(dbDebug) {
            System.out.println("db.driver=" + dbDriverName);
        }
        if(dbDebug) {
            System.out.println("db.url=" + dbUrl);
        }
        
        /* aceder à BD */
        Connection connection = null;
        try {
            /* Iniciação JDBC */
            Class.forName(dbDriverName);

            /* Criar ligação */
            connection = DriverManager.getConnection(dbUrl);
            return connection;
            
        } catch (ClassNotFoundException e) {
            System.err.println("Could not find the database driver " + e);
            fecharLigacao(connection);
            throw new ExcepcaoConfigBD("Erro na iniciacao JDBC", e);
        } catch (SQLException e) {
            System.err.println("Could not create a database connection " + e);
            fecharLigacao(connection);
            throw new ExcepcaoConfigBD("Erro na criacao de ligacao 'a BD", e);
        }

    }

    /* Fecha a ligação à base de dados, ignorando algum problema que possa acontecer */
    public static void fecharLigacao(Connection connection) {
        if(connection != null) {
            try {
                connection.close();
            } catch(SQLException ignore) {
                System.err.println("Ignoring SQLException on connection close: " + ignore);
            }
        }
    }

}
