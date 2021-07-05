package br.com.meli.linktracker.service;

import java.util.List;

import br.com.meli.linktracker.entity.Link;
import br.com.meli.linktracker.repository.LinkRepository2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;


@Service
public class LinkService {

    /*LinkRepository2 linkRepository2;

    public LinkService(LinkRepository2 linkRepository2) {
        this.linkRepository2 = linkRepository2;
    }

    public List<Link> findAll() {
        return linkRepository2.findAll();
    }

    public Link findById(int id){
        return linkRepository2.findById(id);
    }


    public ResponseEntity<Link> add(Link cliente, UriComponentsBuilder uriBuilder) {
        //Cliente cliente = dto.convert();
        linkRepository2.add(cliente);
        return ResponseEntity.ok().body(cliente);
    }

    public int getTotal() {
        return linkRepository2.findAll().size();
    }*/

}
