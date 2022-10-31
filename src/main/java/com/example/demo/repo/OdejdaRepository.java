package com.example.demo.repo;

import com.example.demo.models.Odejda;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OdejdaRepository extends CrudRepository<Odejda, Long> {

    List<Odejda> findByTitleContains(String title);

    List<Odejda> findByTitle(String title);

}
