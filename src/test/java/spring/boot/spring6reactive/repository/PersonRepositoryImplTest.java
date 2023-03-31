package spring.boot.spring6reactive.repository;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import spring.boot.spring6reactive.domain.Person;

import static org.junit.jupiter.api.Assertions.*;

class PersonRepositoryImplTest {

    PersonRepository personRepository= new PersonRepositoryImpl();
    @Test
    void testMonoByIdBlock(){
        Mono<Person> personMono  = personRepository.getById(1);

        Person person=personMono.block();

        System.out.println(
                person.toString()
        );
    }

    @Test
    void testGetByIdSubscriber() {
        Mono<Person> personMono =personRepository.getById(1);
        personMono.subscribe(person ->
                System.out.println(person.toString()));
    }

    @Test
    void TestMapOperation() {
        Mono<Person> personMono =personRepository.getById(1);
         personMono.map(person -> {
             return person.getFirstName();
         }).subscribe(firstName->{
             System.out.println(firstName.toString());
         });
    }
}