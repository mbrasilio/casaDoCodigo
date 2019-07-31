package br.com.casadocodigo.loja.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.com.casadocodigo.loja.models.Usuario;

@Repository
public class UsuarioDAO implements UserDetailsService {

	@PersistenceContext
	private EntityManager manager;

	public Usuario loadUserByUsername(String email) {
		List<Usuario> usuarios = manager.createQuery("select u from Usuario u where email = :email", Usuario.class)
				.setParameter("email", email).getResultList();

		if (usuarios.isEmpty()) {
			throw new UsernameNotFoundException("Usuario " + email + " não foi encontrado");
		}

		return usuarios.get(0);
	}

	public Usuario findUserByEmail(String email) {
		try {
			return manager.createQuery("select u from Usuario u where email = :email", Usuario.class)
					.setParameter("email", email).getSingleResult();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}

	public void gravar(Usuario usuario) {
		manager.persist(usuario);
	}

	public List<Usuario> listar() {
		List<Usuario> usuarios = manager
				.createQuery("select distinct(u) from Usuario u join fetch u.roles", Usuario.class).getResultList();

		if (usuarios.isEmpty()) {
			return new ArrayList<Usuario>();
		}

		return usuarios;
	}
	
	public void alterarRole(Usuario user) {
		Usuario usuario = manager.createQuery("select u from Usuario u where email = :email", Usuario.class)
				.setParameter("email", user.getEmail()).getSingleResult();
		usuario.setRoles(user.getRoles());
		
		gravar(usuario);
	}
}