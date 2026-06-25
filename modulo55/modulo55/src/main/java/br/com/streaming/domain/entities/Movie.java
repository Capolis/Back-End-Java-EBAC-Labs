package br.com.streaming.domain.entities;

import java.util.Set;

import br.com.streaming.domain.enums.PlanType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(name = "age_rating", nullable = false)
    private Integer ageRating;

    @Enumerated(EnumType.STRING)
    @org.hibernate.annotations.JdbcTypeCode(java.sql.Types.VARCHAR)
    @Column(name = "minimum_plan", nullable = false, length = 20)
    private PlanType minimumPlan;

    // Mapeamento direto para a tabela movie_genres
    @ElementCollection
    @CollectionTable(name = "movie_genres", joinColumns = @JoinColumn(name = "movie_id"))
    @Column(name = "genre", length = 50)
    private Set<String> genres;

    // Construtor

    public Movie(){
    }

    //Setter e Getters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getAgeRating() {
        return ageRating;
    }

    public void setAgeRating(Integer ageRating) {
        this.ageRating = ageRating;
    }

    public PlanType getMinimumPlan() {
        return minimumPlan;
    }

    public void setMinimumPlan(PlanType minimumPlan) {
        this.minimumPlan = minimumPlan;
    }

    public Set<String> getGenres() {
        return genres;
    }

    public void setGenres(Set<String> genres) {
        this.genres = genres;
    }


}