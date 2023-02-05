package br.ufg.persistencia.agendamento_vacinacao.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "vacinas")
public class Vacina{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 60,nullable = false)
    private String titulo;
    @Column(length = 200)
    private String descricao;
    private int doses;
    private Periodicidade periodicidade;
    private int intervalo;

    public Vacina(long id) {
        this.id = id;
    }
    public void atualizarVacina(Vacina vacina) {
        this.id = vacina.getId() != 0? vacina.getId() : this.id;
        this.titulo = vacina.getTitulo() != null? vacina.getTitulo() : this.titulo;
        this.descricao = vacina.getDescricao() != null? vacina.getDescricao() : this.descricao;
        this.doses = vacina.getDoses() != 0? vacina.getDoses() : this.doses;
        this.periodicidade = vacina.getPeriodicidade() != null? vacina.getPeriodicidade() : this.periodicidade;
        this.intervalo = vacina.getIntervalo() != 0? vacina.getIntervalo() : this.intervalo;
    }
}
