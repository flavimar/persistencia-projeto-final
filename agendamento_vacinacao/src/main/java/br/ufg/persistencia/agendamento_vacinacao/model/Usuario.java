package br.ufg.persistencia.agendamento_vacinacao.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "Usuarios")
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 10)
    private long id;
    @Column(name = "nome",length = 60,nullable = false,unique = true)
    private String nome;
    @Column(name = "data_nasc",nullable = false)
    private String dataNasc;
    @Column(name = "sexo",length = 10,nullable = false)
    private String sexo;
    @Column(name = "logradouro", length = 60,nullable = false)
    private String logradouro;
    @Column(name = "numero")
    private int numero;
    @Column(name = "setor", length = 40)
    private String setor;
    @Column(name = "cidade", length = 40)
    private String cidade;
    @Column(name = "uf", length = 25)
    private String uf;

    public Usuario(long id){
        this.id = id;
    }

    public void atualizarUsuario(Usuario usuario) {
        this.nome = usuario.getNome() != null? usuario.getNome() :this.nome;
        this.dataNasc = usuario.getDataNasc() != null? usuario.getDataNasc() :this.dataNasc;
        this.sexo = usuario.getSexo() != null? usuario.getSexo() :this.sexo;
        this.logradouro = usuario.getLogradouro() != null? usuario.getLogradouro() :this.logradouro;
        this.numero = usuario.getNumero() != 0 ? usuario.getNumero() : this.numero;
        this.setor = usuario.getSetor() != null? usuario.getSetor() :this.setor;
        this.cidade = usuario.getCidade() != null? usuario.getCidade() :this.cidade;
        this.uf = usuario.getUf() != null? usuario.getUf() : this.uf;
    }
}
