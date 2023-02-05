package br.ufg.persistencia.agendamento_vacinacao.model;

public enum Periodicidade {
    DIA(1),
    SEMANA(2),
    MES(3),
    ANO(4);

    private int valorPeriodicidade;
    Periodicidade(int valorPeriodicidade){
        this.valorPeriodicidade = valorPeriodicidade;
    }

    public int getValorPeriodicidade() {
        return valorPeriodicidade;
    }
}
