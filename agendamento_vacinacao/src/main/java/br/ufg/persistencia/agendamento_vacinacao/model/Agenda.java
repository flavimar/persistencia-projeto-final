package br.ufg.persistencia.agendamento_vacinacao.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;
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
    @Column(nullable = false)
    private Date data;
    @Column(length = 5,nullable = false)
    private LocalTime hora;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoSituacao situacao;
    @Column(name = "data_situacao")
    private  Date dataSituacao;
    @Column(length = 200)
    private String observacao;

    public void atualizarAgenda(Agenda agenda) {
        this.id = agenda.getId() != 0? agenda.getId() : this.id;
        this.data = agenda.getData() != null? agenda.getData() : this.data;
        this.hora = agenda.getHora() != null? agenda.getHora() : this.hora;
        this.situacao = agenda.getSituacao() != null? agenda.getSituacao() : this.situacao;
        this.dataSituacao = agenda.getDataSituacao() != null? agenda.getDataSituacao() : this.dataSituacao;
        this.observacao = agenda.getObservacao() != null ? agenda.getObservacao() : this.observacao;
    }
}
