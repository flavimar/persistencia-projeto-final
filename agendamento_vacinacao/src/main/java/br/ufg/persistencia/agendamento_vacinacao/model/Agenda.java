package br.ufg.persistencia.agendamento_vacinacao.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Date;
@Data
@Entity
@Table(name = "agendas")
@NoArgsConstructor
public class Agenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 10)
    private long id;

    @Column(nullable = true)
    private Date data;
    @Column(length = 5,nullable = true)
    private LocalTime hora;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoSituacao situacao;
    @Column(name = "data_situacao")
    private  Date dataSituacao;
    @Column(length = 200)
    private String observacao;

    @ManyToOne
    @JoinColumn(name = "vacina_id")
    private Vacina vacina;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public void atualizarAgenda(Agenda agenda) {
        this.id = agenda.getId() != 0? agenda.getId() : this.id;
        this.data = agenda.getData() != null? agenda.getData() : this.data;
        this.hora = agenda.getHora() != null? agenda.getHora() : this.hora;
        this.situacao = agenda.getSituacao() != null? agenda.getSituacao() : this.situacao;
        this.dataSituacao = agenda.getDataSituacao() != null? agenda.getDataSituacao() : this.dataSituacao;
        this.observacao = agenda.getObservacao() != null ? agenda.getObservacao() : this.observacao;
        this.vacina = agenda.getVacina() != null ? agenda.getVacina() : this.vacina;
    }
    public void addAgenda(Agenda agenda) {
        this.data = agenda.getData() != null? agenda.getData() : null;
        this.hora = agenda.getHora() != null? agenda.getHora() : null;
        this.situacao = agenda.getSituacao() != null? agenda.getSituacao() : null;
        this.dataSituacao = agenda.getDataSituacao() != null? agenda.getDataSituacao() :null;
        this.observacao = agenda.getObservacao() != null ? agenda.getObservacao() : null;
        this.vacina = agenda.getVacina() != null ? agenda.getVacina() : null;
    }
}
