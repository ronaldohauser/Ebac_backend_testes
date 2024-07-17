
package br.com.rdias.dao.jpa;


import main.java.br.com.rdias.dao.generic.jpa.GenericJpaDB1DAO;
import main.java.br.com.rdias.domain.jpa.ClienteJpa;

public class ClienteJpaDAO extends GenericJpaDB1DAO<ClienteJpa, Long> implements IClienteJpaDAO<ClienteJpa> {

	public ClienteJpaDAO() {
		super(ClienteJpa.class);
	}

}
