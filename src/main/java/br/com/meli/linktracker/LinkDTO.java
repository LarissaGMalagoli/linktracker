package br.com.meli.linktracker;

import br.com.meli.linktracker.entity.Link;
import br.com.meli.linktracker.entity.Status;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;
import java.util.stream.Collectors;

public class LinkDTO {
    private String url;
    private int countRedirect;
    private Status status;

    public LinkDTO() {
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setCountRedirect(int countRedirect) {
        this.countRedirect = countRedirect;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LinkDTO(Link link) {
        this.url = link.getUrl();
        this.countRedirect = link.getCountRedirect();
        this.status = link.getStatus();
    }

    public String getUrl() {
        return url;
    }

    public int getCountRedirect() {
        return countRedirect;
    }

    public static List<LinkDTO> converte(List<Link> links) {
        return links.stream().map(a -> new LinkDTO(a)).collect(Collectors.toList());
    }
}
