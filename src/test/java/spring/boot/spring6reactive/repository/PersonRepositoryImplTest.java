package spring.boot.spring6reactive.repository;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import spring.boot.spring6reactive.domain.Person;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonRepositoryImplTest {

    PersonRepository personRepository = new PersonRepositoryImpl();



    @Test
    void testGetByIdFound() {
        Mono<Person> personMono = personRepository.getById(3);

        assertTrue(personMono.hasElement().block());
    }
    @Test
    void testGetByIdFoundStepVerifier() {
        Mono<Person> personMono = personRepository.getById(3);
        StepVerifier.create(personMono).expectNextCount(1).verifyComplete();
        personMono.subscribe(person -> System.out.println(person.toString()));

    }
    @Test
    void testGetByIdNotFound() {
        Mono<Person> personMono = personRepository.getById(6);

        assertFalse(personMono.hasElement().block());
    }
    @Test
    void testGetByIdNotFoundStepVerifier() {
        Mono<Person> personMono = personRepository.getById(6);

        StepVerifier.create(personMono).expectNextCount(0).verifyComplete();
        personMono.subscribe(person -> System.out.println(person.toString()));
    }
    @Test
    void testMonoByIdBlock() {
        Mono<Person> personMono = personRepository.getById(1);

        Person person = personMono.block();

        System.out.println(
                person.toString()
        );
    }

    @Test
    void testGetByIdSubscriber() {
        Mono<Person> personMono = personRepository.getById(1);
        personMono.subscribe(person ->
                System.out.println(person.toString()));
    }

    @Test
    void TestMapOperation() {
        Mono<Person> personMono = personRepository.getById(1);
        personMono.map(Person::getFirstName).subscribe(System.out::println);
    }

    @Test
    void testFluxBlockFirst() {
        Flux<Person> personFlux = personRepository.findAll();

        Person person
                = personFlux.blockFirst();

        System.out.println(person.toString());
    }

    @Test
    void testFluxSubscriber() {
        Flux<Person> personFlux = personRepository.findAll();
        personFlux.subscribe(person -> System.out.println(person.toString()));
    }

    @Test
    void testFluxMapOperation() {
        Flux<Person> personFlux = personRepository.findAll();

        personFlux.map(Person::getFirstName).subscribe(System.out::println);
    }

    @Test
    void fluxToList() {
        Flux<Person> personFlux = personRepository.findAll();
        Mono<List<Person>> listMono = personFlux.collectList();
        listMono.subscribe(list -> {
            list.forEach(person -> {
                System.out.println(person.toString());
            });
        });

    }
    @Test
    void testFilterOnName() {
        personRepository.findAll()
                .filter(person -> person.getFirstName().equals("Muhammad "))
                .subscribe(person -> System.out.println(person.getLastName()));
    }

    @Test
    void testGetById() {
        Mono<Person> idMono = personRepository.findAll().filter(person -> person.getFirstName().equals("Muhammad "))
                .next();

        idMono.subscribe(person -> System.out.println(person.getLastName()));
    }

    @Test
    void testFindPersonByIdNotFound() {
        Flux<Person> personFlux = personRepository.findAll();
         final Integer id=9;
          Mono<Person> personMono =personFlux.filter(person -> {
              return person.getId() == id;
          }).single().doOnError(throwable ->
          {
              System.out.println("Error occurred in the mono");
              System.out.println(throwable.toString());
          });
          personMono.subscribe(person -> {
              System.out.println(person.toString());
          }, throwable -> {
              System.out.println("Error occurred in the mono");
              System.out.println(throwable.toString());
          });
    }
}


















