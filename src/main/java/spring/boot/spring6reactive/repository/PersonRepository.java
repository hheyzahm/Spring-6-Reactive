package spring.boot.spring6reactive.repository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import spring.boot.spring6reactive.domain.Person;

/**
 * @Created 31 03 2023 - 11:39 AM
 * @Author Hazeem Hassan
 */
public interface PersonRepository {
    Mono<Person> getById(Integer id);
    Flux<Person> findAll();
}
