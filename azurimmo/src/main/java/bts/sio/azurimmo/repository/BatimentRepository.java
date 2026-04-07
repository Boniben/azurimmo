package bts.sio.azurimmo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bts.sio.azurimmo.model.Batiment;

/**
 * Repository pour l'entité Batiment.
 *
 * @Repository : indique à Spring que cette interface est un composant
 *               de la couche d'accès aux données. Spring en crée automatiquement
 *               une implémentation au démarrage.
 *
 * JpaRepository<Batiment, Long> : fournit automatiquement toutes les opérations
 * CRUD sans écrire de code SQL :
 *   - findAll()        → SELECT * FROM batiment
 *   - findById(id)     → SELECT * FROM batiment WHERE id = ?
 *   - save(batiment)   → INSERT ou UPDATE selon si l'id existe déjà
 *   - deleteById(id)   → DELETE FROM batiment WHERE id = ?
 *   - existsById(id)   → vérifie si un enregistrement existe
 *
 * Le second paramètre "Long" est le type de la clé primaire.
 */
@Repository
public interface BatimentRepository extends JpaRepository<Batiment, Long> {
}
