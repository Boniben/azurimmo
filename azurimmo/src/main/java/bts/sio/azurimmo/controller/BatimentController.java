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

/**
 * Controller REST pour l'entité Batiment.
 *
 * Le Controller est la couche qui reçoit les requêtes HTTP et retourne
 * les réponses au client (ex: navigateur, Postman...).
 *
 * @RestController : combine @Controller et @ResponseBody — chaque méthode
 *                  retourne directement des données JSON (pas une vue HTML).
 *
 * @RequestMapping("/batiments") : toutes les routes de ce controller
 *                                 commencent par "/batiments".
 */
@RestController
@RequestMapping("/batiments")
public class BatimentController {

	/**
	 * Spring injecte automatiquement le repository au démarrage.
	 * Note : ici on utilise directement le repository sans passer par un service,
	 * ce qui est acceptable pour des opérations CRUD simples.
	 */
	@Autowired
	private BatimentRepository batimentRepository;

	/**
	 * Retourne la liste de tous les bâtiments.
	 * GET http://localhost:9008/batiments
	 */
	@GetMapping
	public List<Batiment> getAll() {
		return batimentRepository.findAll();
	}

	/**
	 * Retourne un bâtiment par son identifiant.
	 * GET http://localhost:9008/batiments/{id}
	 *
	 * @PathVariable : récupère la valeur {id} depuis l'URL.
	 * ResponseEntity : permet de contrôler le code HTTP de la réponse
	 *   - 200 OK si le bâtiment est trouvé
	 *   - 404 Not Found s'il n'existe pas
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Batiment> getById(@PathVariable Long id) {
		Optional<Batiment> batiment = batimentRepository.findById(id);
		return batiment.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	/**
	 * Crée un nouveau bâtiment.
	 * POST http://localhost:9008/batiments
	 *
	 * @RequestBody : Spring désérialise automatiquement le JSON du corps
	 *               de la requête en objet Batiment.
	 * Retourne 201 Created avec le bâtiment créé (id inclus).
	 */
	@PostMapping
	public ResponseEntity<Batiment> create(@RequestBody Batiment batiment) {
		Batiment saved = batimentRepository.save(batiment);
		return ResponseEntity.status(HttpStatus.CREATED).body(saved);
	}

	/**
	 * Met à jour un bâtiment existant.
	 * PUT http://localhost:9008/batiments/{id}
	 *
	 * On vérifie d'abord que le bâtiment existe (sinon 404),
	 * puis on force l'id pour éviter d'en créer un nouveau par erreur.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Batiment> update(@PathVariable Long id, @RequestBody Batiment updated) {
		if (!batimentRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		updated.setId(id);
		return ResponseEntity.ok(batimentRepository.save(updated));
	}

	/**
	 * Supprime un bâtiment par son identifiant.
	 * DELETE http://localhost:9008/batiments/{id}
	 *
	 * Retourne 204 No Content si la suppression est réussie,
	 * ou 404 Not Found si le bâtiment n'existe pas.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		if (!batimentRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		batimentRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
