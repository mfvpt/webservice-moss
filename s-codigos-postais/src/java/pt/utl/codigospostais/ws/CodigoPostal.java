package pt.utl.codigospostais.ws;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;

import pt.utl.codigospostais.CodigoPostalDAO;
import pt.utl.codigospostais.Localidade;

/**
 *
 * @author gcota
 *
 */
@WebService(serviceName = "CodigosPostais")
public class CodigoPostal {

    public CodigoPostal() {
    }

    @WebMethod(operationName = "validaLocalidade")
    public 
    @WebResult(name = "resultadoWS")
    Resultado validaLocalidade(@WebParam(name = "localidade") String localidade, @WebParam(name = "CP4") String CP4, @WebParam(name = "CP3") String CP3)
            throws ErroInterno {

        /* criar e preencher resultado */
        Resultado resultado = new Resultado();

        try {
            /* pesquisar par localidade/CP4-CP3 do cartao */
            CodigoPostalDAO cpDAO = new CodigoPostalDAO();
            boolean resultadoDAO = cpDAO.validaCodigoPostal(localidade, new Integer(CP4).intValue(), new Integer(CP3).intValue());
            if (!resultadoDAO) {
                /* o par localidade/CP4-CP3 do cartao não foi encontrado */
                resultado.setValor(resultadoDAO);
                resultado.setDescricao("Nao foi encontrado o codigo postal: '" + CP4 + "-" + CP3 + "' na localidade '" + localidade.trim().toUpperCase() + "'");
            } else {
                /* o par localidade/CP4-CP3 do cartao foi encontrado, devolver informacao */
                resultado.setValor(resultadoDAO);
                resultado.setDescricao("Codigo Postal valido.");
            }
            return resultado;

        } catch (Exception e) {
            System.err.println("Ocorreu um erro inesperado na aplicacao de codigos-postais: " + e);
            throw new ErroInterno("O servico encontra-se temporariamente indisponivel. Por favor tente mais tarde: " + e.getMessage());
        }

    }
}
