package com.congtam.customer.service;

import com.congtam.customer.pojos.Employee;
import com.congtam.customer.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    private EmployeeRepository repo;
    public void save(Employee employee) {
        repo.save(employee);
    }

    public List<Employee> listAll() {
        return (List<Employee>) repo.findAll();
    }

    public Employee get(Long id) {
        return repo.findById(id).get();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public Employee login(String email, String password) {
        return repo.login(email, password);
    }

    public void register(String email,String pass,String name) {
        repo.register(email,pass,name);
    }

    public void editPass(String pass,long id) {
        repo.editPass(pass,id);
    }
}
