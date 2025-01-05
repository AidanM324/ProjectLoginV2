package ie.atu.week11example;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long>{

    Person findByAccountId(String customerId);
    Person findByName(String name);
    Optional<Person> findByEmail(String email);
}
