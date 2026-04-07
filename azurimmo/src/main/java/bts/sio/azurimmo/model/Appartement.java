package bts.sio.azurimmo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Classe Model représentant un appartement.
 *
 * @Data (Lombok) : génère automatiquement getters, setters, toString, equals, hashCode.
 * @Entity       : indique que cette classe est mappée sur une table en base de données.
 * @Table        : précise le nom de la table SQL ("appartement").
 */
@Data
@Entity
@Table(name = "appartement")
public class Appartement {

	/**
	 * Identifiant unique de l'appartement (clé primaire, auto-incrémenté).
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * Numéro de l'appartement dans le bâtiment (ex: appartement n°3).
	 */
	@Column(name = "numero")
	private Integer numero;

	/**
	 * Surface de l'appartement en m².
	 */
	@Column(name = "surface")
	private Float surface;

	/**
	 * Nombre de pièces principales (salon, chambres — hors cuisine, salle de bain...).
	 */
	@Column(name = "nb_pieces_principales")
	private Integer nbPiecesPrincipales;

	/**
	 * Description libre de l'appartement.
	 */
	@Column(name = "description")
	private String description;

	/**
	 * Relation ManyToOne vers Batiment :
	 *   - Plusieurs appartements peuvent appartenir à un seul bâtiment.
	 *   - @ManyToOne : définit la relation côté "plusieurs" (l'appartement).
	 *   - @JoinColumn(name = "batiment_id") : la colonne de clé étrangère dans
	 *     la table "appartement" qui pointe vers la clé primaire de "batiment".
	 */
	@ManyToOne
	@JoinColumn(name = "batiment_id")
	private Batiment batiment;

}
