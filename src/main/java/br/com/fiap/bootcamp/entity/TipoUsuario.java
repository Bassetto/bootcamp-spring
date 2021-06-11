package br.com.fiap.bootcamp.entity;

public enum TipoUsuario {
    CANDIDATO(0),
    COACH(1),
    PROFESSOR(2),
    VENDEDOR(3),
    ADMIN(4);

    private int value;

    TipoUsuario(int value) {
        this.value = value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
