package br.com.rdias.dao.generic.jpa;

import main.java.br.com.rdias.domain.jpa.Persistente;
import main.java.br.com.rdias.exceptions.DAOException;
import main.java.br.com.rdias.exceptions.MaisDeUmRegistroException;
import main.java.br.com.rdias.exceptions.TableException;
import main.java.br.com.rdias.exceptions.TipoChaveNaoEncontradaException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public class GenericJpaDAO<T extends Persistente, E extends Serializable> implements IGenericJpaDAO<T, E> {

	private static final String PERSISTENCE_UNIT_NAME = "Postgre1";

	protected EntityManagerFactory entityManagerFactory;
	protected EntityManager entityManager;
	private Class<T> persistenteClass;
	private String persistenceUnitName;

	public GenericJpaDAO(Class<T> persistenteClass, String persistenceUnitName) {
		this.persistenteClass = persistenteClass;
		this.persistenceUnitName = persistenceUnitName;
	}

	@Override
	public T cadastrar(T entity) throws TipoChaveNaoEncontradaException, DAOException {
		openConnection();
		try {
			entityManager.persist(entity);
			entityManager.getTransaction().commit();
			return entity;
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw new DAOException("Erro ao cadastrar entidade", e);
		} finally {
			closeConnection();
		}
	}

	@Override
	public void excluir(T entity) throws DAOException {
		openConnection();
		try {
			entity = entityManager.merge(entity);
			entityManager.remove(entity);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw new DAOException("Erro ao excluir entidade", e);
		} finally {
			closeConnection();
		}
	}

	@Override
	public T alterar(T entity) throws TipoChaveNaoEncontradaException, DAOException {
		openConnection();
		try {
			entity = entityManager.merge(entity);
			entityManager.getTransaction().commit();
			return entity;
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw new DAOException("Erro ao alterar entidade", e);
		} finally {
			closeConnection();
		}
	}

	@Override
	public T consultar(E valor) throws MaisDeUmRegistroException, TableException, DAOException {
		openConnection();
		try {
			return entityManager.find(this.persistenteClass, valor);
		} finally {
			closeConnection();
		}
	}

	@Override
	public Collection<T> buscarTodos() throws DAOException {
		openConnection();
		try {
			return entityManager.createQuery(getSelectSql(), this.persistenteClass).getResultList();
		} finally {
			closeConnection();
		}
	}

	protected void openConnection() {
		entityManagerFactory = Persistence.createEntityManagerFactory(getPersistenceUnitName());
		entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
	}

	protected void closeConnection() {
		if (entityManager.isOpen()) {
			entityManager.close();
		}
		if (entityManagerFactory.isOpen()) {
			entityManagerFactory.close();
		}
	}

	private String getSelectSql() {
		return "SELECT obj FROM " + this.persistenteClass.getSimpleName() + " obj";
	}

	private String getPersistenceUnitName() {
		return (persistenceUnitName != null && !persistenceUnitName.isEmpty()) ? persistenceUnitName : PERSISTENCE_UNIT_NAME;
	}
}
