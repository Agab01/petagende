package br.edu.ucsal.petagende.controller;

import br.edu.ucsal.petagende.domain.Agendamento;
import br.edu.ucsal.petagende.service.AgendamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agendamentos")
public class AgendamentoController {

    private final AgendamentoService service;

    public AgendamentoController(AgendamentoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> agendar(@RequestBody Agendamento agendamento) {
        try {
            Agendamento salvo = service.criar(agendamento);
            return ResponseEntity.ok(salvo);
        } catch (RuntimeException e) {

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Agendamento>> listarAgenda() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @PatchMapping("/{id}/concluir")
    public ResponseEntity<?> concluir(@PathVariable Long id) {
        try {
            Agendamento concluido = service.concluirServico(id);
            return ResponseEntity.ok(concluido);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}