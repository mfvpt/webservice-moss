package pt.utl.codigospostais;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import pt.utl.config.bd.ConfigBD;

/**
 *
 * @author gcota
 *
 */
public class CodigoPostalDAO {

    public CodigoPostalDAO() {
    }

    public boolean validaCodigoPostal(String localidade, int CP4, int CP3) throws Exception {

        /* aceder 'a BD */
        Connection connection = null;
        try {
            /* create a connection to the database */
            connection = ConfigBD.obterLigacao();

            /* create query statement */
            Statement stmt = connection.createStatement();
            localidade = localidade.trim().replace("-", " ");
            String query = "SELECT Localidade, CP4, CP3, CPAlf FROM ISICodigosPostais.dbo.CodigosPostais WHERE RTRIM(LTRIM(UPPER(LOCALIDADE))) = '" + localidade.toUpperCase().trim() + "' AND CP4 = " + CP4 + " AND CP3 = " + CP3 + ";";
            System.out.println("SQL: " + query);

            /* execute query */
            ResultSet rs = stmt.executeQuery(query);

            /* process results */
            if (rs.next()) {
                /* foi encontrado o resultado */

                /* obter dados */
                Localidade local = new Localidade();
                local.setCodPostal(rs.getString("CP4"));
                local.setCodPostalAux(rs.getString("CP3"));
                local.setLocalidade(rs.getString("Localidade"));
                local.setCodPostalAlf(rs.getString("CPAlf"));
                return true;

            } else {
                /* nao foi encontrado o par localidade - codigo-postal */
                return false;
            }

        } catch (SQLException e) {
            System.err.println("SQL exception " + e);
            throw e;
        } finally {
            ConfigBD.fecharLigacao(connection);
        }

    }
}
