package bikes.bike_manager.controller;

import bikes.bike_manager.model.Bikes;
import bikes.bike_manager.model.Cond;
import bikes.bike_manager.model.DadosBike;
import bikes.bike_manager.model.ResponseClass;
import bikes.bike_manager.repository.BikesRepository;
import bikes.bike_manager.repository.CondRepository;
import jakarta.transaction.Transactional;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.util.*;

import static java.util.stream.Collectors.toList;
import static org.apache.commons.text.CharacterPredicates.DIGITS;
import static org.apache.commons.text.CharacterPredicates.LETTERS;

@RestController
@RequestMapping("bike")
public class BikeController {
    @Autowired
    private BikesRepository repository;

    @Autowired
    private CondRepository condRepository;
    @PostMapping
    @Transactional
    public ResponseEntity cadastar(@RequestBody DadosBike dados){
        List<Bikes> bikes = repository.findAll();
        Cond cond = condRepository.findById(dados.idCond())
                .orElseThrow(() -> new RuntimeException("Não encontrado"));
        System.out.println("antes" + cond);
        var dadosBike = repository.save(new Bikes(dados));
        List<Bikes> newBikes = repository.findAll();
        Optional<Bikes> bike = repository.findById(dadosBike.getIdBike());

        //List<Bikes> funciona = newBikes.stream().filter(nb -> nb.getIdBike().equals(dados.idBike())).collect(toList());
        //cond.setBikes(bike.stream().toList());
        cond.atualizarBikesNoCondominio(bike.stream().toList());
        System.out.println("depois" +cond);
        //condRepository.save(cond);

        //List<Bikes> lastBike = Collections.singletonList(repository.save(new Bikes(dados)));


        /*
        List<Bikes> encontrado = bikes.stream().
                filter(e -> e.getCodigoSerie().
                        equalsIgnoreCase(dados.codigoSerie())).
                collect(Collectors.toList());

        if (!encontrado.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
*/
      //  repository.save(new Bikes(dados));
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("{id}")
    @Transactional
    public void atualizarBike (@PathVariable Long id,  @RequestBody DadosBike dados){
        var bikes = repository.getReferenceById(id);
      //  Optional<Bikes> bike = repository.findById(id);

        //List<Bikes> funciona = newBikes.stream().filter(nb -> nb.getIdBike().equals(dados.idBike())).collect(toList());

        bikes.atualizarBike(dados);
        Cond cond = condRepository.getReferenceById(dados.idCond());
        cond.setBikes(List.of(bikes));
      //  condRepository.save(cond);
    }
    @GetMapping
    public List<Bikes> listar(){
        return repository.findAll(Sort.by(Sort.Direction.ASC, "idBike"));
    }

    @GetMapping("/comcondominios")
    public List<Bikes> listarBikesComCondominios(){
        List<Bikes> bikes = repository.findAll(Sort.by(Sort.Direction.ASC, "idBike"));
        Long verificador = 1L;
        System.out.println(verificador);
        return bikes.stream()
                .filter(b -> (!Objects.equals(b.getIdCond(), verificador)))
                .toList()
                .stream()
                .filter(nb -> nb.getIdCond() != null)
                .collect(toList());

        //return repository.findAll();
    }

    @GetMapping("/semcondominios")
    public List<Bikes> listarBikesSemCondominios(){
        List<Bikes> bikes = repository.findAll(Sort.by(Sort.Direction.ASC, "idBike"));
        Long verificador = 1L;
        System.out.println(verificador);
        return bikes.stream()
                .filter(b -> (Objects.equals(b.getIdCond(), verificador)))
                .collect(toList());

        //return repository.findAll();
    }


    @PostMapping("/file")
    public ResponseEntity<ResponseClass> handleFileUpload(@RequestParam("file") MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        RandomStringGenerator generator = new RandomStringGenerator.Builder()
                .withinRange('0', 'z')
                .filteredBy(LETTERS, DIGITS)
                .build();
        String codigoAleatorio = generator.generate(10);

        String fileName = (codigoAleatorio+"."+extension).toLowerCase();
        try {
            file.transferTo(new File("C:\\projetos\\servidor_imagens\\images_bikes\\" + fileName));
            String downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/download/")
                    .path(fileName)
                    .toUriString();
            ResponseClass response = new ResponseClass(fileName,
                    downloadUrl,
                    file.getContentType(),
                    file.getSize());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
        Optional<Bikes> existeBike = repository.findById(id);
        if(existeBike.isPresent()){
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> mostrarBike(@PathVariable("id") Long id) {
        Optional<Bikes> bikes = repository.findById(id);

        if(bikes.isPresent()){
           return ResponseEntity.accepted().body(bikes);
        }

        return new ResponseEntity<Error>(HttpStatus.NOT_FOUND);
    }


}
