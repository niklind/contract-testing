package com.example.provider;

import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.RestPactRunner;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit.target.TestTarget;
import au.com.dius.pact.provider.spring.target.MockMvcTarget;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;

@RunWith(RestPactRunner.class)
@Provider("provider")
@PactFolder("pacts")
@SuppressWarnings("squid:S2187")
public class UserControllerContractTest {

    @InjectMocks
    private UserController userController = new UserController();

    @TestTarget
    public final MockMvcTarget target = new MockMvcTarget();

    @Before
    public void setup() {
        target.setControllers(userController);
    }

    @State("No user with email 'user@test.com' exists")
    public void state() {
        // no action needed
    }

}
