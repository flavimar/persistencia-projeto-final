package br.ufg.persistencia.agendamento_vacinacao.dao;

import br.ufg.persistencia.agendamento_vacinacao.model.Agenda;
import br.ufg.persistencia.agendamento_vacinacao.model.TipoSituacao;
import br.ufg.persistencia.agendamento_vacinacao.model.Vacina;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.logging.Filter;

public class DaoAgenda {
    private EntityManager entityManager;
    public DaoAgenda(EntityManager entityManager){
        this.entityManager = entityManager;
    }
    public void create(Agenda user){
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();

    }
    public List<Agenda> findAll(TipoSituacao filtro){
        entityManager.getTransaction().begin();
        StringBuilder sb = new StringBuilder();
        sb.append("select a from Agenda as a");
        if(filtro != null){
            sb.append(" where a.situacao = ?1");
        }
        Query query = entityManager.createQuery(sb.toString());
        if(filtro != null){
            query.setParameter(1,filtro);
        }
        List<Agenda> users = query.getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return users;
    }
    public List<Agenda> findByVacinaId(long vacinaId){
        entityManager.getTransaction().begin();
        List<Agenda> agendas = entityManager.createQuery("select a from Agenda as a where a.vacina.id = ?1")
                .setParameter(1,vacinaId)
                .getResultList();
        entityManager.getTransaction().commit();
        return agendas;
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
