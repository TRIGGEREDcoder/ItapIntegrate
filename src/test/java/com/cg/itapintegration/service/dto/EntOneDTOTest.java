package com.cg.itapintegration.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.cg.itapintegration.web.rest.TestUtil;

public class EntOneDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EntOneDTO.class);
        EntOneDTO entOneDTO1 = new EntOneDTO();
        entOneDTO1.setId(1L);
        EntOneDTO entOneDTO2 = new EntOneDTO();
        assertThat(entOneDTO1).isNotEqualTo(entOneDTO2);
        entOneDTO2.setId(entOneDTO1.getId());
        assertThat(entOneDTO1).isEqualTo(entOneDTO2);
        entOneDTO2.setId(2L);
        assertThat(entOneDTO1).isNotEqualTo(entOneDTO2);
        entOneDTO1.setId(null);
        assertThat(entOneDTO1).isNotEqualTo(entOneDTO2);
    }
}
