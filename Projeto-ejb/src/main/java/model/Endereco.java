package model;

import java.io.Serializable;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@Table(name = "enderecos")
@SequenceGenerator(name = "seq_enderecos", sequenceName = "seq_enderecos", initialValue = 1, allocationSize = 1)
public class Endereco implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_enderecos")
    private Long id;
    @Size(min = 8, max = 8)
    @Column(name = "cep")
    private String cep;
    @NotNull
    @Size(max = 100)
    @Column(name = "rua")
    private String rua;
    @ManyToOne(cascade = {PERSIST, MERGE})
    @JoinColumn(name = "id_bairro")
    private Bairro bairro;
    @ManyToOne(cascade = {PERSIST, MERGE})
    @JoinColumn(name = "id_cidade")
    private Cidade cidade;

    private Endereco() {
    }

    public Endereco(final String cep, final String rua, final Bairro bairro, final Cidade cidade) {
        this.cep = cep;
        this.rua = rua;
        this.bairro = bairro;
        this.cidade = cidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(final String cep) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(final String rua) {
        this.rua = rua;
    }

    public Bairro getBairro() {
        return bairro;
    }

    public void setBairro(final Bairro bairro) {
        this.bairro = bairro;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(final Cidade cidade) {
        this.cidade = cidade;
    }

    @Override
    public String toString() {
        return "Endereco{" + "id=" + id + ", cep=" + cep + ", rua=" + rua + ", bairro=" + bairro + ", cidade=" + cidade + '}';
    }

}
