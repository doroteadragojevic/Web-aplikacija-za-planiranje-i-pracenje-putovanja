package fer.hr.zavrsni.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fer.hr.zavrsni.dto.DrzavaDTO;
import fer.hr.zavrsni.service.DrzavaService;

@RestController
@RequestMapping("/drzave")
public class DrzavaController {

	@Autowired
	DrzavaService drzavaService;

	@GetMapping("/")
	public ResponseEntity<List<DrzavaDTO>> getAllDrzave() {
		return new ResponseEntity<>(drzavaService.getAllDrzave(), HttpStatus.OK);
	}

}
