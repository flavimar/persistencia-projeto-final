package br.ufg.persistencia.agendamento_vacinacao.controller;

import br.ufg.persistencia.agendamento_vacinacao.dao.Conexao;
import br.ufg.persistencia.agendamento_vacinacao.dao.DaoAgenda;
import br.ufg.persistencia.agendamento_vacinacao.dao.DaoUsuario;
import br.ufg.persistencia.agendamento_vacinacao.dao.DaoVacina;
import br.ufg.persistencia.agendamento_vacinacao.model.Agenda;
import br.ufg.persistencia.agendamento_vacinacao.model.TipoSituacao;
import br.ufg.persistencia.agendamento_vacinacao.model.Usuario;
import br.ufg.persistencia.agendamento_vacinacao.model.Vacina;
import br.ufg.persistencia.agendamento_vacinacao.service.ServicoAgenda;
import br.ufg.persistencia.agendamento_vacinacao.service.ServicoVacina;
import br.ufg.persistencia.agendamento_vacinacao.util.StringUtil;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@WebServlet("/agenda/*")
public class ControleAgenda extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private EntityManager en;
    private DaoVacina daoVacina;
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
            String hora = request.getParameter("hora");
            if(!StringUtil.isNullOrEmpty(data) && !StringUtil.isNullOrEmpty(hora)){
                agenda.setData(sdf.parse(data));
                agenda.setHora(LocalTime.parse(hora));
            }
            String sit = request.getParameter("situacao");
            if(TipoSituacao.valueOf(sit) != TipoSituacao.AGENDADO){
                agenda.setDataSituacao(new Date());
            }
            agenda.setSituacao(TipoSituacao.valueOf(sit));
            if(!StringUtil.isNullOrEmpty(request.getParameter("obs"))) {
                agenda.setObservacao(request.getParameter("obs"));
            }
            agenda.setVacina(new Vacina(Long.parseLong(request.getParameter("vacina"))));
            Usuario usuario = new Usuario();
            usuario.setNome(request.getParameter("usuario"));
            agenda.setUsuario(usuario);

        ServicoAgenda servicoAgenda = new ServicoAgenda();
        String ms = servicoAgenda.insert(agenda);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    @SneakyThrows
    protected void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Agenda agenda = new Agenda();
        agenda.setId(Long.parseLong(request.getParameter("id")));
        String data = request.getParameter("data");
        String hora = request.getParameter("hora");
        if(!StringUtil.isNullOrEmpty(data) && !StringUtil.isNullOrEmpty(hora)){
            agenda.setData(sdf.parse(data));
            agenda.setHora(LocalTime.parse(hora));
        }
        String sit = request.getParameter("situacao");
        if(TipoSituacao.valueOf(sit) != TipoSituacao.AGENDADO){
            agenda.setDataSituacao(new Date());
        }
        agenda.setSituacao(TipoSituacao.valueOf(sit));
        if(!StringUtil.isNullOrEmpty(request.getParameter("obs"))) {
            agenda.setObservacao(request.getParameter("obs"));
        }
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

    @Transactional
    private void getInsert(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        en = Conexao.getEntityManager();
        daoVacina = new DaoVacina(en);
        DaoUsuario daoUsuario = new DaoUsuario(en);
        RequestDispatcher rd = request.getRequestDispatcher("/templates/agenda/cadastrar-agenda.jsp");
        request.setAttribute("vacinas",daoVacina.findAll());
        List<Usuario> usuarios = daoUsuario.findAll();
        usuarios.forEach(u -> {
            u.setAlergias(null);
        });
        request.setAttribute("usuarios",usuarios);
        en.close();
        rd.forward(request, response);
    }
    private void getUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        long id =  Long.parseLong(request.getParameter("id"));
        en = Conexao.getEntityManager();
        DaoAgenda daoAgenda = new DaoAgenda(en);
        daoVacina = new DaoVacina(en);
        Agenda agenda = daoAgenda.findById(id);
        request.setAttribute("vacinas",daoVacina.findAll());
        RequestDispatcher rd = request.getRequestDispatcher("/templates/agenda/editar-agenda.jsp");
        request.setAttribute("agenda",agenda);
        en.close();
        rd.forward(request, response);

    }
    protected void getList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String ms = request.getParameter("ms");
        en = Conexao.getEntityManager();
        DaoAgenda daoAgenda = new DaoAgenda(en);
        DaoUsuario daoUsuario = new DaoUsuario(en);
        String filtro = request.getParameter("filtro");
        TipoSituacao tipoSituacao = null;
        if(!StringUtil.isNullOrEmpty(filtro)) {
           tipoSituacao = TipoSituacao.valueOf(filtro);
        }
        boolean ord = Boolean.parseBoolean(request.getParameter("ord"));

        String usuario = request.getParameter("usuario");
        long usuarioId = 0;
        Usuario usuario1 = null;
        if(usuario != null) {
            usuario1 = daoUsuario.findByNome(usuario);
            if(usuario1 != null) {
                usuarioId = usuario1.getId();
            }else {
                ms = "Usuario não existe";
            }
        }
        java.util.List<Agenda> agendas = daoAgenda.findAll(tipoSituacao,ord,usuarioId);
        RequestDispatcher rd = request.getRequestDispatcher("/templates/agenda/listar-agendas.jsp");
        request.setAttribute("lista", agendas);
        request.setAttribute("usuarios",daoUsuario.findAll());
        request.setAttribute("filtro",tipoSituacao);
        request.setAttribute("usuario",usuario1);
        request.setAttribute("ord",ord);
        request.setAttribute("ms",ms);
        en.close();
        rd.forward(request, response);
    }

}
