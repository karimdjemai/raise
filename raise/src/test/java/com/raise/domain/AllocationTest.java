package com.raise.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.raise.web.rest.TestUtil;

public class AllocationTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Allocation.class);
        Allocation allocation1 = new Allocation();
        allocation1.setId(1L);
        Allocation allocation2 = new Allocation();
        allocation2.setId(allocation1.getId());
        assertThat(allocation1).isEqualTo(allocation2);
        allocation2.setId(2L);
        assertThat(allocation1).isNotEqualTo(allocation2);
        allocation1.setId(null);
        assertThat(allocation1).isNotEqualTo(allocation2);
    }
}
