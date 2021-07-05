package br.com.meli.linktracker.repository;


import br.com.meli.linktracker.entity.Link;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class LinkRepository2 {
    private static final File FILE = new File("link.json");

    ObjectMapper mapper;

    public LinkRepository2(ObjectMapper mapper) {
        this.mapper = mapper;
    }


    public List<Link> findAll() {
        List<Link> links = new ArrayList<>();
        try {
            FileInputStream is = new FileInputStream(FILE);
            TypeReference<List<Link>> typeReference = new TypeReference<List<Link>>() {};
            links = mapper.readValue(is, typeReference);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return links;
    }

    public Link findById(int id){
        return findAll().get(id);
    }

    public int getList(){
        return findAll().size();
    }

    public void add(Link link) {
        try {
            List<Link> links = findAll();
            links.add(link);
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(FILE)));
            mapper.writeValue(out, links);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
