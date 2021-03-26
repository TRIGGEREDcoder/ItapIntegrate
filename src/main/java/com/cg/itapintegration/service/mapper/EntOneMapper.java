package com.cg.itapintegration.service.mapper;


import com.cg.itapintegration.domain.*;
import com.cg.itapintegration.service.dto.EntOneDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EntOne} and its DTO {@link EntOneDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EntOneMapper extends EntityMapper<EntOneDTO, EntOne> {



    default EntOne fromId(Long id) {
        if (id == null) {
            return null;
        }
        EntOne entOne = new EntOne();
        entOne.setId(id);
        return entOne;
    }
}
