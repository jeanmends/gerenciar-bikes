package bikes.bike_manager.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_endereco")
    private Long idEndereco;
    @Column(length = 8)
    private String cep;
    private String logradouro;
    private String complimento;
    private String bairro;
    private String cidade;
    @Column(length = 2)
    private String UF;
    @OneToOne(mappedBy = "endereco")
    private Cond conds;
    public Endereco(){}
    public Endereco(Long idEndereco, String cep, String logradouro, String complimento,
                    String bairro, String cidade, String UF /*, Cond conds*/) {
        this.idEndereco = idEndereco;
        this.cep = cep;
        this.logradouro = logradouro;
        this.complimento = complimento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.UF = UF;
       // this.conds = conds;
    }

    public Endereco(DadosEndereco dadosEnderecos) {
        this.idEndereco = getIdEndereco();
        this.bairro = dadosEnderecos.bairro();
        this.cep = dadosEnderecos.cep();
        this.logradouro= dadosEnderecos.logradouro();
        this.complimento = dadosEnderecos.complimento();
        this.UF = dadosEnderecos.UF();
        this.cidade = dadosEnderecos.cidade();
    }

    public Long getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Long idEndereco) {
        this.idEndereco = idEndereco;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getComplimento() {
        return complimento;
    }

    public void setComplimento(String complimento) {
        this.complimento = complimento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUF() {
        return UF;
    }

    public void setUF(String UF) {
        this.UF = UF;
    }
/*
    public Cond getConds() {
        return conds;
    }
*/
    public void setConds(Cond conds) {
        this.conds = conds;
    }

}
