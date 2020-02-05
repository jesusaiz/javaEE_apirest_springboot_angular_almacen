package com.jesusaiz.springboot.backend.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jesusaiz.springboot.backend.apirest.models.dao.IGrupoDao;
import com.jesusaiz.springboot.backend.apirest.models.entity.Categoria;
import com.jesusaiz.springboot.backend.apirest.models.entity.Grupo;

@Service
public class GrupoServiceImpl implements IGrupoService{

	@Autowired
	private IGrupoDao grupoDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Grupo> findAll() {
		return (List<Grupo>) grupoDao.findAll();
	}

	@Override
	@Transactional
	public Grupo save(Grupo grupo) {
		return grupoDao.save(grupo);
	}

	@Override
	@Transactional(readOnly = true)
	public Grupo findById(Long id) {
		return grupoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		grupoDao.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<Categoria> findAllCategorias() {
		return grupoDao.findAllCategorias();
	}
	
	

}
