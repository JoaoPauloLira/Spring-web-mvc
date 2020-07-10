package br.com.newtec.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.newtec.model.Usuario;

@Repository
@Transactional
public class UsuarioDao {

	@PersistenceContext
	private EntityManager manager;
	
	public void save(Usuario usuario) {
		manager.persist(usuario);
	}
}
