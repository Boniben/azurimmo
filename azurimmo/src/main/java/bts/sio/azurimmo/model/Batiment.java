package bts.sio.azurimmo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Classe Model représentant un bâtiment.
 *
 * @Data (Lombok) : génère automatiquement à la compilation les getters, setters,
 *                  toString, equals et hashCode — évite d'écrire ce code manuellement.
 *
 * @Entity : indique à JPA (Java Persistence API) que cette classe correspond
 *           à une table en base de données.
 *
 * @Table(name = "batiment") : précise le nom exact de la table SQL associée.
 */
@Data
@Entity
@Table(name = "batiment")
public class Batiment {

	/**
	 * Identifiant unique du bâtiment (clé primaire).
	 *
	 * @Id : désigne ce champ comme clé primaire de la table.
	 * @GeneratedValue(strategy = GenerationType.IDENTITY) : la valeur est
	 *   générée automatiquement par la base de données (auto-increment).
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * Adresse du bâtiment.
	 * @Column(name = "adresse") : mappe cet attribut sur la colonne "adresse" en base.
	 */
	@Column(name = "adresse")
	private String adresse;

	/**
	 * Ville où se situe le bâtiment.
	 * @Column(name = "ville") : mappe cet attribut sur la colonne "ville" en base.
	 */
	@Column(name = "ville")
	private String ville;

}
