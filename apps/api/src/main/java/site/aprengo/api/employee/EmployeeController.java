package site.aprengo.api.employee;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController
{
    private final EmployeeRepo employeeRepo;

    EmployeeController(EmployeeRepo repository)
    {
        this.employeeRepo = repository;
    }

    @GetMapping("/employees")
    List<Employee> all()
    {
        return employeeRepo.findAll();
    }

    @PostMapping("/employees")
    Employee newEmployee(@RequestBody Employee newEmployee)
    {
        return employeeRepo.save(newEmployee);
    }

    @GetMapping("/employees/{id}")
    Employee one(@PathVariable Long id)
    {
        return employeeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException());
    }

    @PutMapping("/employees/{id}")
    Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id)
    {

        return employeeRepo.findById(id)
                .map(employee -> {
                    employee.setFirstName(newEmployee.getFirstName());
                    employee.setLastName(newEmployee.getLastName());
                    return employeeRepo.save(employee);
                })
                .orElseGet(() -> {
                    newEmployee.setId(id);
                    return employeeRepo.save(newEmployee);
                });
    }

    @DeleteMapping("/employees/{id}")
    void deleteEmployee(@PathVariable Long id)
    {
        employeeRepo.deleteById(id);
    }
}
