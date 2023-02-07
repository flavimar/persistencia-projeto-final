package br.ufg.persistencia.agendamento_vacinacao.controller;

import br.ufg.persistencia.agendamento_vacinacao.dao.Conexao;
import br.ufg.persistencia.agendamento_vacinacao.dao.DaoAgenda;
import br.ufg.persistencia.agendamento_vacinacao.dao.DaoVacina;
import br.ufg.persistencia.agendamento_vacinacao.model.Agenda;
import br.ufg.persistencia.agendamento_vacinacao.model.TipoSituacao;
import br.ufg.persistencia.agendamento_vacinacao.model.Vacina;
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
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

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
            agenda.setVacina(new Vacina(Integer.parseInt(request.getParameter("vacina"))));
            en = Conexao.getEntityManager();
            DaoAgenda daoAgenda = new DaoAgenda(en);
            daoAgenda.create(agenda);
            EntityManager entityManager = Conexao.getEntityManager();
            DaoVacina daoVacina = new DaoVacina(entityManager);
            Vacina vacina = daoVacina.findById(agenda.getVacina().getId());
            entityManager.close();
            if(agenda.getSituacao().equals(TipoSituacao.AGENDADO)) {
                int doses = vacina.getDoses();
                Calendar c = Calendar.getInstance();
                while(doses > 1) {
                    Agenda novaAgenda = new Agenda();
                    novaAgenda.setData(agenda.getData());
                    novaAgenda.setVacina(new Vacina(agenda.getVacina().getId()));
                    novaAgenda.setSituacao(agenda.getSituacao());
                    novaAgenda.setHora(agenda.getHora());
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

    private void getInsert(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        en = Conexao.getEntityManager();
        daoVacina = new DaoVacina(en);
        RequestDispatcher rd = request.getRequestDispatcher("/templates/agenda/cadastrar-agenda.jsp");
        request.setAttribute("vacinas",daoVacina.findAll());
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
        en = Conexao.getEntityManager();
        DaoAgenda daoAgenda = new DaoAgenda(en);
        String filtro = request.getParameter("filtro");
        TipoSituacao tipoSituacao = null;
        if(!StringUtil.isNullOrEmpty(filtro)) {
           tipoSituacao = TipoSituacao.valueOf(filtro);
        }
        java.util.List<Agenda> agendas = daoAgenda.findAll(tipoSituacao);
        RequestDispatcher rd = request.getRequestDispatcher("/templates/agenda/listar-agendas.jsp");
        request.setAttribute("lista", agendas);
        request.setAttribute("filtro",tipoSituacao);
        request.setAttribute("ms", request.getParameter("ms"));
        rd.forward(request, response);
    }
}
