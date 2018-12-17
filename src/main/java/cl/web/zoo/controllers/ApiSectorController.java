package cl.web.zoo.controllers;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cl.web.zoo.DAO.impl.SectorDAO;
import cl.web.zoo.entity.Sector;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class ApiSectorController {

  @Autowired
  private SectorDAO sectorDao;

  @GetMapping("/sector")
  public Iterable<Sector> listarSector() {
    return sectorDao.getCrud().findAll();
  }


  @PostMapping("/sector")
  public ResponseEntity<Sector> guardarSector(@RequestBody Sector sector) {

    try {
      Sector sectorCreado = sectorDao.getCrud().save(sector);
      return new ResponseEntity<Sector>(sectorCreado, HttpStatus.ACCEPTED);
    } catch (Exception e) {
      return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
  }
  
  @GetMapping("/sector/{id}")
  public ResponseEntity<Sector> buscarSector(@PathVariable("id") int id) {

    Sector sectorBuscado = null;
    try {
      sectorBuscado = sectorDao.getCrud().findById(id).get();
      return new ResponseEntity<Sector>(sectorBuscado, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
  }


  @PutMapping("/sector/{id}")
  public ResponseEntity<Sector> editarSector(@RequestBody Sector sector,
      @PathVariable("id") int id) {

    Sector sectorBuscado = null;
    try {
      sectorBuscado = sectorDao.getCrud().findById(id).get();
      sectorBuscado.setNombre(sector.getNombre());
      sectorBuscado.setDescripcion(sector.getDescripcion());
    } catch (Exception e) {
      return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
    
    try {
      Sector sectorModificado = sectorDao.getCrud().save(sectorBuscado);
      return new ResponseEntity<Sector>(sectorModificado, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
  }


  @DeleteMapping("/sector/{id}")
  public ResponseEntity<Map<String, String>> eliminar(@PathVariable("id") int id) {
    try {
      sectorDao.getCrud().deleteById(id);
      HashMap<String, String> mensaje = new HashMap<>();
      mensaje.put("mensaje", "Eliminado correctamente");
      return new ResponseEntity<Map<String, String>>(mensaje, HttpStatus.OK);
    } catch (Exception ex) {
      return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
  }
}
