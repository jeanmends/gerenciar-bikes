package bikes.bike_manager.model;

import java.util.List;

public record DadosCond (Integer ahreasCode,
                         String nomeCondomonio,
                         List<DadosEndereco> enderecoList
                         ) {
}
