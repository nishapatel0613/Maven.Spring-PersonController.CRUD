package io.zipcoder.crudapp.controller;

import io.zipcoder.crudapp.entities.Person;
import io.zipcoder.crudapp.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {
    @Autowired
    private PersonRepository repository;

    @RequestMapping(method = RequestMethod.POST, value = "/people")
    public ResponseEntity createPerson(@RequestBody Person p){
        repository.save(p);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.GET, value = "/people/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable Long id){
        return new ResponseEntity(repository.findById(id),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/people")
    public ResponseEntity<Iterable<Person>> getPersonId(){
        return new ResponseEntity<>(repository.findAll(),HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.PUT, value = "/people/{id}")
    public ResponseEntity<Person> updatePerson(@RequestBody Person p, @PathVariable Long id){
        if(p.getId() != null)
            return new ResponseEntity<>(repository.save(p),HttpStatus.OK);
        else
            return createPerson(p);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/people/{id}")
    public ResponseEntity delete( @PathVariable Long id){
        repository.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);

    }




}

