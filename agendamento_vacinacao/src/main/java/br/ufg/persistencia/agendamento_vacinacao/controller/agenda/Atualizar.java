package br.ufg.persistencia.agendamento_vacinacao.controller.agenda;

import br.ufg.persistencia.agendamento_vacinacao.dao.Conexao;
import br.ufg.persistencia.agendamento_vacinacao.dao.DaoAgenda;
import br.ufg.persistencia.agendamento_vacinacao.model.Agenda;
import br.ufg.persistencia.agendamento_vacinacao.model.TipoSituacao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;

@WebServlet("/atualizar/*" )
public class Atualizar extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    @SneakyThrows
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Agenda agenda = new Agenda();
        agenda.setId(Integer.parseInt(request.getParameter("id")));
        String data = request.getParameter("data");
        agenda.setData(sdf.parse(data));
        agenda.setHora(LocalTime.parse(request.getParameter("hora")));
        agenda.setDataSituacao(sdf.parse(request.getParameter("dataSituacao")));
        String sit = request.getParameter("situacao");
        agenda.setSituacao(TipoSituacao.valueOf(sit));
        agenda.setObservacao(request.getParameter("obs"));
        EntityManager en = Conexao.getEntityManager();
        DaoAgenda daoAgenda = new DaoAgenda(en);
        Agenda upAgenda = daoAgenda.findById(agenda.getId());
        if(upAgenda == null){
            response.sendRedirect("listar?ms='Agenda não encontrado'");
        }else {
            upAgenda.atualizarAgenda(agenda);
            daoAgenda.update(upAgenda);
            en.close();
            response.sendRedirect("listar");
        }


    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        long id =  Long.parseLong(request.getParameter("id"));
        EntityManager en = Conexao.getEntityManager();
        DaoAgenda daoAgenda = new DaoAgenda(en);
        Agenda agenda = daoAgenda.findById(id);
        RequestDispatcher rd = request.getRequestDispatcher("/editar-agenda.jsp");
        request.setAttribute("agenda",agenda);
        en.close();
        rd.forward(request, response);

    }
}
