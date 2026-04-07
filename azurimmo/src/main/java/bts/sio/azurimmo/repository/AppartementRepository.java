package bts.sio.azurimmo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bts.sio.azurimmo.model.Appartement;

@Repository
public interface AppartementRepository extends JpaRepository<Appartement, Long> {
}
