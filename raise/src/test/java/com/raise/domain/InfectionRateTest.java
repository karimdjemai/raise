package com.raise.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.raise.web.rest.TestUtil;

public class InfectionRateTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InfectionRate.class);
        InfectionRate infectionRate1 = new InfectionRate();
        infectionRate1.setId(1L);
        InfectionRate infectionRate2 = new InfectionRate();
        infectionRate2.setId(infectionRate1.getId());
        assertThat(infectionRate1).isEqualTo(infectionRate2);
        infectionRate2.setId(2L);
        assertThat(infectionRate1).isNotEqualTo(infectionRate2);
        infectionRate1.setId(null);
        assertThat(infectionRate1).isNotEqualTo(infectionRate2);
    }
}
