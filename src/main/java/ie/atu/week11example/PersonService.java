package ie.atu.week11example;

import org.springframework.stereotype.Service;

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
            existingPerson.setCustomerId(updatedPerson.getCustomerId());
            existingPerson.setLocation(updatedPerson.getLocation());
            personRepository.save(existingPerson);
        }
        else {
            //Handle not found scenario
        }
        }
}


