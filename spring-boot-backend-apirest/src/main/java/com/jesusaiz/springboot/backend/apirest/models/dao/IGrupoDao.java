package com.jesusaiz.springboot.backend.apirest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.jesusaiz.springboot.backend.apirest.models.entity.Categoria;
import com.jesusaiz.springboot.backend.apirest.models.entity.Grupo;

public interface IGrupoDao extends CrudRepository<Grupo, Long>{
	
 //al tener una sola consulta lo asignamos aquí y mapeamos consulta y personalizamos
	@Query("from Categoria")
	public List<Categoria> findAllCategorias();
}
