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
import cl.web.zoo.DAO.impl.TipoDAO;
import cl.web.zoo.entity.Tipo;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class ApiTipoController {

  @Autowired
  private TipoDAO tipoDao;

  @GetMapping("/tipo")
  public Iterable<Tipo> listarTipos() {
    return tipoDao.getCrud().findAll();
  }

  @PostMapping("/tipo")
  public ResponseEntity<Tipo> agregarTipos(@RequestBody Tipo tipo) {
    try {
      Tipo tipoCreado = tipoDao.getCrud().save(tipo);
      return new ResponseEntity<Tipo>(tipoCreado, HttpStatus.ACCEPTED);
    } catch (Exception e) {
      return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
  }

  @PutMapping("/tipo/{id}")
  public ResponseEntity<Tipo> editarTipo(@RequestBody Tipo tipo, @PathVariable("id") int id) {

    Tipo tipoBuscado = null;
    try {
      tipoBuscado = tipoDao.getCrud().findById(id).get();
      tipoBuscado.setNombre(tipo.getNombre());
      tipoBuscado.setDescripcion(tipo.getDescripcion());
    } catch (Exception e) {
      return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    try {
      Tipo tipoModificado = tipoDao.getCrud().save(tipoBuscado);
      return new ResponseEntity<Tipo>(tipoModificado, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("/tipo/{id}")
  public ResponseEntity<Tipo> buscarTipo(@PathVariable("id") int id) {

    Tipo tipoBuscado = null;
    try {
      tipoBuscado = tipoDao.getCrud().findById(id).get();
      return new ResponseEntity<Tipo>(tipoBuscado, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
  }

  @DeleteMapping("/tipo/{id}")
  public ResponseEntity<Map<String, String>> eliminar(@PathVariable("id") int id) {

    try {
      tipoDao.getCrud().deleteById(id);
      HashMap<String, String> mensaje = new HashMap<>();
      mensaje.put("mensaje", "Eliminado correctamente");
      return new ResponseEntity<Map<String, String>>(mensaje, HttpStatus.OK);
    } catch (Exception ex) {
      return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
  }
}
