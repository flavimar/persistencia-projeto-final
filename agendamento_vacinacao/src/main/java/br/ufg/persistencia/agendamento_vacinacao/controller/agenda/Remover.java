package br.ufg.persistencia.agendamento_vacinacao.controller.agenda;

import br.ufg.persistencia.agendamento_vacinacao.dao.Conexao;
import br.ufg.persistencia.agendamento_vacinacao.dao.DaoAgenda;
import br.ufg.persistencia.agendamento_vacinacao.model.Agenda;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.persistence.EntityManager;
import java.io.IOException;

@WebServlet("/remover")
public class Remover extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long id = Long.parseLong(request.getParameter("id"));
        EntityManager en = Conexao.getEntityManager();
        DaoAgenda daoAgenda = new DaoAgenda(en);
        Agenda agenda = daoAgenda.findById(id);
        if(agenda == null){
            response.sendRedirect("listar?ms='Agenda não encontrado'");
        }
        daoAgenda.delete(agenda);
        en.close();
        response.sendRedirect("listar");
    }
}
