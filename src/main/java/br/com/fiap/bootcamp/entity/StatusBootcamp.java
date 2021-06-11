package br.com.fiap.bootcamp.entity;

public enum StatusBootcamp {
    PLANEJAMENTO(0),
    ANDAMENTO(1),
    VALIDACAO(2),
    RECRUTAMENTO(3);

    private int value;

    StatusBootcamp(int value) {
        this.value = value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
