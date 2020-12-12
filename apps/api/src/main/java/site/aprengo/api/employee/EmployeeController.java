package site.aprengo.api.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import site.aprengo.api.exceptionhandling.ResourceNotFoundException;

import java.util.List;

@RestController
public class EmployeeController
{
    @Autowired
    private EmployeeRepo employeeRepo;

    @GetMapping("/employees")
    public List<Employee> findAll()
    {
        return employeeRepo.findAll();
    }

    @GetMapping("/employees/{id}")
    public Employee findOne(@PathVariable Long id)
    {
        return employeeRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Failed to find an employee with an ID of " + id));
    }

    @PostMapping("/employees")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee create(@RequestBody Employee newEmployee)
    {
        return employeeRepo.save(newEmployee);
    }

    @PutMapping("/employees/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Employee update(@RequestBody Employee newEmployee, @PathVariable Long id)
    {
        return employeeRepo.findById(id)
                .map(employee -> {
                    employee.setFirstName(newEmployee.getFirstName());
                    employee.setLastName(newEmployee.getLastName());
                    employee.setPhoneNumber(newEmployee.getPhoneNumber());
                    employee.setMinutesWorked(newEmployee.getMinutesWorked());
                    return employeeRepo.save(employee);
                })
                .orElseGet(() -> {
                    newEmployee.setId(id);
                    return employeeRepo.save(newEmployee);
                });
    }

    @DeleteMapping("/employees/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(@PathVariable Long id)
    {
        if (!employeeRepo.existsById(id))
        {
            throw new ResourceNotFoundException("Failed to find an employee with an ID of " + id);
        }
        employeeRepo.deleteById(id);
    }
}
