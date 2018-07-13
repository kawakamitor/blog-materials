package com.example.provider;

import com.example.provider.api.user.UserRestController;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProviderApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ContractTestBase {

    @Autowired
    UserRestController userRestController;

    @Before
    public void setup() {
        RestAssuredMockMvc.standaloneSetup(userRestController);
    }

    @Test
    public void contextLoads() {
    }
}
