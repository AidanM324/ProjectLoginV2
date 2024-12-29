package ie.atu.week11example;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    // Placeholder method to save a person (add to Db in the next two weeks)
    public void savePerson(Person person) {
        personRepository.save(person);
        System.out.println("Person saved: " + person);
    }

    // Placeholder method to retrieve a person by employeeId
    public Person getPersonByCustomerId(String customerId) {
        // fetch data from a database in future lab
        // For simplicity, we return a dummy person here
        return personRepository.findByCustomerId(customerId);
    }

    public void deletePerson(Long Id){
        personRepository.deleteById(Id);
    }

    public void updatePerson(String email, Person updatedPerson){
        Optional<Person> existingPersonOptional = personRepository.findByEmail(email);

        if(existingPersonOptional.isPresent()){
            Person existingPerson = existingPersonOptional.get();

            //update fields with the new data
            existingPerson.setName(updatedPerson.getName());
            existingPerson.setAge(updatedPerson.getAge());
            existingPerson.setEmail(updatedPerson.getEmail());
            existingPerson.setUsername(updatedPerson.getUsername());
            existingPerson.setPassword(updatedPerson.getPassword());
            existingPerson.setCustomerId(updatedPerson.getCustomerId());
            existingPerson.setLocation(updatedPerson.getLocation());
            personRepository.save(existingPerson);
        }
        else {
            //Handle not found scenario
        }
        }

    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public String verify(String username, String password){
        Person person = personRepository.findByUsername(username);

        if(person != null && person.getPassword().equals(password)){
            return "Login Successful";
            //should bring you to next url microservice insert link
        }
        else{
            throw new RuntimeException("Invalid username or password");
        }
    }
}


