package com.jesusaiz.springboot.backend.apirest.models.services;

import java.util.List;

import com.jesusaiz.springboot.backend.apirest.models.entity.Categoria;
import com.jesusaiz.springboot.backend.apirest.models.entity.Grupo;

public interface IGrupoService {
	
	public List<Grupo> findAll();
	
	public Grupo save(Grupo grupo);
	
	public Grupo findById(Long id);
	
	public void delete(Long id);
	
	public List<Categoria> findAllCategorias();

}
