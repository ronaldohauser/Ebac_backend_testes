
package br.com.rdias.services.generic.jpa;

import main.java.br.com.rdias.dao.generic.jpa.IGenericJapDAO;
import main.java.br.com.rdias.domain.jpa.Persistente;
import main.java.br.com.rdias.exceptions.DAOException;
import main.java.br.com.rdias.exceptions.MaisDeUmRegistroException;
import main.java.br.com.rdias.exceptions.TableException;
import main.java.br.com.rdias.exceptions.TipoChaveNaoEncontradaException;

import java.io.Serializable;
import java.util.Collection;


public abstract class GenericJpaService<T extends Persistente, E extends Serializable>
	implements IGenericJpaService<T, E> {
	
	protected IGenericJapDAO<T, E> dao;
	
	public GenericJpaService(IGenericJapDAO<T, E> dao) {
		this.dao = dao;
	}
	

	@Override
	public T cadastrar(T entity) throws TipoChaveNaoEncontradaException, DAOException {
		return this.dao.cadastrar(entity);
	}

	@Override
	public void excluir(T entity) throws DAOException {
		this.dao.excluir(entity);
	}

	@Override
	public T alterar(T entity) throws TipoChaveNaoEncontradaException, DAOException {
		return this.dao.alterar(entity);
	}

	@Override
	public T consultar(E valor) throws MaisDeUmRegistroException, TableException, DAOException {
		return this.dao.consultar(valor);
	}

	@Override
	public Collection<T> buscarTodos() throws DAOException {
		return this.dao.buscarTodos();
	}
	

}
