package com.raise.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.raise.web.rest.TestUtil;

public class RunInstanceTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RunInstance.class);
        RunInstance runInstance1 = new RunInstance();
        runInstance1.setId(1L);
        RunInstance runInstance2 = new RunInstance();
        runInstance2.setId(runInstance1.getId());
        assertThat(runInstance1).isEqualTo(runInstance2);
        runInstance2.setId(2L);
        assertThat(runInstance1).isNotEqualTo(runInstance2);
        runInstance1.setId(null);
        assertThat(runInstance1).isNotEqualTo(runInstance2);
    }
}
