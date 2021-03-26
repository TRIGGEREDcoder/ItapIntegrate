package com.cg.itapintegration.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A EntOne.
 */
@Entity
@Table(name = "entone")
public class EntOne implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "field_one")
    private String fieldOne;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFieldOne() {
        return fieldOne;
    }

    public void setFieldOne(String fieldOne) {
        this.fieldOne = fieldOne;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EntOne)) {
            return false;
        }
        return id != null && id.equals(((EntOne) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "EntOne{" +
            "id=" + getId() +
            ", fieldOne='" + getFieldOne() + "'" +
            "}";
    }
}
