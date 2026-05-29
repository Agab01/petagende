package br.edu.ucsal.petagende.service;

import br.edu.ucsal.petagende.domain.Agendamento;
import br.edu.ucsal.petagende.repository.AgendamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendamentoService {

    private final AgendamentoRepository repository;

    public AgendamentoService(AgendamentoRepository repository) {
        this.repository = repository;
    }

    public Agendamento criar(Agendamento agendamento) {
        boolean existeConflito = repository.existsByTecnicoIdAndDataHora(
                agendamento.getTecnico().getId(),
                agendamento.getDataHora()
        );

        if (existeConflito) {
            throw new RuntimeException("Conflito de agenda: Este profissional já possui um serviço neste horário exato.");
        }

        return repository.save(agendamento);
    }

    public List<Agendamento> listarTodos() {
        return repository.findAll();
    }


    public Agendamento concluirServico(Long id) {
        Agendamento agendamento = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agendamento não encontrado."));

        agendamento.setStatus("CONCLUIDO");
        Agendamento salvo = repository.save(agendamento);

        System.out.println("🔔 [NOTIFICAÇÃO API] Disparando aviso de prontidão para o tutor do Pet ID: " + salvo.getPet().getId());

        return salvo;
    }
}