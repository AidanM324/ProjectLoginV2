package ie.atu.week11example;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long>{

    Person findByCustomerId(String customerId);
    Person findByUsername(String username);
    Optional<Person> findByEmail(String email);
}
