package com.pfcti.springdevapp.api;

import com.pfcti.springdevapp.reactive.model.Employee;
import com.pfcti.springdevapp.reactive.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequestMapping("/v1/api/employee")
public class EmployeeApi {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Employee e) {
        employeeService.create(e); }

    @GetMapping("/{id}")
    public ResponseEntity<Mono<Employee>> findById(@PathVariable("id") Integer id) {
        Mono<Employee> e = employeeService.findById(id);
        return new ResponseEntity<>(e, HttpStatus.OK); }

    @GetMapping("/name/{name}")
    public Flux<Employee> findByName(@PathVariable("name") String name) {
        return employeeService.findByName(name); }

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE) public Flux<Employee> findAll() {
        Flux<Employee> emps = employeeService.findAll();
        return emps;}
       // return emps.delayElements(Duration.ofSeconds(2)); }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Employee> update(@RequestBody Employee e) {
        return employeeService.update(e); }
    @DeleteMapping("/delete/{id}") @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Integer id) {
        employeeService.delete(id).subscribe(); }

}