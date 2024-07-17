package br.com.rdias.dao.jpa;


import main.java.br.com.rdias.dao.generic.jpa.GenericJpaDB1DAO;
import main.java.br.com.rdias.domain.jpa.ProdutoJpa;

public class ProdutoJpaDAO extends GenericJpaDB1DAO<ProdutoJpa, Long> implements IProdutoJpaDAO {

    public ProdutoJpaDAO() {
        super(ProdutoJpa.class);
    }

}