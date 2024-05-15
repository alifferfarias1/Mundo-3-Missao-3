package cadastrobd.model;

/**
 *
 * @author Aliffer
 */
public class Pessoa {
    
    private Integer pessoaId;
    private String nomeCompleto;
    private String enderecoCompleto;
    private String cidade;
    private String estado;
    private String telefoneContato;
    private String emailContato;

    public Pessoa() {
        
    }

    public Pessoa(Integer pessoaId, String nomeCompleto, String enderecoCompleto, String cidade,
        String estado, String telefoneContato, String emailContato) {
        this.pessoaId = pessoaId;
        this.nomeCompleto = nomeCompleto;
        this.enderecoCompleto = enderecoCompleto;
        this.cidade = cidade;
        this.estado = estado;
        this.telefoneContato = telefoneContato;
        this.emailContato = emailContato;
    }

    public void displayInfo() {
        System.out.println(this);
    }

    public Integer getPessoaId() {
        return pessoaId;
    }

    public void setPessoaId(Integer pessoaId) {
        this.pessoaId = pessoaId;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getEnderecoCompleto() {
        return enderecoCompleto;
    }

    public void setEnderecoCompleto(String enderecoCompleto) {
        this.enderecoCompleto = enderecoCompleto;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTelefoneContato() {
        return telefoneContato;
    }

    public void setTelefoneContato(String telefoneContato) {
        this.telefoneContato = telefoneContato;
    }

    public String getEmailContato() {
        return emailContato;
    }

    public void setEmailContato(String emailContato) {
        this.emailContato = emailContato;
    }
    
    @Override
    public String toString() {
        String output = "ID: ".concat(pessoaId.toString());
        output = output.concat("\nNome Completo: ".concat(nomeCompleto));
        output = output.concat("\nEndereço Completo: ".concat(enderecoCompleto));
        output = output.concat("\nCidade: ".concat(cidade));
        output = output.concat("\nEstado: ".concat(estado));
        output = output.concat("\nTelefone de Contato: ".concat(telefoneContato));
        output = output.concat("\nEmail de Contato: ".concat(emailContato));
        return output;
    }
}