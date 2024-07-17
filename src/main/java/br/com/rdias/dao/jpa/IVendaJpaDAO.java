
package br.com.rdias.dao.jpa;


import main.java.br.com.rdias.dao.generic.jpa.IGenericJapDAO;
import main.java.br.com.rdias.domain.jpa.VendaJpa;
import main.java.br.com.rdias.exceptions.DAOException;
import main.java.br.com.rdias.exceptions.TipoChaveNaoEncontradaException;

public interface IVendaJpaDAO extends IGenericJapDAO<VendaJpa, Long> {

	public void finalizarVenda(VendaJpa venda) throws TipoChaveNaoEncontradaException, DAOException;
	
	public void cancelarVenda(VendaJpa venda) throws TipoChaveNaoEncontradaException, DAOException;
	

	public VendaJpa consultarComCollection(Long id);
}
