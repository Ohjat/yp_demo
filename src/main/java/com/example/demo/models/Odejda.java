package com.example.demo.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Odejda {
    public Odejda(String title, Mashina mashina, Date release_date, boolean for_sale, double price) {
        this.title = title;
        this.mashina = mashina;
        this.release_date = release_date;
        this.for_sale = for_sale;
        this.price = price;
    }

    public Odejda() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @NotBlank
    @Size(min = 1, max = 70)
    public String title;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    public Mashina mashina;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @PastOrPresent
    public Date release_date;

    @NotNull
    public boolean for_sale;

    @NotNull
    @Positive
    public double price;

    @NotNull
    @ManyToMany
    @JoinTable (name="genres_books",
            joinColumns=@JoinColumn (name="book_id"),
            inverseJoinColumns=@JoinColumn(name="genre_id"))
    public Set<Kuzov> kuzovs;

    public Set<Kuzov> getKuzovs() {
        return kuzovs;
    }

    public void setKuzovs(Set<Kuzov> kuzovs) {
        this.kuzovs = kuzovs;
    }

    public Mashina getMashina() {
        return mashina;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMashina(Mashina mashina) {
        this.mashina = mashina;
    }

    public Date getRelease_date() {
        return release_date;
    }

    public void setRelease_date(Date release_date) {
        this.release_date = release_date;
    }

    public boolean isFor_sale() {
        return for_sale;
    }

    public void setFor_sale(boolean for_sale) {
        this.for_sale = for_sale;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
