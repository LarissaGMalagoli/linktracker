package br.com.meli.linktracker;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;


import br.com.meli.linktracker.dto.LinkForm;
import br.com.meli.linktracker.entity.Link;
import br.com.meli.linktracker.repository.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponentsBuilder;



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
    public RedirectView localRedirect(@PathVariable int id) {
        Optional<Link> optionalLink = linkRepository.findById(Long.valueOf(id));

        if(optionalLink.isPresent()){
            System.out.println("teste "+optionalLink.get().getUrl());
            optionalLink.get().setCountRedirect(optionalLink.get().getCountRedirect()+1);
            //redirectView.setUrl(optionalLink.get().getUrl());
            RedirectView redirectView = new RedirectView(optionalLink.get().getUrl());
            System.out.println("teste2 "+redirectView.getUrl());
            return redirectView;
        }
        //return ResponseEntity.notFound().build();
        return null;
    }

    @RequestMapping("/teste/{id}")
    public ResponseEntity<Object> redirectToExternalUrl(@PathVariable int id) throws URISyntaxException {
        //URI yahoo = new URI("www.yahoo.com");
        Optional<Link> optionalLink = linkRepository.findById(Long.valueOf(id));
        if(optionalLink.isPresent()) {
            HttpHeaders httpHeaders = new HttpHeaders();
            URI yahoo = new URI(optionalLink.get().getUrl());
            httpHeaders.setLocation(yahoo);
            return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
        }
        return ResponseEntity.notFound().build();
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