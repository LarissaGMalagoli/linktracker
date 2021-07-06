package br.com.meli.linktracker;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;


import br.com.meli.linktracker.dto.LinkForm;
import br.com.meli.linktracker.entity.Link;
import br.com.meli.linktracker.entity.Status;
import br.com.meli.linktracker.exception.InvalidLinkException;
import br.com.meli.linktracker.exception.InvalidSenhaException;
import br.com.meli.linktracker.repository.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;


@RestController
@RequestMapping("/link")
public class Controller {

    @Autowired
    LinkRepository linkRepository;

    @PostMapping
    public ResponseEntity<Link> addProduto(@RequestBody LinkForm formLink, UriComponentsBuilder builder) {
        Link link = formLink.converte(); //linkRepository.findAll().size()));

        URI uri = builder.path("/link/{id}").buildAndExpand(link.getId()).toUri();

        linkRepository.save(link);

        return ResponseEntity.created(uri).body(link);
    }

    @GetMapping
    public List<LinkDTO> link() {
        return LinkDTO.converte(linkRepository.findAll());
    }
/*
    @GetMapping("/{id}")
    public ResponseEntity<Link> getLink(@PathVariable Long id){
        Optional<Link> optionalProduto = linkRepository.findById(id);
        if(optionalProduto.isPresent()){
            return ResponseEntity.ok((optionalProduto.get()));
        }
        return ResponseEntity.notFound().build();
    }
*/
    @RequestMapping("/{id}")
    @Transactional
    public RedirectView localRedirect(@PathVariable int id, @RequestParam String senha) {
        Optional<Link> optionalLink = linkRepository.findById(Long.valueOf(id));

        if(optionalLink.isPresent()){
            Link link = optionalLink.get();
            if(link.getStatus()== Status.INVALIDO){
                throw new InvalidLinkException();
            }

            if(!link.getSenha().equals(senha)){
                throw new InvalidSenhaException();
            }
            link.setCountRedirect(link.getCountRedirect()+1);

            //redirectView.setUrl(optionalLink.get().getUrl());
            RedirectView redirectView = new RedirectView(link.getUrl(), false);

            return redirectView;
        }
        //return ResponseEntity.notFound().build();
        return null;
    }

    @GetMapping("metrics/{id}")
    public String getTotal(@PathVariable int id) {
        Optional<Link> optionalLink = linkRepository.findById(Long.valueOf(id));

        if(optionalLink.isPresent()) {

            return "Total de redirecionamentos: " + optionalLink.get().getCountRedirect();

        }

        return "Link não cadastrado";
    }


    @RequestMapping("invalidate/{id}")
    @Transactional
    public LinkDTO invalido(@PathVariable int id) {
        Optional<Link> optionalLink = linkRepository.findById(Long.valueOf(id));

        if(optionalLink.isPresent()){
            Link link = optionalLink.get();

            link.setStatus(Status.INVALIDO);

            return new LinkDTO(link);
        }
        //return ResponseEntity.notFound().build();
        return null;
    }

    /*@PostMapping
    public ResponseEntity<Link> add(@RequestBody LinkDTO dto, UriComponentsBuilder uriBuilder){
        Link link = LinkDTO.converte(dto, (long)linkRepository.getTotal());
        return linkRepository.add(link, uriBuilder);
    }

    @RequestMapping("/{id}")
    public RedirectView localRedirect(@PathVariable int id) {
        Link l = linkRepository.findById(id);
        RedirectView redirectView = new RedirectView();
        l.setCountRedirect(l.getCountRedirect()+1);
        redirectView.setUrl(l.getUrl());
        return redirectView;
    }

    @GetMapping("metrics/{id}")
    public String links(@PathVariable int id) {
        Link l = linkService.findById(id);
        return "Total de redirecionamentos: " + l.getCountRedirect();
    }*/

   /* ResponseEntity<Produto> alteraProduto(@PathVariable Long id, @RequestBody @Valid ProdutoForm produtoForm) {

        Optional<Produto> optionalProduto = produtoRepository.findById(id);
        if (optionalProduto.isPresent()){

            Produto produto = produtoForm.atualizar(id, produtoRepository, categoriaRepository);
            return ResponseEntity.ok(produto);
        }
        return ResponseEntity.notFound().build();
    }*/

}