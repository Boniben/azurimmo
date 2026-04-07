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

@RestController
@RequestMapping("/api/appartements")
public class AppartementController {

	@Autowired
	private AppartementService appartementService;

	@PostMapping("/")
	public Appartement createAppartement(@RequestBody Appartement appartement) {
		return appartementService.saveAppartement(appartement);
	}

	@GetMapping("/ville/{ville}")
	public List<Appartement> getByVille(@PathVariable String ville) {
		return appartementService.findByVille(ville);
	}

	@GetMapping("/batiment/{batimentId}")
	public List<Appartement> getAppartementsParBatiment(@PathVariable long batimentId) {
		return appartementService.getAppartementsParBatiment(batimentId);
	}

	@GetMapping("/surface/{surface}")
	public List<Appartement> getBySurfaceGreaterThan(@PathVariable Float surface) {
		return appartementService.findBySurfaceGreaterThan(surface);
	}

}
