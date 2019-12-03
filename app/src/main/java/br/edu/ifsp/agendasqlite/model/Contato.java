package br.edu.ifsp.agendasqlite.model;

import java.io.Serializable;

public class Contato implements Serializable {

    private int id;
    private String nome;
    private String fone;
    private String email;
    private String foneAlternativo;
    private boolean isFavorito;

    public Contato() {
    }

    public Contato(String nome, String fone, String foneAlternativo, String email) {
        this.nome = nome;
        this.fone = fone;
        this.email = email;
        this.isFavorito = false;
        this.foneAlternativo = foneAlternativo;
    }


    public boolean equals(Object obj)
    {
        Contato c2= (Contato) obj;
        if (this.id ==c2.getId())
            return true;
           else
            return false;

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isFavorito() {
        return isFavorito;
    }

    public void setFavorito(boolean favorito) {
        isFavorito = favorito;
    }

    public String getFoneAlternativo() {
        return foneAlternativo;
    }

    public void setFoneAlternativo(String foneAlternativo) {
        this.foneAlternativo = foneAlternativo;
    }
}
