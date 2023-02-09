package br.ufg.persistencia.agendamento_vacinacao.controller;


import br.ufg.persistencia.agendamento_vacinacao.dao.*;
import br.ufg.persistencia.agendamento_vacinacao.model.*;
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


@WebServlet("/alergia/*")
public class ControleAlergia extends HttpServlet {
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
        Alergia alergia = new Alergia();
        alergia.setNome( request.getParameter("nome"));

        en = Conexao.getEntityManager();
        DaoAlergia daoAlergia = new DaoAlergia(en);
        daoAlergia.create(alergia);
        en.close();
    }

    @SneakyThrows
    protected void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Alergia alergia = new Alergia();
        alergia.setId(Long.parseLong(request.getParameter("id")));
        alergia.setNome( request.getParameter("nome"));
        en = Conexao.getEntityManager();
        DaoAlergia daoAlergia= new DaoAlergia(en);
        Alergia upAlergia = daoAlergia.findById(alergia.getId());
        if(upAlergia == null){
            response.sendRedirect("listar?ms='Alergia não encontrada'");
        }else {
            upAlergia.atualizarAlergia(alergia);
            daoAlergia.update(upAlergia);
            en.close();
        }
    }
    public void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long id = Long.parseLong(request.getParameter("id"));
        en = Conexao.getEntityManager();
        DaoAlergia daoAlergia = new DaoAlergia(en);
        Alergia alergia = daoAlergia.findById(id);
        if(alergia == null){
            response.sendRedirect("listar?ms='Alergia não encontrada'");
        }
        else {
            daoAlergia.delete(alergia);
            en.close();
            response.sendRedirect("listar");
        }
    }

    private void getInsert(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher rd = request.getRequestDispatcher("/templates/alergia/cadastrar-alergia.jsp");
        rd.forward(request, response);
    }

    private void getUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        long id =  Long.parseLong(request.getParameter("id"));
        en = Conexao.getEntityManager();
        DaoAlergia daoAlergia = new DaoAlergia(en);
        Alergia alergia = daoAlergia.findById(id);
        RequestDispatcher rd = request.getRequestDispatcher("/templates/alergia/editar-alergia.jsp");
        request.setAttribute("alergia",alergia);
        en.close();
        rd.forward(request, response);
    }
    protected void getList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        en = Conexao.getEntityManager();
        DaoAlergia daoUsuario = new DaoAlergia(en);
        java.util.List<Alergia> usuarios = daoUsuario.findAll();
        RequestDispatcher rd = request.getRequestDispatcher("/templates/alergia/listar-alergias.jsp");
        request.setAttribute("lista", usuarios);
        request.setAttribute("ms", request.getParameter("ms"));
        rd.forward(request, response);
    }
}
