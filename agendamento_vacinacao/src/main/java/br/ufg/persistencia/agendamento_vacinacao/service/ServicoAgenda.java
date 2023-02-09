package br.ufg.persistencia.agendamento_vacinacao.service;

import br.ufg.persistencia.agendamento_vacinacao.dao.Conexao;
import br.ufg.persistencia.agendamento_vacinacao.dao.DaoAgenda;
import br.ufg.persistencia.agendamento_vacinacao.dao.DaoUsuario;
import br.ufg.persistencia.agendamento_vacinacao.model.Agenda;
import br.ufg.persistencia.agendamento_vacinacao.model.TipoSituacao;
import br.ufg.persistencia.agendamento_vacinacao.model.Usuario;
import br.ufg.persistencia.agendamento_vacinacao.model.Vacina;


import javax.persistence.EntityManager;

import java.util.Calendar;


public class ServicoAgenda {
    EntityManager en;
    DaoAgenda daoAgenda;

    public String insert(Agenda agenda){
            en = Conexao.getEntityManager();
            daoAgenda = new DaoAgenda(en);
            DaoUsuario daoUsuario = new DaoUsuario(en);
            Usuario usuario1 = daoUsuario.findByNome(agenda.getUsuario().getNome());
            if(usuario1 == null){
                return "usuario não existe";
            }
            agenda.setUsuario(new Usuario(usuario1.getId()));
            daoAgenda.create(agenda);
            ServicoVacina servicoVacina = new ServicoVacina();
            Vacina vacina = servicoVacina.buscaVacina(agenda.getVacina().getId());
            if(agenda.getSituacao().equals(TipoSituacao.AGENDADO)) {
                int doses = vacina.getDoses();
                Calendar c = Calendar.getInstance();
                while(doses > 1) {
                    Agenda novaAgenda = new Agenda();
                    novaAgenda.setData(agenda.getData());
                    novaAgenda.setVacina(new Vacina(agenda.getVacina().getId()));
                    novaAgenda.setSituacao(agenda.getSituacao());
                    novaAgenda.setHora(agenda.getHora());
                    novaAgenda.setUsuario(agenda.getUsuario());
                    switch (vacina.getPeriodicidade()) {
                        case DIA:
                            c.setTime(novaAgenda.getData());
                            c.add(Calendar.DATE, vacina.getIntervalo());
                            novaAgenda.setData(c.getTime());
                            daoAgenda.create(novaAgenda);
                            break;
                        case SEMANA:
                            c.setTime(novaAgenda.getData());
                            c.add(Calendar.DATE, vacina.getIntervalo() * 7);
                            novaAgenda.setData(c.getTime());
                            daoAgenda.create(novaAgenda);
                            break;
                        case MES:
                            c.setTime(novaAgenda.getData());
                            c.add(Calendar.MONTH, vacina.getIntervalo());
                            novaAgenda.setData(c.getTime());
                            daoAgenda.create(novaAgenda);
                            break;
                        case ANO:
                            c.setTime(novaAgenda.getData());
                            c.add(Calendar.YEAR, vacina.getIntervalo());
                            novaAgenda.setData(c.getTime());
                            daoAgenda.create(novaAgenda);
                            break;
                    }
                    agenda = novaAgenda;
                    doses--;
                }
            }
            en.close();
            return null;
    }
    public void addAgendas(){

    }

}
