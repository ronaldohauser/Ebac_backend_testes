
package br.com.rdias.dao.generic.jpa;

import main.java.br.com.rdias.domain.jpa.Persistente;

import java.io.Serializable;


public abstract class GenericJpaDB1DAO <T extends Persistente, E extends Serializable>
	extends GenericJpaDAO<T,E> {

	public GenericJpaDB1DAO(Class<T> persistenteClass) {
		super(persistenteClass, "Postgre1");
	}

}
