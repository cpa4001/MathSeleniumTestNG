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
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;

import java.util.UUID;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.testifyproject.annotation.Application;
import org.testifyproject.annotation.Fake;
import org.testifyproject.annotation.Module;
import org.testifyproject.annotation.Sut;
import org.testifyproject.annotation.VirtualResource;
import org.testifyproject.junit4.SystemTest;

import example.grpc.GreetingsGrpc;
import example.grpc.common.GreetingId;
import examples.GreetingsServer;
import examples.service.entity.GreetingEntity;
import fixture.TestModule;

/**
 *
 * @author saden
 */
@Module(value = TestModule.class, test = true)
@VirtualResource(value = "postgres", version = "9.4")
@Application(value = GreetingsServer.class, start = "start", stop = "stop")
@RunWith(SystemTest.class)
public class RemoveGreetingsST {

    @Sut
    GreetingsGrpc.GreetingsBlockingStub sut;

    @Fake
    EntityManager entityManager;

    @Test
    public void givenExistingGreetingRemoveGreetingShouldNotRemoveGreeting() {
        //Arrange
        String id = "0d216415-1b8e-4ab9-8531-fcbd25d5966f";
        GreetingId request = GreetingId.newBuilder().setId(id).build();
        UUID uuid = UUID.fromString(id);
        GreetingEntity entity = new GreetingEntity(uuid, "hello");

        given(entityManager.getReference(GreetingEntity.class, uuid)).willReturn(entity);
        willDoNothing().given(entityManager).remove(entity);

        //Act
        sut.remove(request);

        //Assert: the entity was not removed
        GreetingEntity result = entityManager.getReference(GreetingEntity.class, entity.getId());
        assertThat(result).isNotNull();
    }

}
