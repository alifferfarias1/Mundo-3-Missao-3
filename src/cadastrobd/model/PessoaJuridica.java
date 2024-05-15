package cadastrobd.model;

/**
 *
 * Author: Aliffer
 */
public class Empresa extends Pessoa {
    
    private String cnpj;

    public Empresa() {
        
    }

    public Empresa(Integer id, String nome, String endereco, String cidade,
        String estado, String telefone, String email, String cnpj) {
        super(id, nome, endereco, cidade, estado, telefone, email);
        this.cnpj = cnpj;
    }

    @Override
    public void exibir() {
        System.out.println(this);
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    
    @Override
    public String toString() {
        String output = super.toString();
        output = output.concat("\nCNPJ: ".concat(cnpj));
        return output;
    }
    
}