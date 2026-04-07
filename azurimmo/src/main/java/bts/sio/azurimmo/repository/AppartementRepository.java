package bts.sio.azurimmo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bts.sio.azurimmo.model.Appartement;

/**
 * Repository pour l'entité Appartement.
 *
 * En plus des méthodes CRUD héritées de JpaRepository, ce repository
 * déclare des méthodes de recherche personnalisées.
 *
 * Spring Data JPA génère automatiquement les requêtes SQL à partir
 * du nom des méthodes, en suivant une convention de nommage :
 *   - "findBy"   : début de toute requête de recherche
 *   - Suivi du nom de l'attribut (ou du chemin via les relations)
 *   - Mots-clés spéciaux : GreaterThan, LessThan, Between, Like...
 */
@Repository
public interface AppartementRepository extends JpaRepository<Appartement, Long> {

	/**
	 * Recherche les appartements dont le bâtiment est situé dans une ville donnée.
	 *
	 * Convention : "Batiment_Ville" suit la relation @ManyToOne vers Batiment,
	 * puis accède à l'attribut "ville" de la classe Batiment.
	 *
	 * SQL généré : SELECT * FROM appartement a
	 *              JOIN batiment b ON a.batiment_id = b.id
	 *              WHERE b.ville = ?
	 */
	List<Appartement> findByBatiment_Ville(String ville);

	/**
	 * Recherche les appartements appartenant à un bâtiment donné par son id.
	 *
	 * SQL généré : SELECT * FROM appartement WHERE batiment_id = ?
	 */
	List<Appartement> findByBatiment_Id(long id);

	/**
	 * Recherche les appartements dont la surface est strictement supérieure
	 * à la valeur donnée.
	 *
	 * "GreaterThan" est un mot-clé Spring Data JPA qui traduit l'opérateur SQL ">".
	 *
	 * SQL généré : SELECT * FROM appartement WHERE surface > ?
	 */
	List<Appartement> findBySurfaceGreaterThan(Float surface);
}
