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

import static java.util.Collections.EMPTY_LIST;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.testifyproject.annotation.Application;
import org.testifyproject.annotation.Fake;
import org.testifyproject.annotation.Module;
import org.testifyproject.annotation.Sut;
import org.testifyproject.annotation.VirtualResource;
import org.testifyproject.junit4.SystemTest;

import com.google.protobuf.Empty;

import example.grpc.GreetingsGrpc;
import example.grpc.common.GreetingResponse;
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
public class ListGreetingsST {

    @Sut
    GreetingsGrpc.GreetingsBlockingStub sut;

    @Fake
    EntityManager entityManager;

    @Test
    public void callToListGreetingsShouldReturnEmptyListOfGreetings() {
        //Arrange
        Query query = mock(Query.class);
        List<GreetingEntity> entities = EMPTY_LIST;

        given(entityManager.createQuery(anyString())).willReturn(query);
        given(query.getResultList()).willReturn(entities);

        //Act
        Iterator<GreetingResponse> result = sut.list(Empty.getDefaultInstance());

        //Assert
        assertThat(result).isEmpty();
    }

}
