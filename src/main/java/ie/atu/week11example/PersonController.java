package ie.atu.week11example;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/login")
@RestController
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<?> getPerson(@PathVariable String accountId) {
        if (accountId.length() > 5 || accountId.isBlank()) {
            return ResponseEntity.badRequest().body("AccountId is invalid");
        }

        Person person = personService.getPersonByAccountId(accountId);

        if (person == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(person);
    }

    @GetMapping("/getAllAccounts")
    public ResponseEntity<?> getAllPersons() {

        List<Person> persons = personService.getAllPersons();

        if (persons == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(persons);
    }


    @PostMapping("/create")
    public ResponseEntity<String>create(@Valid @RequestBody Person person) {
        personService.savePerson(person);
        return new ResponseEntity<>("Account created successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{Id}")
    public ResponseEntity<String>delete(@PathVariable Long Id) {
        personService.deletePerson(Id);
        return new ResponseEntity<>("Account deleted successfully", HttpStatus.OK);
    }

    @PutMapping("/{email}")
    public ResponseEntity<String>updatePerson(@PathVariable String email, @RequestBody Person updatedPerson) {
        personService.updatePerson(email, updatedPerson);
        return new ResponseEntity<>("Account changed successfully", HttpStatus.OK);
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyAccount(@RequestParam String email, @RequestParam String password){
        try {
        String message = personService.verify(email, password);
        return ResponseEntity.ok(message);
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("invalid email or password");
        }
    }
}
