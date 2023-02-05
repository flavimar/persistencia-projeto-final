package br.ufg.persistencia.agendamento_vacinacao.dao;

import br.ufg.persistencia.agendamento_vacinacao.model.Alergia ;
import br.ufg.persistencia.agendamento_vacinacao.model.Usuario;

import javax.persistence.EntityManager;
import java.util.List;

public class DaoAlergia {
    private EntityManager entityManager;
    public DaoAlergia(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public void create(Alergia alergia){
        entityManager.getTransaction().begin();
        entityManager.persist(alergia);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    public List<Alergia> findAll(){
        entityManager.getTransaction().begin();
        List<Alergia> alergias = entityManager.createQuery("select u from Alergia as u").getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return alergias;
    }
    public Alergia findById(long id){
        entityManager.getTransaction().begin();
        Alergia alergia = entityManager.find(Alergia.class,id);
        entityManager.getTransaction().commit();
        return alergia;
    }
    public void delete(Alergia user){
        Alergia deleteAlergia = entityManager.find(Alergia.class,user.getId());
        entityManager.getTransaction().begin();
        entityManager.remove(deleteAlergia);
        entityManager.getTransaction().commit();
    }
    public Alergia update(Alergia alergia){
        entityManager.getTransaction().begin();
        Alergia alergia1 = entityManager.merge(alergia);
        entityManager.getTransaction().commit();
        return alergia1;
    }
}
