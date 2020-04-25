package com.raise.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Allocation.
 */
@Entity
@Table(name = "allocation")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Allocation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "amount", nullable = false)
    private Double amount;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "cost", nullable = false)
    private Double cost;

    @ManyToOne
    @JsonIgnoreProperties("allocations")
    private RunInstance runInstance;

    @ManyToOne
    @JsonIgnoreProperties("allocations")
    private Resource resource;

    @ManyToOne
    @JsonIgnoreProperties("allocations")
    private Region region;

    @ManyToOne
    @JsonIgnoreProperties("allocations")
    private RiskGroup riskGroup;

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

    public Allocation amount(Double amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getCost() {
        return cost;
    }

    public Allocation cost(Double cost) {
        this.cost = cost;
        return this;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public RunInstance getRunInstance() {
        return runInstance;
    }

    public Allocation runInstance(RunInstance runInstance) {
        this.runInstance = runInstance;
        return this;
    }

    public void setRunInstance(RunInstance runInstance) {
        this.runInstance = runInstance;
    }

    public Resource getResource() {
        return resource;
    }

    public Allocation resource(Resource resource) {
        this.resource = resource;
        return this;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public Region getRegion() {
        return region;
    }

    public Allocation region(Region region) {
        this.region = region;
        return this;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public RiskGroup getRiskGroup() {
        return riskGroup;
    }

    public Allocation riskGroup(RiskGroup riskGroup) {
        this.riskGroup = riskGroup;
        return this;
    }

    public void setRiskGroup(RiskGroup riskGroup) {
        this.riskGroup = riskGroup;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Allocation)) {
            return false;
        }
        return id != null && id.equals(((Allocation) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Allocation{" +
            "id=" + getId() +
            ", amount=" + getAmount() +
            ", cost=" + getCost() +
            "}";
    }
}
