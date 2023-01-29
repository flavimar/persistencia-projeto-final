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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;

@WebServlet("/agenda/*")
public class ControleAgenda extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private EntityManager en;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sPath = request.getPathInfo();
        switch (sPath) {
            case "/inserir":
                insert(request, response);
                break;
            case "/atualizar":
                try {
                    update(request, response);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
        }
        response.sendRedirect("listar");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String sPath = request.getPathInfo();
        switch (sPath) {
            case "/inserir":
                getInsert(request,response);
                break;
            case "/atualizar":
                getUpdate(request,response);
                break;
            case "/remover":
                delete(request,response);
                break;
            case "/listar":
                getList(request,response);
                break;
            default:
                getList(request,response);
                break;
        }
    }
    protected void insert(HttpServletRequest request, HttpServletResponse response){
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
            en = Conexao.getEntityManager();
            DaoAgenda daoAgenda = new DaoAgenda(en);
            daoAgenda.create(agenda);
        }catch (ParseException e){
            e.printStackTrace();
        }
    }
    @SneakyThrows
    protected void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Agenda agenda = new Agenda();
        agenda.setId(Long.parseLong(request.getParameter("id")));
        String data = request.getParameter("data");
        agenda.setData(sdf.parse(data));
        agenda.setHora(LocalTime.parse(request.getParameter("hora")));
        agenda.setDataSituacao(sdf.parse(request.getParameter("dataSituacao")));
        String sit = request.getParameter("situacao");
        agenda.setSituacao(TipoSituacao.valueOf(sit));
        agenda.setObservacao(request.getParameter("obs"));
        en = Conexao.getEntityManager();
        DaoAgenda daoAgenda = new DaoAgenda(en);
        Agenda upAgenda = daoAgenda.findById(agenda.getId());
        if(upAgenda == null){
            response.sendRedirect("listar?ms='Agenda não encontrado'");
        }else {
            upAgenda.atualizarAgenda(agenda);
            daoAgenda.update(upAgenda);
            en.close();
        }
    }
    public void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long id = Long.parseLong(request.getParameter("id"));
        en = Conexao.getEntityManager();
        DaoAgenda daoAgenda = new DaoAgenda(en);
        Agenda agenda = daoAgenda.findById(id);
        if(agenda == null){
            response.sendRedirect("listar?ms='Agenda não encontrado'");
        }
        daoAgenda.delete(agenda);
        en.close();
        response.sendRedirect("listar");
    }

    private void getInsert(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher rd = request.getRequestDispatcher("/templates/agenda/cadastrar-agenda.html");
        rd.forward(request, response);
    }
    private void getUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        long id =  Long.parseLong(request.getParameter("id"));
        en = Conexao.getEntityManager();
        DaoAgenda daoAgenda = new DaoAgenda(en);
        Agenda agenda = daoAgenda.findById(id);
        RequestDispatcher rd = request.getRequestDispatcher("/templates/agenda/editar-agenda.jsp");
        request.setAttribute("agenda",agenda);
        en.close();
        rd.forward(request, response);

    }
    protected void getList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        en = Conexao.getEntityManager();
        DaoAgenda daoAgenda = new DaoAgenda(en);
        java.util.List<Agenda> agendas = daoAgenda.findAll();
        RequestDispatcher rd = request.getRequestDispatcher("/templates/agenda/listar-agendas.jsp");
        request.setAttribute("lista", agendas);
        request.setAttribute("ms", request.getParameter("ms"));
        rd.forward(request, response);
    }
}
