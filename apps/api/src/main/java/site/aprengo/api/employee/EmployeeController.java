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
    public List<Employee> all()
    {
        return employeeRepo.findAll();
    }

    @PostMapping("/employees")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee newEmployee(@RequestBody Employee newEmployee)
    {
        return employeeRepo.save(newEmployee);
    }

    @GetMapping("/employees/{id}")
    public Employee one(@PathVariable Long id)
    {
        return employeeRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Failed to find an employee with an ID of " + id));
    }

    @PutMapping("/employees/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id)
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
    public void deleteEmployee(@PathVariable Long id)
    {
        if (!employeeRepo.existsById(id))
        {
            throw new ResourceNotFoundException("Failed to find an employee with an ID of " + id);
        }
        employeeRepo.deleteById(id);
    }
}
