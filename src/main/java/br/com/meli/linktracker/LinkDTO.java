package br.com.meli.linktracker;

import br.com.meli.linktracker.entity.Link;

import java.util.List;
import java.util.stream.Collectors;

public class LinkDTO {
    private String url;
    private int countRedirect;

    public LinkDTO() {
    }

    public LinkDTO(Link link) {
        this.url = link.getUrl();
        this.countRedirect = link.getCountRedirect();
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
