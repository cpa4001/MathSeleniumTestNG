/*
 * Copyright 2016-2017 Testify Project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package examples.greeting;


import static org.assertj.core.api.Assertions.assertThat;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.testifyproject.annotation.Application;
import org.testifyproject.annotation.Module;
import org.testifyproject.annotation.Real;
import org.testifyproject.annotation.Sut;
import org.testifyproject.annotation.VirtualResource;
import org.testifyproject.junit4.SystemTest;

import example.grpc.GreetingsGrpc;
import example.grpc.common.GreetingId;
import example.grpc.common.GreetingRequest;
import examples.GreetingsServer;
import fixture.TestModule;

/**
 *
 * @author saden
 */
@Module(value = TestModule.class, test = true)
@VirtualResource(value = "postgres", version = "9.4")
@Application(value = GreetingsServer.class, start = "start", stop = "stop")
@RunWith(SystemTest.class)
public class CreateGreetingST {

    @Sut
    GreetingsGrpc.GreetingsBlockingStub sut;

    @Real
    EntityManager entityManager;

    @Test
    public void givenNameSayHelloShouldReturnHelloReply() {
        //Arrange
        String phrase = "Hello";
        GreetingRequest request = GreetingRequest.newBuilder()
                .setPhrase(phrase)
                .build();

        //Act
        GreetingId response = sut.create(request);

        //Assert
        assertThat(response).isNotNull();
        assertThat(response.getId()).isNotEmpty();

    }

}
