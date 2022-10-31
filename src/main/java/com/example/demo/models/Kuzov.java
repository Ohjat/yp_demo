package com.example.demo.models;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
public class Kuzov {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="author_id")
    public Mashina mashina;

    @NotBlank
    @Size(min = 1, max = 70)
    public String name;

    @ManyToMany
    @JoinTable (name="genres_books",
            joinColumns=@JoinColumn (name="genre_id"),
            inverseJoinColumns=@JoinColumn(name="book_id"))
    private Set<Odejda> odejdas;

    public Mashina getMashina() {
        return mashina;
    }

    public void setMashina(Mashina mashina) {
        this.mashina = mashina;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
