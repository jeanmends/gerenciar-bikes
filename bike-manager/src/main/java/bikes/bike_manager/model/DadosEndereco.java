package bikes.bike_manager.model;

import bikes.bike_manager.repository.EnderecoRepository;
import jakarta.persistence.Column;
import org.springframework.beans.factory.annotation.Autowired;

public record DadosEndereco(String cep,
                            String logradouro,
                            String complimento,
                            String bairro,
                            String cidade,
                            String UF) {

}
