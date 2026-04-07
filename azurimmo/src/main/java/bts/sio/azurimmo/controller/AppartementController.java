package bts.sio.azurimmo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bts.sio.azurimmo.model.Appartement;
import bts.sio.azurimmo.repository.AppartementRepository;

@RestController
@RequestMapping("/appartements")
public class AppartementController {

	@Autowired
	private AppartementRepository appartementRepository;

	// GET /appartements — liste tous les appartements
	@GetMapping
	public List<Appartement> getAll() {
		return appartementRepository.findAll();
	}

	// GET /appartements/{id} — récupère un appartement par son id
	@GetMapping("/{id}")
	public ResponseEntity<Appartement> getById(@PathVariable Long id) {
		Optional<Appartement> appartement = appartementRepository.findById(id);
		return appartement.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	// POST /appartements — crée un appartement
	@PostMapping
	public ResponseEntity<Appartement> create(@RequestBody Appartement appartement) {
		Appartement saved = appartementRepository.save(appartement);
		return ResponseEntity.status(HttpStatus.CREATED).body(saved);
	}

	// PUT /appartements/{id} — met à jour un appartement
	@PutMapping("/{id}")
	public ResponseEntity<Appartement> update(@PathVariable Long id, @RequestBody Appartement updated) {
		if (!appartementRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		updated.setId(id);
		return ResponseEntity.ok(appartementRepository.save(updated));
	}

	// DELETE /appartements/{id} — supprime un appartement
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		if (!appartementRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		appartementRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
