package spring.boot.spring6reactive.repository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import spring.boot.spring6reactive.domain.Person;

/**
 * @Created 31 03 2023 - 11:41 AM
 * @Author Hazeem Hassan
 */
public class PersonRepositoryImpl implements PersonRepository {

    Person sirSyedAhmadKhan = Person.builder().id(1).firstName("Sir Syed ").lastName("Ahmad Khan").build();
    Person allamaIqbal = Person.builder().id(2).firstName("Muhammad ").lastName("Allama Iqbal").build();
    Person aliJinnah = Person.builder().id(3).firstName("Muhammad ").lastName("Ali Jinnah").build();
    Person fatimaJinnah = Person.builder().id(4).firstName("Fatima ").lastName(" Jinnah").build();
    @Override
    public Mono<Person> getById(Integer id) {
        return Mono.just(aliJinnah);
    }

    @Override
    public Flux<Person> findAll() {
        return Flux.just(sirSyedAhmadKhan,allamaIqbal,aliJinnah,fatimaJinnah);
    }
}
