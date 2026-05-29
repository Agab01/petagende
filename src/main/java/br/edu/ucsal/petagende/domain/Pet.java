package br.edu.ucsal.petagende.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_pet")
public class Pet {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nome;
    private String especie;
    private String alergias; 
    
    private String nomeTutor; 
    private String telefoneTutor;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEspecie() { return especie; }
    public void setEspecie(String especie) { this.especie = especie; }

    public String getAlergias() { return alergias; }
    public void setAlergias(String alergias) { this.alergias = alergias; }

    public String getNomeTutor() { return nomeTutor; }
    public void setNomeTutor(String nomeTutor) { this.nomeTutor = nomeTutor; }

    public String getTelefoneTutor() { return telefoneTutor; }
    public void setTelefoneTutor(String telefoneTutor) { this.telefoneTutor = telefoneTutor; }
}