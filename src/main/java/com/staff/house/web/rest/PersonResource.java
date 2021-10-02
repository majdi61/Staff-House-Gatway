package com.staff.house.web.rest;

import com.staff.house.domain.Person;
import com.staff.house.service.PersonService;
import java.util.Collection;
import java.util.Optional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/person")
@CrossOrigin(origins = { "*" }, methods = { RequestMethod.GET, RequestMethod.POST })
public class PersonResource {

    private final PersonService personService;

    public PersonResource(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public Person create(@RequestBody Person person) {
        return personService.insertPersonData(person);
    }

    @GetMapping
    public Collection<Person> read() {
        return personService.getAllPersonInformation();
    }

    @GetMapping(path = "{id}")
    public Optional<Person> readQueryUsingId(@PathVariable("id") String id) {
        return personService.getPersonInformationById(id);
    }

    @PutMapping(path = "/update/{id}")
    public void update(@PathVariable String id, @RequestBody Person person) {
        personService.updatePersonUsingId(id, person);
    }

    @DeleteMapping(path = "/delete/{id}")
    public void delete(@PathVariable("id") String id) {
        personService.deletePersonUsingId(id);
    }
}
