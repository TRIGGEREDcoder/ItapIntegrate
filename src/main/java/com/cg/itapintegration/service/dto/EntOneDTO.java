package com.cg.itapintegration.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.cg.itapintegration.domain.EntOne} entity.
 */
public class EntOneDTO implements Serializable {
    
    private Long id;

    private String fieldOne;

    
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EntOneDTO entOneDTO = (EntOneDTO) o;
        if (entOneDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), entOneDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EntOneDTO{" +
            "id=" + getId() +
            ", fieldOne='" + getFieldOne() + "'" +
            "}";
    }
}
