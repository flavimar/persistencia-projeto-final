package br.ufg.persistencia.agendamento_vacinacao.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Conexao {
    private static final EntityManagerFactory FABRICA =
            Persistence.createEntityManagerFactory("postgresql");
    public static EntityManager getEntityManager(){
        return FABRICA.createEntityManager();
    }

}
