package br.ufg.persistencia.agendamento_vacinacao.service;

import br.ufg.persistencia.agendamento_vacinacao.dao.Conexao;
import br.ufg.persistencia.agendamento_vacinacao.dao.DaoVacina;
import br.ufg.persistencia.agendamento_vacinacao.model.Vacina;

import javax.persistence.EntityManager;

public class ServicoVacina {
    EntityManager en;
    public Vacina buscaVacina(long id){
        en = Conexao.getEntityManager();
        DaoVacina daoVacina = new DaoVacina(en);
        Vacina vacina = daoVacina.findById(id);
        en.close();
        return vacina;
    }
}
