package br.ufg.persistencia.agendamento_vacinacao.controller.agenda;

import br.ufg.persistencia.agendamento_vacinacao.dao.Conexao;
import br.ufg.persistencia.agendamento_vacinacao.dao.DaoAgenda;
import br.ufg.persistencia.agendamento_vacinacao.model.Agenda;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.persistence.EntityManager;
import java.io.IOException;

@WebServlet("/listar")
public class Listar extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Listar() {
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        EntityManager en = Conexao.getEntityManager();
        DaoAgenda daoAgenda = new DaoAgenda(en);
        java.util.List<Agenda> agendas = daoAgenda.findAll();
        RequestDispatcher rd = request.getRequestDispatcher("/listar-agendas.jsp");
        request.setAttribute("lista", agendas);
        request.setAttribute("ms", request.getParameter("ms"));
        rd.forward(request, response);
    }
}
