package br.com.meli.linktracker.dto;

import br.com.meli.linktracker.entity.Link;

public class LinkForm {
    private String url;
    private String senha;

    public LinkForm() {

    }

    public LinkForm(String url, String senha) {
        this.url = url;
        this.senha = senha;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Link converte() {
        return new Link(url, senha);
    }

}
