package com.raise.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.raise.web.rest.TestUtil;

public class OdmatrixTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Odmatrix.class);
        Odmatrix odmatrix1 = new Odmatrix();
        odmatrix1.setId(1L);
        Odmatrix odmatrix2 = new Odmatrix();
        odmatrix2.setId(odmatrix1.getId());
        assertThat(odmatrix1).isEqualTo(odmatrix2);
        odmatrix2.setId(2L);
        assertThat(odmatrix1).isNotEqualTo(odmatrix2);
        odmatrix1.setId(null);
        assertThat(odmatrix1).isNotEqualTo(odmatrix2);
    }
}
