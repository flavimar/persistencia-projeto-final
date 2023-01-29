package br.ufg.persistencia.agendamento_vacinacao.dao;

import br.ufg.persistencia.agendamento_vacinacao.model.Agenda;

import javax.persistence.EntityManager;
import java.util.List;

public class DaoAgenda {
    private EntityManager entityManager;
    public DaoAgenda(EntityManager entityManager){
        this.entityManager = entityManager;
    }
    public void create(Agenda user){
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    public List<Agenda> findAll(){
        entityManager.getTransaction().begin();
        List<Agenda> users = entityManager.createQuery("select u from Agenda as u").getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return users;
    }
    public Agenda findById(long id){
        entityManager.getTransaction().begin();
        Agenda agenda = entityManager.find(Agenda.class,id);
        entityManager.getTransaction().commit();
        return agenda;
    }
    public void delete(Agenda user){
        Agenda deleteUser = entityManager.find(Agenda.class,user.getId());
        entityManager.getTransaction().begin();
        entityManager.remove(deleteUser);
        entityManager.getTransaction().commit();
    }
    public Agenda update(Agenda user){
        entityManager.getTransaction().begin();
        Agenda user1 = entityManager.merge(user);
        entityManager.getTransaction().commit();
        return user1;
    }
}
