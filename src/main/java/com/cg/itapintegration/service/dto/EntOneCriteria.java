package com.cg.itapintegration.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link com.cg.itapintegration.domain.EntOne} entity. This class is used
 * in {@link com.cg.itapintegration.web.rest.EntOneResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /ent-ones?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class EntOneCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter fieldOne;

    public EntOneCriteria() {
    }

    public EntOneCriteria(EntOneCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.fieldOne = other.fieldOne == null ? null : other.fieldOne.copy();
    }

    @Override
    public EntOneCriteria copy() {
        return new EntOneCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getFieldOne() {
        return fieldOne;
    }

    public void setFieldOne(StringFilter fieldOne) {
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
        final EntOneCriteria that = (EntOneCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(fieldOne, that.fieldOne);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        fieldOne
        );
    }

    @Override
    public String toString() {
        return "EntOneCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (fieldOne != null ? "fieldOne=" + fieldOne + ", " : "") +
            "}";
    }

}
