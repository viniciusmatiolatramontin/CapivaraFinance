package com.ezbudget.EzBudgetAPI;

import static org.assertj.core.api.Assertions.assertThat;

import com.ezbudget.EzBudgetAPI.controller.AuthController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EzBudgetApiApplicationTests {

    @Autowired
    private AuthController controller;

    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
    }

}
