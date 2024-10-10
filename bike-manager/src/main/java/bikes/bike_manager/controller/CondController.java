package bikes.bike_manager.controller;

import bikes.bike_manager.model.*;
import bikes.bike_manager.repository.BikesRepository;
import bikes.bike_manager.repository.CondRepository;
import bikes.bike_manager.repository.EnderecoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("condominios")
public class CondController {
    @Autowired
    private CondRepository repository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private BikesRepository bikesRepository;
    @GetMapping
    public List<Cond> listar(){
        return repository.findAll();
    }
    @GetMapping("detalhes")
    public List<Cond> listarTodosCompleto () {
        List<Cond> cond = repository.findAll();
        List<Bikes> bikes = bikesRepository.findAll();
        List<Bikes> entrada = new ArrayList<>(bikes);

        List<Cond> newCondList = new ArrayList<>();
        List<Bikes> bikesToRemove = new ArrayList<>();
        for (Cond c : cond) {
            List<Bikes> newList = new ArrayList<>();
            for (Bikes b : entrada) {

                if (b.getIdCond().equals(c.getIdCondomonio())) {
                    newList.add(b);
                    bikesToRemove.add(b);
                }
                c.setBikes(newList);

            }
            newCondList.add(c);
           // newList.removeAll(bikesToRemove);
            //entrada.removeAll(bikesToRemove);
        }

        return newCondList;
    }


    @GetMapping("{id}")
    public List<Cond> listagarApenasUmCondominio (@PathVariable("id") Long id){

        List<Cond> cond = repository.findAll();

        List<Bikes> bikes = bikesRepository.findAll();

        List<Bikes> newList = new ArrayList<>();
        List<Cond> newCondList = new ArrayList<>();
        for (Bikes a : bikes) {
            if (a.getIdCond().equals(id)) {
                newList.add(a);
            }
        }

        for (Cond c :cond){
            if(c.getIdCondomonio().equals(id)){
                c.setBikes(newList);
                newCondList.add(c);
            }
        }
/*
        List<Cond> newCond = cond.stream().filter(c -> c.getIdCondomonio().equals(id))
                .collect(Collectors.toList());
*/
       return newCondList;

    }
    @PostMapping
    @Transactional
    public ResponseEntity cadastar(@RequestBody DadosCond dados){
        List<Cond> bikes = repository.findAll();
/*
        List<Bikes> encontrado = bikes.stream().
                filter(e -> e.getCodigoSerie().
                        equalsIgnoreCase(dados.codigoSerie())).
                collect(Collectors.toList());

        if (!encontrado.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
*/      Endereco getId = enderecoRepository.save(new Endereco(dados.enderecoList().get(0)));
        System.out.println(getId.getIdEndereco());
        repository.save(new Cond(getId, dados));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
