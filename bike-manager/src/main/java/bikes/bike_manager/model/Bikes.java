package bikes.bike_manager.model;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Bikes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_bike")
    private Long idBike;

    private String codigoSerie;
    private Long numeroPatrimonio;
    private String descricao;
    private String imagem;
    private Long idCond;

    @ManyToOne
    @JoinColumn(name = "id_condominio")
    private Cond cond;
    public Bikes(){}

    public Bikes(DadosBike dados){
        this.idBike = dados.idBike();
        this.codigoSerie = dados.codigoSerie();
        this.numeroPatrimonio = dados.numeroPatrimonio();
        this.descricao = dados.descricao();
        this.imagem = dados.imagem();
        this.idCond = dados.idCond(); 
    }
    public Bikes(Long idBike, String codigoSerie, Long numeroPatrimonio, String descricao,
                 String imagem, Cond cond) {
        this.idBike = idBike;
        this.codigoSerie = codigoSerie;
        this.numeroPatrimonio = numeroPatrimonio;
        this.descricao = descricao;
        this.imagem = imagem;
        this.idCond = cond.getIdCondomonio();
       // this.cond = cond;
    }

    public void atualizarBike(DadosBike dados){
        this.codigoSerie = dados.codigoSerie();
        this.idCond = dados.idCond();
        this.numeroPatrimonio = dados.numeroPatrimonio();
        this.imagem = dados.imagem();
    }

    public Long getIdBike() {
        return idBike;
    }

    public void setIdBike(Long idBike) {
        this.idBike = idBike;
    }

    public String getCodigoSerie() {
        return codigoSerie;
    }

    public void setCodigoSerie(String codigoSerie) {
        this.codigoSerie = codigoSerie;
    }

    public Long getNumeroPatrimonio() {
        return numeroPatrimonio;
    }

    public void setNumeroPatrimonio(Long numeroPatrimonio) {
        this.numeroPatrimonio = numeroPatrimonio;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public Long getIdCond() {
        return idCond;
    }

    public void setIdCond(Long idCond) {
        this.idCond = idCond;
    }

    public Cond getCond() {
        return cond;
    }

    public void setCond(Cond cond) {
        this.cond = cond;
    }

    @Override
    public String toString() {
        return "Bikes{" +
                "idBike=" + idBike +
                ", codigoSerie='" + codigoSerie + '\'' +
                ", numeroPatrimonio=" + numeroPatrimonio +
                ", descricao='" + descricao + '\'' +
                ", imagem='" + imagem + '\'' +
                ", idCond=" + idCond +
                ", cond=" + cond +
                '}';
    }
}
