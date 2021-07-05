package br.com.meli.linktracker.dto;

import br.com.meli.linktracker.entity.Link;

public class LinkForm {
    private String url;

    public LinkForm() {

    }

    public LinkForm(String url) {
        this.url = url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Link converte() {
        return new Link(url);
    }

}
