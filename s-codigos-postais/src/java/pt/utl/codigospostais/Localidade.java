package pt.utl.codigospostais;

/**
 *
 * @author gcota
 *
 */
public class Localidade {

    private String localidade;
    private String codPostal;
    private String codPostalAux;
    private String codPostalAlf;

    public String getCodPostalAlf() {
        return codPostalAlf;
    }

    public void setCodPostalAlf(String codPostalAlf) {
        this.codPostalAlf = codPostalAlf;
    }

    public Localidade() {
    }

    public String getCodPostal() {
        return codPostal;
    }

    public void setCodPostal(String codPostal) {
        this.codPostal = codPostal;
    }

    public String getCodPostalAux() {
        return codPostalAux;
    }

    public void setCodPostalAux(String codPostalAux) {
        this.codPostalAux = codPostalAux;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }
}
