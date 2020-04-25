package com.raise.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Odmatrix.
 */
@Entity
@Table(name = "odmatrix")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Odmatrix implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "cost", nullable = false)
    private Double cost;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("odmatrices")
    private Region fromRegion;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("odmatrices")
    private Region toRegion;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getCost() {
        return cost;
    }

    public Odmatrix cost(Double cost) {
        this.cost = cost;
        return this;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Region getFromRegion() {
        return fromRegion;
    }

    public Odmatrix fromRegion(Region region) {
        this.fromRegion = region;
        return this;
    }

    public void setFromRegion(Region region) {
        this.fromRegion = region;
    }

    public Region getToRegion() {
        return toRegion;
    }

    public Odmatrix toRegion(Region region) {
        this.toRegion = region;
        return this;
    }

    public void setToRegion(Region region) {
        this.toRegion = region;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Odmatrix)) {
            return false;
        }
        return id != null && id.equals(((Odmatrix) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Odmatrix{" +
            "id=" + getId() +
            ", cost=" + getCost() +
            "}";
    }
}
