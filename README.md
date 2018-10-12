# contract-testing
Small example showing how to use [Pact](https://docs.pact.io/) in Spring Boot.

## Consumer
The consumer uses the DSL from [pact-jvm-consumer-java8](https://github.com/DiUS/pact-jvm/tree/master/pact-jvm-consumer-java8) and the [JUnit5 extension](https://github.com/DiUS/pact-jvm/tree/master/pact-jvm-consumer-junit5). It publishes the results to a broker using `mvn pact:publish` from [pact-jvm-provider-maven](https://github.com/DiUS/pact-jvm/tree/master/pact-jvm-provider-maven)

## Provider
The provider uses [pact-jvm-provider-spring](https://github.com/DiUS/pact-jvm/tree/master/pact-jvm-provider-spring) to download and verify pacts from the broker. 

## Broker
Requires a running Pact Broker at `http://localhost:80/`, e.g. using the official [pact_broker-docker](https://github.com/DiUS/pact_broker-docker/blob/master/docker-compose.yml) 
