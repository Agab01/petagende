package br.edu.ucsal.petagende.controller;

import br.edu.ucsal.petagende.domain.Pet;
import br.edu.ucsal.petagende.repository.PetRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pets")
public class PetController {

	private final PetRepository repository;

	public PetController(PetRepository repository) {
		this.repository = repository;
	}

	@PostMapping
	public ResponseEntity<Pet> cadastrar(@RequestBody Pet pet) {
		Pet petSalvo = repository.save(pet);
		return ResponseEntity.ok(petSalvo);
	}

	@GetMapping
	public ResponseEntity<List<Pet>> consultarTodos() {
		return ResponseEntity.ok(repository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Pet> consultarPorId(@PathVariable Long id) {
		Optional<Pet> pet = repository.findById(id);
		if (pet.isPresent()) {
			return ResponseEntity.ok(pet.get());
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Pet> atualizarCadastro(@PathVariable Long id, @RequestBody Pet dadosAtualizados) {
		Optional<Pet> petExistente = repository.findById(id);

		if (petExistente.isPresent()) {
			Pet pet = petExistente.get();

			pet.setNome(dadosAtualizados.getNome());
			pet.setEspecie(dadosAtualizados.getEspecie());
			pet.setAlergias(dadosAtualizados.getAlergias());

			pet.setNomeTutor(dadosAtualizados.getNomeTutor());
			pet.setTelefoneTutor(dadosAtualizados.getTelefoneTutor());

			Pet petSalvo = repository.save(pet);
			return ResponseEntity.ok(petSalvo);
		}

		return ResponseEntity.notFound().build();
	}
}