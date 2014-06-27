package pt.utl.codigospostais.ws;

/**
 * Resultado da execucao do servico
 *
 * @author gcota
 *
 */
public class Resultado {

    private boolean valor;
    private String descricao;

    public Resultado() {
        valor = false;
        descricao = "";
    }

    public boolean getValor() {
        return valor;
    }

    public void setValor(boolean valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        if (descricao == null) {
            this.descricao = "";
        } else {
            this.descricao = descricao;
        }
    }
}
