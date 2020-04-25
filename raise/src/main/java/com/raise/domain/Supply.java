package com.raise.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Supply.
 */
@Entity
@Table(name = "supply")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Supply implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "geometry")
    private String geometry;

    @ManyToOne
    @JsonIgnoreProperties("supplies")
    private RunInstance runInstance;

    @ManyToOne
    @JsonIgnoreProperties("supplies")
    private Resource resource;

    @ManyToOne
    @JsonIgnoreProperties("supplies")
    private Region region;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public Supply amount(Double amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getGeometry() {
        return geometry;
    }

    public Supply geometry(String geometry) {
        this.geometry = geometry;
        return this;
    }

    public void setGeometry(String geometry) {
        this.geometry = geometry;
    }

    public RunInstance getRunInstance() {
        return runInstance;
    }

    public Supply runInstance(RunInstance runInstance) {
        this.runInstance = runInstance;
        return this;
    }

    public void setRunInstance(RunInstance runInstance) {
        this.runInstance = runInstance;
    }

    public Resource getResource() {
        return resource;
    }

    public Supply resource(Resource resource) {
        this.resource = resource;
        return this;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public Region getRegion() {
        return region;
    }

    public Supply region(Region region) {
        this.region = region;
        return this;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Supply)) {
            return false;
        }
        return id != null && id.equals(((Supply) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Supply{" +
            "id=" + getId() +
            ", amount=" + getAmount() +
            ", geometry='" + getGeometry() + "'" +
            "}";
    }
}
