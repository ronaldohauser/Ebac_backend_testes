
package br.com.rdias.dao.generic.jpa;

import main.java.br.com.rdias.domain.jpa.Persistente;
import main.java.br.com.rdias.exceptions.DAOException;
import main.java.br.com.rdias.exceptions.MaisDeUmRegistroException;
import main.java.br.com.rdias.exceptions.TableException;
import main.java.br.com.rdias.exceptions.TipoChaveNaoEncontradaException;

import java.io.Serializable;
import java.util.Collection;


public interface IGenericJapDAO <T extends Persistente, E extends Serializable> {


    public T cadastrar(T entity) throws TipoChaveNaoEncontradaException, DAOException;

    public void excluir(T entity) throws DAOException;


    public T alterar(T entity) throws TipoChaveNaoEncontradaException, DAOException;


    public T consultar(E id) throws MaisDeUmRegistroException, TableException, DAOException;


    public Collection<T> buscarTodos() throws DAOException;
}