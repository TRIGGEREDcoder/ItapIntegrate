package com.cg.itapintegration.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.cg.itapintegration.web.rest.TestUtil;

public class EntOneTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EntOne.class);
        EntOne entOne1 = new EntOne();
        entOne1.setId(1L);
        EntOne entOne2 = new EntOne();
        entOne2.setId(entOne1.getId());
        assertThat(entOne1).isEqualTo(entOne2);
        entOne2.setId(2L);
        assertThat(entOne1).isNotEqualTo(entOne2);
        entOne1.setId(null);
        assertThat(entOne1).isNotEqualTo(entOne2);
    }
}
