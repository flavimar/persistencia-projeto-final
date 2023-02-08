package br.ufg.persistencia.agendamento_vacinacao.dao;

import br.ufg.persistencia.agendamento_vacinacao.model.Agenda;
import br.ufg.persistencia.agendamento_vacinacao.model.Usuario ;

import javax.persistence.EntityManager;
import java.util.List;

public class DaoUsuario {
    private EntityManager entityManager;
    public DaoUsuario(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public void create(Usuario user){
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    public List<Usuario> findAll(){
        entityManager.getTransaction().begin();
        List<Usuario> users = entityManager.createQuery("select u from Usuario as u").getResultList();
        entityManager.getTransaction().commit();
        return users;
    }
    public Usuario findByNome(String nome){
        entityManager.getTransaction().begin();
        Usuario usuario = (Usuario) entityManager.createQuery("select u from Usuario as u where u.nome = ?1")
                .setParameter(1,nome)
                .getSingleResult();
        entityManager.getTransaction().commit();
        return usuario;
    }
    public List<Usuario> findByAlergiaId(long alergiaId){
        entityManager.getTransaction().begin();
        List<Usuario> usuarios = entityManager.createQuery("select a from Usuario as a where a.alergia.id = ?1")
                .setParameter(1,alergiaId)
                .getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return usuarios;
    }
    public Usuario findById(long id){
        entityManager.getTransaction().begin();
        Usuario user = entityManager.find(Usuario.class,id);
        entityManager.getTransaction().commit();
        return user;
    }
    public void delete(Usuario user){
        Usuario deleteUser = entityManager.find(Usuario.class,user.getId());
        entityManager.getTransaction().begin();
        entityManager.remove(deleteUser);
        entityManager.getTransaction().commit();
    }
    public Usuario update(Usuario user){
        entityManager.getTransaction().begin();
        Usuario user1 = entityManager.merge(user);
        entityManager.getTransaction().commit();
        return user1;
    }
}
