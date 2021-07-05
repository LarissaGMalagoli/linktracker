package br.com.meli.linktracker.repository;

import br.com.meli.linktracker.entity.Link;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LinkRepository  extends JpaRepository<Link, Long> {
    //List<Link> findAllByCategorias_Nome(String nome);
    //Produto findByNome(String nome);
}
