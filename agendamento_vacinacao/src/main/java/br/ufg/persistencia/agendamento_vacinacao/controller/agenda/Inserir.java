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

import javax.persistence.EntityManager;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;

@WebServlet("/inserir")
public class Inserir extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        try {
            Agenda agenda = new Agenda();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String data = request.getParameter("data");
            agenda.setData(sdf.parse(data));
            agenda.setHora(LocalTime.parse(request.getParameter("hora")));
            agenda.setDataSituacao(sdf.parse(request.getParameter("dataSituacao")));
            String sit = request.getParameter("situacao");
            agenda.setSituacao(TipoSituacao.valueOf(sit));
            agenda.setObservacao(request.getParameter("obs"));
            EntityManager en = Conexao.getEntityManager();
            DaoAgenda daoAgenda = new DaoAgenda(en);
            daoAgenda.create(agenda);
            response.sendRedirect("listar");
        }catch (ParseException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher rd = request.getRequestDispatcher("/cadastrar-agenda.jsp");
        rd.forward(request, response);
    }
}
