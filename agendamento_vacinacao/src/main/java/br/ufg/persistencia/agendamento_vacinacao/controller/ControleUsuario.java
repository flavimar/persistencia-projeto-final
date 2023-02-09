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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@WebServlet("/usuario/*")
public class ControleUsuario extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private EntityManager en;
    private DaoAlergia daoAlergia;
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
            case "/vincular-alergias":
                vicularAlergias(request, response);
                break;
        }
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
            case "/vincular-alergias":
                getAlergias(request,response);
                break;
            default:
                getList(request,response);
                break;
        }
    }

    protected void insert(HttpServletRequest request, HttpServletResponse response){
        try {
            Usuario usuario = new Usuario();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            usuario.setNome( request.getParameter("nome"));
            String dataNasc = request.getParameter("data_nasc");
            if(!StringUtil.isNullOrEmpty(dataNasc)){
                usuario.setDataNasc(sdf.parse(dataNasc));
            }
            usuario.setSexo( request.getParameter("sexo"));
            usuario.setLogradouro( request.getParameter("logradouro"));
            usuario.setNumero( Integer.valueOf(request.getParameter("numero")));
            usuario.setSetor( request.getParameter("setor"));
            usuario.setCidade( request.getParameter("cidade"));
            usuario.setUf( request.getParameter("uf"));

            en = Conexao.getEntityManager();
            DaoUsuario daoUsuario = new DaoUsuario(en);
            daoUsuario.create(usuario);
            en.close();
            request.setAttribute("id",usuario.getId());
            response.sendRedirect("vincular-alergias?id="+usuario.getId());
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @SneakyThrows
    protected void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Usuario usuario = new Usuario();
        usuario.setId(Long.parseLong(request.getParameter("id")));
        usuario.setNome( request.getParameter("nome"));
        String dataNasc = request.getParameter("data_nasc");
        if(!StringUtil.isNullOrEmpty(dataNasc)){
            usuario.setDataNasc(sdf.parse(dataNasc));
        }
        usuario.setSexo( request.getParameter("sexo"));
        usuario.setLogradouro( request.getParameter("logradouro"));
        usuario.setNumero( Integer.valueOf(request.getParameter("numero")));
        usuario.setSetor( request.getParameter("setor"));
        usuario.setCidade( request.getParameter("cidade"));
        usuario.setUf( request.getParameter("uf"));

        en = Conexao.getEntityManager();
        DaoUsuario daoUsuario = new DaoUsuario(en);
        Usuario upUsuario = daoUsuario.findById(usuario.getId());
        if(upUsuario == null){
            response.sendRedirect("listar?ms='Usuário não encontrado'");
        }else {
            upUsuario.atualizarUsuario(usuario);
            daoUsuario.update(upUsuario);
            en.close();
            response.sendRedirect("listar");
        }

    }
    public void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long id = Long.parseLong(request.getParameter("id"));
        en = Conexao.getEntityManager();
        DaoUsuario daoUsuario = new DaoUsuario(en);
        Usuario usuario = daoUsuario.findById(id);
        if(usuario == null){
            response.sendRedirect("listar?ms='Usuário não encontrado'");
        }
        daoUsuario.delete(usuario);
        en.close();
        response.sendRedirect("'listar");
    }
    private void getAlergias(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        en = Conexao.getEntityManager();
        daoAlergia = new DaoAlergia(en);
        RequestDispatcher rd = request.getRequestDispatcher("/templates/alergia/vincular-alergias.jsp");
        request.setAttribute("alergias",daoAlergia.findAll());
        request.setAttribute("usuarioId", request.getParameter("id"));
        en.close();
        rd.forward(request, response);
    }
    private void vicularAlergias(HttpServletRequest request, HttpServletResponse response) throws IOException {
        en = Conexao.getEntityManager();
        DaoUsuario daoUsuario = new DaoUsuario(en);
        daoAlergia = new DaoAlergia(en);
        long id = Long.parseLong(request.getParameter("id"));
        Usuario usuario = daoUsuario.findById(id);
        String nome = request.getParameter("alergia");
        Alergia alergia = daoAlergia.findByNome(nome);
        if(usuario != null && alergia != null){
            if(usuario.getAlergias() == null){
                usuario.setAlergias(new ArrayList<>());
            }
            usuario.getAlergias().add(alergia);
            daoUsuario.update(usuario);
            en.close();
            response.sendRedirect("vincular-alergias?id="+usuario.getId());
        }else{

            response.sendRedirect("vincular-alergias");
        }

    }
    private void getInsert(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        en = Conexao.getEntityManager();
        daoAlergia = new DaoAlergia(en);
        RequestDispatcher rd = request.getRequestDispatcher("/templates/usuario/cadastrar-usuario.jsp");
        request.setAttribute("alergias",daoAlergia.findAll());
        en.close();
        rd.forward(request, response);
    }

    private void getUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        long id =  Long.parseLong(request.getParameter("id"));
        en = Conexao.getEntityManager();
        DaoUsuario daoUsuario = new DaoUsuario(en);
       //daoAlergia = new DaoAlergia(en);
        Usuario usuario = daoUsuario.findById(id);
        //request.setAttribute("alergias",daoAlergia.findAll());
        RequestDispatcher rd = request.getRequestDispatcher("/templates/usuario/editar-usuario.jsp");
        request.setAttribute("usuario",usuario);
        en.close();
        rd.forward(request, response);
    }
    protected void getList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        en = Conexao.getEntityManager();
        DaoUsuario daoUsuario = new DaoUsuario(en);
        java.util.List<Usuario> usuarios = daoUsuario.findAll();
        en.close();
        RequestDispatcher rd = request.getRequestDispatcher("/templates/usuario/listar-usuarios.jsp");
        request.setAttribute("lista", usuarios);
        request.setAttribute("ms", request.getParameter("ms"));
        rd.forward(request, response);
    }
}
