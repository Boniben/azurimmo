package bts.sio.azurimmo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bts.sio.azurimmo.model.Batiment;

@Repository
public interface BatimentRepository extends JpaRepository<Batiment, Long> {
}
