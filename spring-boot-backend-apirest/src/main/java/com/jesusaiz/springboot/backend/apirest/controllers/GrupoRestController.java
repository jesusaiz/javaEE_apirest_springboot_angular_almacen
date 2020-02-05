package com.jesusaiz.springboot.backend.apirest.controllers;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jesusaiz.springboot.backend.apirest.models.entity.Categoria;
import com.jesusaiz.springboot.backend.apirest.models.entity.Grupo;
import com.jesusaiz.springboot.backend.apirest.models.services.IGrupoService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class GrupoRestController {

	@Autowired
	private IGrupoService grupoService;

	@GetMapping("/grupos")
	public List<Grupo> index() {
		return grupoService.findAll();
	}
	
	
	
//////SHOW
	@GetMapping("/grupos/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		//manejamos errores
		Grupo  grupo = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			grupo = grupoService.findById(id);
			
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la Base de Datos");
			response.put("error", e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			
		}
		
		
		//preguntamos
		
		if (grupo == null) {
			response.put("mensaje", "El Grupo ID: ".concat(id.toString().concat(" No existe en la Base de Datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Grupo>(grupo, HttpStatus.OK); 
	}
	
	
	
//////CREATE
	@PostMapping("/grupos")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@RequestBody Grupo grupo) {
		
		Grupo  grupoNew = null;
		Map<String, Object> response = new HashMap<>();
		
		
		try {
			grupoNew = grupoService.save(grupo);
			
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la Base de Datos");
			response.put("error", e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El Grupo ha sido creado con éxito!");
		response.put("grupo", grupoNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		
		
		
	}
	
	
	
//////UPDATE
	@PutMapping("/grupos/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Grupo grupo, BindingResult result, @PathVariable Long id)  {
		
		Grupo currentGrupo = grupoService.findById(id);

		Grupo grupoUpdated = null;

		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		if (currentGrupo == null) {
			response.put("mensaje", "Error: no se pudo editar, el grupo ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {

			currentGrupo.setDisco(grupo.getDisco());
			currentGrupo.setNombre(grupo.getNombre());
			currentGrupo.setFecha(grupo.getFecha());
			currentGrupo.setCategoria(grupo.getCategoria());

			grupoUpdated = grupoService.save(currentGrupo);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el grupo en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El grupo ha sido actualizado con éxito!");
		response.put("cliente", grupoUpdated);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		
	
	}
	
	
	
//////DELETE
	@DeleteMapping("/grupos/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();
		
		try {
		    grupoService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el grupo de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El grupo eliminado con éxito!");
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	
//////CATEGORIAS FIND
		@GetMapping("/grupos/categorias")
		public List<Categoria> ListarCategorias(){
			return grupoService.findAllCategorias();
			
		}
	
}
