package bikes.bike_manager.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Cond {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_condominio")
    private Long idCondomonio;
    private Integer ahreasCode;
    private String nomeCondomonio;



    @OneToMany(mappedBy="cond")
    private List<Bikes> bikes;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;
    public Cond(){}
    public Cond(Long idCondomonio, Integer ahreasCode, String nomeCondomonio, List<Bikes> bikes, Endereco endereco) {
        this.idCondomonio = idCondomonio;
        this.ahreasCode = ahreasCode;
        this.nomeCondomonio = nomeCondomonio;
        this.bikes = bikes;
        this.endereco = endereco;
    }

    public Cond (DadosCond dados) {
        this.ahreasCode = dados.ahreasCode();
        this.nomeCondomonio = dados.nomeCondomonio();
        this.endereco = endereco;
    }

    public Cond (Endereco id, DadosCond dados){
        this.endereco = id;
        this.nomeCondomonio = dados.nomeCondomonio();
        this.ahreasCode = dados.ahreasCode();
    }
    public void atualizarBikesNoCondominio(List<Bikes> bikes){
       // this.bikes = cod.setBikes(bikes);
        this.bikes = setBikes(bikes);
    }
    public Long getIdCondomonio() {
        return idCondomonio;
    }

    public void setIdCondomonio(Long idCondomonio) {
        this.idCondomonio = idCondomonio;
    }

    public Integer getAhreasCode() {
        return ahreasCode;
    }

    public void setAhreasCode(Integer ahreasCode) {
        this.ahreasCode = ahreasCode;
    }

    public String getNomeCondomonio() {
        return nomeCondomonio;
    }

    public void setNomeCondomonio(String nomeCondomonio) {
        this.nomeCondomonio = nomeCondomonio;
    }

    public List<Bikes> getBikes() {
       return bikes;
    }

    public List<Bikes> setBikes(List<Bikes> bikes) {
        this.bikes = bikes;
        return bikes;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {

        this.endereco = endereco;
        endereco.setConds(this);
    }

    @Override
    public String toString() {
        return "Cond{" +
                "idCondomonio=" + idCondomonio +
                ", ahreasCode=" + ahreasCode +
                ", nomeCondomonio='" + nomeCondomonio + '\'' +
                ", bikes=" + bikes +
                ", endereco=" + endereco +
                '}';
    }
}
