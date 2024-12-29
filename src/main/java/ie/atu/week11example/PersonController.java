package ie.atu.week11example;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/login")
@RestController
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<?> getPerson(@PathVariable String customerId) {
        if (customerId.length() > 5 || customerId.isBlank()) {
            return ResponseEntity.badRequest().body("CustomerId is invalid");
        }

        Person person = personService.getPersonByCustomerId(customerId);

        if (person == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(person);
    }


    @PostMapping("/create")
    public ResponseEntity<String>create(@Valid @RequestBody Person person) {
        personService.savePerson(person);
        return new ResponseEntity<>("Account created successfully", HttpStatus.OK);
    }
}
