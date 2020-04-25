package com.raise.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.raise.web.rest.TestUtil;

public class RiskGroupTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RiskGroup.class);
        RiskGroup riskGroup1 = new RiskGroup();
        riskGroup1.setId(1L);
        RiskGroup riskGroup2 = new RiskGroup();
        riskGroup2.setId(riskGroup1.getId());
        assertThat(riskGroup1).isEqualTo(riskGroup2);
        riskGroup2.setId(2L);
        assertThat(riskGroup1).isNotEqualTo(riskGroup2);
        riskGroup1.setId(null);
        assertThat(riskGroup1).isNotEqualTo(riskGroup2);
    }
}
