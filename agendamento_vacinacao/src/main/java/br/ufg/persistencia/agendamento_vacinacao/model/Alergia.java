package br.ufg.persistencia.agendamento_vacinacao.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "alergias")
@NoArgsConstructor
public class Alergia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 10)
    private long id;
    @Column(name = "nome",length = 40,nullable = false,unique = true)
    private String nome;

    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "alergias")
    Set<Usuario> usuarios;
    public void atualizarAlergia(Alergia alergia) {
        this.id = alergia.getId() != 0? alergia.getId() : this.id;
        this.nome = alergia.getNome() != null? alergia.getNome() :this.nome;
    }
}
