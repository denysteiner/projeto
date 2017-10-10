package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@Table(name = "bairros")
@SequenceGenerator(name = "seq_bairros", sequenceName = "seq_bairros", initialValue = 1, allocationSize = 1)
public class Bairro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_bairros")
    private Long id;
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nome")
    private String nome;

    private Bairro() {
    }

    public Bairro(final String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Bairro{" + "id=" + id + ", nome=" + nome + '}';
    }

}
