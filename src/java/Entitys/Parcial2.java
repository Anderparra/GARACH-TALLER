/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entitys;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author HERMANOS
 */
@Entity
@Table(name = "parcial2")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Parcial2.findAll", query = "SELECT p FROM Parcial2 p")
    , @NamedQuery(name = "Parcial2.findById", query = "SELECT p FROM Parcial2 p WHERE p.id = :id")
    , @NamedQuery(name = "Parcial2.findByNumero", query = "SELECT p FROM Parcial2 p WHERE p.numero = :numero")})
public class Parcial2 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "numero")
    private int numero;

    public Parcial2() {
    }

    public Parcial2(Integer id) {
        this.id = id;
    }

    public Parcial2(Integer id, int numero) {
        this.id = id;
        this.numero = numero;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Parcial2)) {
            return false;
        }
        Parcial2 other = (Parcial2) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entitys.Parcial2[ id=" + id + " ]";
    }
    
}
