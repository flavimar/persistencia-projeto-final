package br.ufg.persistencia.agendamento_vacinacao.dao;

import br.ufg.persistencia.agendamento_vacinacao.model.Agenda;
import br.ufg.persistencia.agendamento_vacinacao.model.Vacina;

import javax.persistence.EntityManager;
import java.util.List;

public class DaoVacina {
    private EntityManager entityManager;

    public DaoVacina(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    public void create(Vacina vacina){
        entityManager.getTransaction().begin();
        entityManager.persist(vacina);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    public List<Vacina> findAll(){
        entityManager.getTransaction().begin();
        List<Vacina> vacinas = entityManager.createQuery("select u from Vacina as u").getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return vacinas;
    }
    public Vacina findById(long id){
        entityManager.getTransaction().begin();
        Vacina vacina = entityManager.find(Vacina.class,id);
        entityManager.getTransaction().commit();
        return vacina;
    }
    public void delete(Vacina vacina){
        Vacina vac = entityManager.find(Vacina.class,vacina.getId());
        entityManager.getTransaction().begin();
        entityManager.remove(vac);
        entityManager.getTransaction().commit();
    }
    public Vacina update(Vacina vacina){
        entityManager.getTransaction().begin();
        Vacina vac = entityManager.merge(vacina);
        entityManager.getTransaction().commit();
        return vac;
    }
}
