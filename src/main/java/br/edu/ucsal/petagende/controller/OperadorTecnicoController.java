package br.edu.ucsal.petagende.controller;

import br.edu.ucsal.petagende.domain.OperadorTecnico;
import br.edu.ucsal.petagende.repository.OperadorTecnicoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tecnicos")
public class OperadorTecnicoController {

    private final OperadorTecnicoRepository repository;

    public OperadorTecnicoController(OperadorTecnicoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<OperadorTecnico>> listarTodos() {
        return ResponseEntity.ok(repository.findAll());
    }
}