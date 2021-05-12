package com.example.cadastrodeclientes;

public class Cliente {

    public int id;
    public String nome;
    public String telefone;
    public String celular;

    public Cliente() {

    }

    public Cliente(String nome, String telefone, String celular) {
        this.nome = nome;
        this.telefone = telefone;
        this.celular = celular;
    }

    public Cliente(int id, String nome, String telefone, String celular) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.celular = celular;
    }

    @Override
    public String toString() {
        return id + " - " + nome + " - " + telefone + " - " + celular;
    }

}
