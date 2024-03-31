package com.capivarafinance.CapivaraFinance;

import static org.assertj.core.api.Assertions.assertThat;

import com.capivarafinance.CapivaraFinance.controller.AuthController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CapivaraFinanceApplicationTests {

    @Autowired
    private AuthController controller;

    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
    }

}
