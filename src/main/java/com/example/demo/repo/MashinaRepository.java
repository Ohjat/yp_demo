package com.example.demo.repo;

import com.example.demo.models.Mashina;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MashinaRepository extends CrudRepository<Mashina, Long> {

    List<Mashina> findByNameContains(String name);

    List<Mashina> findByName(String name);

}
