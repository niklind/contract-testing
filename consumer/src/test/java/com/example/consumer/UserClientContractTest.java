package com.example.consumer;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.model.RequestResponsePact;
import org.apache.http.entity.ContentType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

import static io.pactfoundation.consumer.dsl.LambdaDsl.newJsonBody;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(PactConsumerTestExt.class)
public class UserClientContractTest {

    private static final String EXPECTED_BODY =
        "{\n" +
            "\"name\": \"User\",\n" +
            "\"email\": \"user@test.com\"\n" +
        "}";

    @Pact(consumer = "consumer", provider = "provider")
    @SuppressWarnings("unused")
    public RequestResponsePact pact(PactDslWithProvider builder) {
        return builder
                .given("No user with email 'user@test.com' exists")
                .uponReceiving("Create new user")
                    .method("POST")
                    .path("/")
                    .body(EXPECTED_BODY, ContentType.APPLICATION_JSON)
                .willRespondWith()
                    .status(200)
                    .body(newJsonBody((root) -> {
                        root.stringType("userId");
                        root.stringValue("name", "User");
                        root.stringValue("email", "user@test.com");
                    }).build())
                .toPact();
    }

    @Test
    void test(MockServer mockServer) {
        String url = mockServer.getUrl();
        UserClient userClient = createUserClient();
        User userToCreate = new User("User", "user@test.com");

        User createdUser = userClient.post(url, userToCreate);

        assertNotNull(createdUser);
    }

    UserClient createUserClient() {
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder().messageConverters(new GsonHttpMessageConverter());
        return new UserClient(restTemplateBuilder);
    }

}
