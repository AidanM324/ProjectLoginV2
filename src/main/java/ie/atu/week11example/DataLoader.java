package ie.atu.week11example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final PersonRepository personRepository;

    @Autowired
    public DataLoader(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    @Override
    public void run(String... args) throws Exception{
        Person testData = new Person(1L, "Aidan", "aidan@atu.ie", "am", "12345", "ireland");
        Person testData2 = new Person(2L, "Paul", "Paul@atu.ie", "pl", "56789", "ireland");
        Person testData3 = new Person(3L, "Jamie", "jamie@atu.ie", "jm", "11223", "ireland");
        personRepository.save(testData);
        personRepository.save(testData2);
        personRepository.save(testData3);
    }
}
