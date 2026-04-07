package bts.sio.azurimmo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bts.sio.azurimmo.model.Appartement;
import bts.sio.azurimmo.repository.AppartementRepository;
import lombok.Data;

/**
 * Couche Service pour l'entité Appartement.
 *
 * Le rôle du Service est de contenir la logique métier de l'application.
 * Il fait le lien entre le Controller (qui reçoit les requêtes HTTP)
 * et le Repository (qui accède à la base de données).
 *
 * @Data    (Lombok) : génère getters, setters, toString, equals, hashCode.
 * @Service : indique à Spring que cette classe est un composant de la couche
 *            service. Spring en crée une instance unique (singleton) et la
 *            rend injectable via @Autowired.
 */
@Data
@Service
public class AppartementService {

	/**
	 * Injection du repository par Spring.
	 * @Autowired : Spring injecte automatiquement l'instance de AppartementRepository
	 *              — pas besoin de faire "new AppartementRepository()".
	 */
	@Autowired
	private AppartementRepository appartementRepository;

	/**
	 * Crée ou met à jour un appartement en base de données.
	 * Si l'appartement a déjà un id → UPDATE, sinon → INSERT.
	 *
	 * @param appartement L'appartement à sauvegarder
	 * @return L'appartement sauvegardé (avec son id généré si c'est une création)
	 */
	public Appartement saveAppartement(Appartement appartement) {
		Appartement savedAppartement = appartementRepository.save(appartement);
		return savedAppartement;
	}

	/**
	 * Retourne la liste des appartements situés dans une ville donnée
	 * (via la relation avec le bâtiment).
	 *
	 * @param ville Le nom de la ville à rechercher
	 * @return Liste des appartements de cette ville
	 */
	public List<Appartement> findByVille(String ville) {
		return appartementRepository.findByBatiment_Ville(ville);
	}

	/**
	 * Retourne la liste des appartements appartenant à un bâtiment donné.
	 *
	 * @param id L'identifiant du bâtiment
	 * @return Liste des appartements de ce bâtiment
	 */
	public List<Appartement> getAppartementsParBatiment(long id) {
		return appartementRepository.findByBatiment_Id(id);
	}

	/**
	 * Retourne la liste des appartements dont la surface est supérieure
	 * à la valeur donnée.
	 *
	 * @param surface La surface minimale (non incluse) en m²
	 * @return Liste des appartements correspondants
	 */
	public List<Appartement> findBySurfaceGreaterThan(Float surface) {
		return appartementRepository.findBySurfaceGreaterThan(surface);
	}

}
