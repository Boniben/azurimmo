package bts.sio.azurimmo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bts.sio.azurimmo.model.Appartement;
import bts.sio.azurimmo.service.AppartementService;

/**
 * Controller REST pour l'entité Appartement.
 *
 * Contrairement au BatimentController, ce controller passe par une couche
 * Service (AppartementService) pour respecter l'architecture en couches :
 *   Requête HTTP → Controller → Service → Repository → Base de données
 *
 * @RestController : indique que cette classe gère des requêtes HTTP
 *                   et retourne du JSON.
 * @RequestMapping("/api/appartements") : préfixe "/api" pour distinguer
 *                   ces routes des routes auto-générées par Spring Data REST.
 */
@RestController
@RequestMapping("/api/appartements")
public class AppartementController {

	/**
	 * Injection du service par Spring.
	 * Le controller délègue toute la logique au service.
	 */
	@Autowired
	private AppartementService appartementService;

	/**
	 * Crée un nouvel appartement en base de données.
	 * POST http://localhost:9008/api/appartements/
	 *
	 * Exemple de body JSON :
	 * {
	 *   "numero": 1,
	 *   "surface": 45.5,
	 *   "nbPiecesPrincipales": 2,
	 *   "description": "Appartement lumineux",
	 *   "batiment": { "id": 1 }
	 * }
	 */
	@PostMapping("/")
	public Appartement createAppartement(@RequestBody Appartement appartement) {
		return appartementService.saveAppartement(appartement);
	}

	/**
	 * Retourne la liste des appartements situés dans une ville donnée.
	 * GET http://localhost:9008/api/appartements/ville/{ville}
	 *
	 * Exemple : /api/appartements/ville/Dijon
	 * → retourne tous les appartements dont le bâtiment est à Dijon.
	 */
	@GetMapping("/ville/{ville}")
	public List<Appartement> getByVille(@PathVariable String ville) {
		return appartementService.findByVille(ville);
	}

	/**
	 * Retourne la liste des appartements d'un bâtiment donné.
	 * GET http://localhost:9008/api/appartements/batiment/{batimentId}
	 *
	 * Exemple : /api/appartements/batiment/1
	 * → retourne tous les appartements du bâtiment avec id=1.
	 */
	@GetMapping("/batiment/{batimentId}")
	public List<Appartement> getAppartementsParBatiment(@PathVariable long batimentId) {
		return appartementService.getAppartementsParBatiment(batimentId);
	}

	/**
	 * Retourne la liste des appartements dont la surface est supérieure
	 * à la valeur passée en paramètre.
	 * GET http://localhost:9008/api/appartements/surface/{surface}
	 *
	 * Exemple : /api/appartements/surface/50.0
	 * → retourne tous les appartements de plus de 50 m².
	 */
	@GetMapping("/surface/{surface}")
	public List<Appartement> getBySurfaceGreaterThan(@PathVariable Float surface) {
		return appartementService.findBySurfaceGreaterThan(surface);
	}

}
