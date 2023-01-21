package br.ufg.persistencia.agendamento_vacinacao.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    private String hora;
    @Column(nullable = false)
    private TipoSituacao situacao;
    @Column(name = "data_situacao")
    private  Date dataSituacao;
    @Column(length = 200)
    private String observacao;
}
