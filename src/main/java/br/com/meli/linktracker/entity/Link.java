package br.com.meli.linktracker.entity;

import javax.persistence.*;

@Entity
public class Link {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String url;
    private int countRedirect;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String senha;

    public Link(){

    }

    public Link(String url, String senha) {
        this.url = url;
        this.countRedirect = 0;
        this.status = Status.VALIDO;
        this.senha = senha;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Long getId() {
        return id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getCountRedirect() {
        return countRedirect;
    }

    public void setCountRedirect(int countRedirect) {
        this.countRedirect = countRedirect;
    }
}
