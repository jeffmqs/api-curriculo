package com.exemplo.curriculoapi.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Curriculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String telefone;
    private String email;

    @OneToMany(mappedBy = "curriculo", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<FormacaoAcademica> formacaoAcademica;

    @OneToMany(mappedBy = "curriculo", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ExperienciaProfissional> experienciaProfissional;

    @OneToMany(mappedBy = "curriculo", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Idioma> idiomas;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<FormacaoAcademica> getFormacaoAcademica() {
        return formacaoAcademica;
    }

    public void setFormacaoAcademica(List<FormacaoAcademica> formacaoAcademica) {
        this.formacaoAcademica = formacaoAcademica;
    }

    public List<ExperienciaProfissional> getExperienciaProfissional() {
        return experienciaProfissional;
    }

    public void setExperienciaProfissional(List<ExperienciaProfissional> experienciaProfissional) {
        this.experienciaProfissional = experienciaProfissional;
    }

    public List<Idioma> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<Idioma> idiomas) {
        this.idiomas = idiomas;
    }
}
