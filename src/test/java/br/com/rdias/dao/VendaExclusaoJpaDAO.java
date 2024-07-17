package br.com.rdias.dao;


import main.java.br.com.rdias.dao.generic.jpa.GenericJpaDB1DAO;
import main.java.br.com.rdias.dao.jpa.IVendaJpaDAO;
import main.java.br.com.rdias.domain.jpa.VendaJpa;
import main.java.br.com.rdias.exceptions.DAOException;
import main.java.br.com.rdias.exceptions.TipoChaveNaoEncontradaException;

public class VendaExclusaoJpaDAO extends GenericJpaDB1DAO<VendaJpa, Long> implements IVendaJpaDAO {

	public VendaExclusaoJpaDAO() {
		super(VendaJpa.class);
	}

	@Override
	public void finalizarVenda(VendaJpa venda) throws TipoChaveNaoEncontradaException, DAOException {
		throw new UnsupportedOperationException("OPERAÇÃO NÃO PERMITIDA");
	}

	@Override
	public void cancelarVenda(VendaJpa venda) throws TipoChaveNaoEncontradaException, DAOException {
		throw new UnsupportedOperationException("OPERAÇÃO NÃO PERMITIDA");
	}

	@Override
	public VendaJpa consultarComCollection(Long id) {
		throw new UnsupportedOperationException("OPERAÇÃO NÃO PERMITIDA");
	}

}
