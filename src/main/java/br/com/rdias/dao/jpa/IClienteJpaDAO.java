
package br.com.rdias.dao.jpa;


import main.java.br.com.rdias.dao.generic.jpa.IGenericJapDAO;
import main.java.br.com.rdias.domain.jpa.Persistente;

public interface IClienteJpaDAO<T extends Persistente> extends IGenericJapDAO<T, Long> {

}
