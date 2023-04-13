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
package examples.greeting.entity;

import examples.service.entity.GreetingEntity;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.testifyproject.annotation.Fixture;
import org.testifyproject.annotation.LocalResource;
import org.testifyproject.annotation.Module;
import org.testifyproject.annotation.Real;
import org.testifyproject.annotation.Sut;
import org.testifyproject.junit4.IntegrationTest;
import org.testifyproject.resource.hsql.InMemoryHSQLResource;

import examples.GreetingsModule;

/**
 * An integration test that demonstrates the ability to:
 * <ul>
 * <li>substitute the production database with an in-memory HSQL database using
 * {@link LocalResource @LocalResource} annotation</li>
 * <li>specify the the class under test using {@link Sut @Sut} annotation</li>
 * <li>inject the class under test's real collaborating EntityManager instance using
 * {@link Real @Real} annotation</li>
 * <li>inject a managed EntityManager instance using {@link Inject @Inject} and
 * {@link Fixture @Fixture} annotations for verification purpose</li>
 * </ul>
 *
 * @author saden
 */
@Ignore
@Module(GreetingsModule.class)
@LocalResource(InMemoryHSQLResource.class)
@RunWith(IntegrationTest.class)
public class GreetingEntityIT {

    @Real
    EntityManagerFactory entityManagerFactory;

    EntityManager entityManager;

    @Before
    public void init() {
        entityManager = entityManagerFactory.createEntityManager();
    }

    @After
    public void destroy() {
        entityManager.close();
    }

    @Test
    public void givenNewGreetingEntityManagerShouldPersistCreateGreeting() {
        //Arrange
        String phrase = "hello";
        GreetingEntity entity = new GreetingEntity(phrase);

        //Act
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();

        //Assert
        UUID id = entity.getId();
        GreetingEntity result = entityManager.find(GreetingEntity.class, id);
        assertThat(result).isEqualTo(entity);
        assertThat(result.hashCode()).isEqualTo(entity.hashCode());
    }

    @Test
    public void givenExistingGreetingEntityManagerShouldUpdateGreeting() {
        //Arrange
        GreetingEntity createdEntity = new GreetingEntity("hello");
        entityManager.getTransaction().begin();
        entityManager.persist(createdEntity);
        entityManager.getTransaction().commit();
        entityManager.detach(createdEntity);

        //Act
        UUID id = createdEntity.getId();
        String phrase = "caio";
        GreetingEntity entity = entityManager.getReference(GreetingEntity.class, id);
        entity.setPhrase(phrase);
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();

        //Assert
        assertThat(entity).isNotEqualTo(createdEntity);
        assertThat(entity.getId()).isEqualTo(createdEntity.getId());
        assertThat(entity.getPhrase()).isNotEqualTo(createdEntity.getPhrase());
        assertThat(entity.hashCode()).isNotEqualTo(createdEntity.hashCode());
    }

    @Test
    public void givenSameEntityEqualsShouldReturnTrue() {
        //Arrange
        GreetingEntity entity = new GreetingEntity("hello");
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
        UUID id = entity.getId();

        //Act
        GreetingEntity result = entityManager.find(GreetingEntity.class, id);

        //Assert
        assertThat(result).isEqualTo(entity);
    }

    @Test
    public void givenDifferentEntityEqualsShouldReturnFalse() {
        //Arrange
        GreetingEntity helloEntity = new GreetingEntity("hello");
        GreetingEntity ciaoEntity = new GreetingEntity("ciao");
        entityManager.getTransaction().begin();
        entityManager.persist(helloEntity);
        entityManager.persist(ciaoEntity);
        entityManager.getTransaction().commit();

        //Act & Assert
        assertThat(helloEntity).isNotEqualTo(ciaoEntity);
    }

    @Test
    public void givenNullEntityEqualsShouldReturnFalse() {
        //Arrange
        GreetingEntity entity = new GreetingEntity("hello");
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();

        //Act & Assert
        assertThat(entity).isNotEqualTo(null);
    }

    @Test
    public void givenDifferentyTypeEntityEqualsShouldReturnFalse() {
        //Arrange
        GreetingEntity entity = new GreetingEntity("hello");
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();

        //Act & Assert
        assertThat(entity).isNotEqualTo(new Object());
    }

}
