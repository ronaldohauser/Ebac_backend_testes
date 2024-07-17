package br.com.rdias.dao.jpa;

import main.java.br.com.rdias.dao.generic.jpa.GenericJpaDAO;
import main.java.br.com.rdias.domain.jpa.ClienteJpa;
import main.java.br.com.rdias.domain.jpa.ProdutoJpa;
import main.java.br.com.rdias.domain.jpa.VendaJpa;
import main.java.br.com.rdias.exceptions.DAOException;
import main.java.br.com.rdias.exceptions.TipoChaveNaoEncontradaException;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class VendaJpaDAO extends GenericJpaDAO<VendaJpa, Long> implements IVendaJpaDAO {

	public VendaJpaDAO() {
		super(VendaJpa.class);
	}

	@Override
	public void finalizarVenda(VendaJpa venda) throws TipoChaveNaoEncontradaException, DAOException {
		super.alterar(venda);
	}

	@Override
	public void cancelarVenda(VendaJpa venda) throws TipoChaveNaoEncontradaException, DAOException {
		super.alterar(venda);
	}

	@Override
	public void excluir(VendaJpa entity) throws DAOException {
		throw new UnsupportedOperationException("OPERAÇÃO NÃO PERMITIDA");
	}

	@Override
	public VendaJpa cadastrar(VendaJpa entity) throws TipoChaveNaoEncontradaException, DAOException {
		openConnection();
		try {
			entity.getProdutos().forEach(prod -> {
				ProdutoJpa prodJpa = entityManager.merge(prod.getProduto());
				prod.setProduto(prodJpa);
			});
			ClienteJpa cliente = entityManager.merge(entity.getCliente());
			entity.setCliente(cliente);
			entityManager.persist(entity);
			entityManager.getTransaction().commit();
			return entity;
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw new DAOException("ERRO SALVANDO VENDA", e);
		} finally {
			closeConnection();
		}
	}

	@Override
	public VendaJpa consultarComCollection(Long id) throws DAOException {
		openConnection();
		try {
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaQuery<VendaJpa> query = builder.createQuery(VendaJpa.class);
			Root<VendaJpa> root = query.from(VendaJpa.class);
			root.fetch("cliente");
			root.fetch("produtos");
			query.select(root).where(builder.equal(root.get("id"), id));
			TypedQuery<VendaJpa> tpQuery = entityManager.createQuery(query);
			return tpQuery.getSingleResult();
		} catch (Exception e) {
			throw new DAOException("Erro ao consultar venda", e);
		} finally {
			closeConnection();
		}
	}
}
