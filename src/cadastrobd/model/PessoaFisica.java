package cadastrobd.model;

/**
 *
 * @author Aliffer
 */
public class PessoaFisica extends Pessoa {
    
    private String cpf;

    public PessoaFisica() {
        
    }

    public PessoaFisica(Integer pessoaId, String nomeCompleto, String enderecoCompleto, String cidade,
        String estado, String telefoneContato, String emailContato, String cpf) {
        super(pessoaId, nomeCompleto, enderecoCompleto, cidade, estado, telefoneContato, emailContato);
        this.cpf = cpf;
    }
    
    @Override
    public void displayInfo() {
        System.out.println(this);
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    @Override
    public String toString() {
        String output = super.toString();
        output = output.concat("\nCPF: ".concat(cpf));
        return output;
    }
    
}