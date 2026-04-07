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

import bts.sio.azurimmo.model.Batiment;
import bts.sio.azurimmo.repository.BatimentRepository;

@RestController
@RequestMapping("/batiments")
public class BatimentController {

	@Autowired
	private BatimentRepository batimentRepository;

	// GET /batiments — liste tous les bâtiments
	@GetMapping
	public List<Batiment> getAll() {
		return batimentRepository.findAll();
	}

	// GET /batiments/{id} — récupère un bâtiment par son id
	@GetMapping("/{id}")
	public ResponseEntity<Batiment> getById(@PathVariable Long id) {
		Optional<Batiment> batiment = batimentRepository.findById(id);
		return batiment.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	// POST /batiments — crée un bâtiment
	@PostMapping
	public ResponseEntity<Batiment> create(@RequestBody Batiment batiment) {
		Batiment saved = batimentRepository.save(batiment);
		return ResponseEntity.status(HttpStatus.CREATED).body(saved);
	}

	// PUT /batiments/{id} — met à jour un bâtiment
	@PutMapping("/{id}")
	public ResponseEntity<Batiment> update(@PathVariable Long id, @RequestBody Batiment updated) {
		if (!batimentRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		updated.setId(id);
		return ResponseEntity.ok(batimentRepository.save(updated));
	}

	// DELETE /batiments/{id} — supprime un bâtiment
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		if (!batimentRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		batimentRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
