package com.cg.itapintegration.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class EntOneMapperTest {

    private EntOneMapper entOneMapper;

    @BeforeEach
    public void setUp() {
        entOneMapper = new EntOneMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(entOneMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(entOneMapper.fromId(null)).isNull();
    }
}
