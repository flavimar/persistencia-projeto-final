package br.ufg.persistencia.agendamento_vacinacao.controller;

import br.ufg.persistencia.agendamento_vacinacao.dao.Conexao;
import br.ufg.persistencia.agendamento_vacinacao.dao.DaoAgenda;
import br.ufg.persistencia.agendamento_vacinacao.dao.DaoVacina;
import br.ufg.persistencia.agendamento_vacinacao.model.Agenda;
import br.ufg.persistencia.agendamento_vacinacao.model.Periodicidade;
import br.ufg.persistencia.agendamento_vacinacao.model.Vacina;
import br.ufg.persistencia.agendamento_vacinacao.util.StringUtil;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.util.List;

@WebServlet("/vacina/*")
public class ControleVacina extends HttpServlet {
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
                    update(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
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
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }
    protected void insert(HttpServletRequest request, HttpServletResponse response){
            Vacina vacina = new Vacina();
            vacina.setTitulo( request.getParameter("titulo"));
            if(!StringUtil.isNullOrEmpty(request.getParameter("descricao"))) {
                vacina.setDescricao(request.getParameter("descricao") );
            }
            vacina.setDoses(Integer.parseInt(request.getParameter("doses")));
            String intervalo = request.getParameter("intervalo");
            if (intervalo != null) {
                vacina.setIntervalo(Integer.parseInt(intervalo));
            }
            String periodicidade = request.getParameter("periodicidade");
            if(periodicidade != null) {
                vacina.setPeriodicidade(Periodicidade.valueOf(periodicidade));
            }

            en = Conexao.getEntityManager();
            DaoVacina daoVacina = new DaoVacina(en);
            daoVacina.create(vacina);
    }
    @SneakyThrows
    protected void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Vacina vacina = new Vacina();
        vacina.setTitulo( request.getParameter("titulo"));
        vacina.setDescricao(request.getParameter("descricao"));
        vacina.setDoses(Integer.parseInt(request.getParameter("doses")));
        vacina.setIntervalo(Integer.parseInt(request.getParameter("intervalo")));
        vacina.setPeriodicidade(Periodicidade.valueOf(request.getParameter("periodicidade")));
        en = Conexao.getEntityManager();
        DaoVacina daoVacina= new DaoVacina(en);
        Vacina upVacina = daoVacina.findById(vacina.getId());
        if(upVacina == null){
            response.sendRedirect("listar?ms='Agenda não encontrado'");
        }else {
            upVacina.atualizarVacina(vacina);
            daoVacina.update(upVacina);
            en.close();
        }
    }
    public void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long id = Long.parseLong(request.getParameter("id"));
        en = Conexao.getEntityManager();
        DaoVacina daoVacina = new DaoVacina(en);
        DaoAgenda daoAgenda = new DaoAgenda(en);
        Vacina vacina= daoVacina.findById(id);
        if(vacina == null){
            response.sendRedirect("listar?ms='Vacina não encontrada'");
        }
        List<Agenda> agendas = daoAgenda.findByVacinaId(vacina.getId());
        if (!agendas.isEmpty()){
            en.close();
            response.sendRedirect("listar?ms='vacina já vinculada a agenda'");
        }else {
            daoVacina.delete(vacina);
            en.close();
            response.sendRedirect("listar");
        }
    }

    private void getInsert(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher rd = request.getRequestDispatcher("/templates/vacina/cadastrar-vacina.jsp");
        rd.forward(request, response);
    }
    private void getUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        long id =  Long.parseLong(request.getParameter("id"));
        en = Conexao.getEntityManager();
        DaoVacina daoVacina = new DaoVacina(en);
        Vacina vacina = daoVacina.findById(id);
        RequestDispatcher rd = request.getRequestDispatcher("/templates/vacina/editar-vacina.jsp");
        request.setAttribute("vacina",vacina);
        en.close();
        rd.forward(request, response);

    }
    protected void getList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        en = Conexao.getEntityManager();
        DaoVacina daoAgenda = new DaoVacina(en);
        java.util.List<Vacina> agendas = daoAgenda.findAll();
        RequestDispatcher rd = request.getRequestDispatcher("/templates/vacina/listar-vacinas.jsp");
        request.setAttribute("lista", agendas);
        request.setAttribute("ms", request.getParameter("ms"));
        rd.forward(request, response);
    }
}
