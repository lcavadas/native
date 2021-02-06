package payroll

import org.springframework.web.bind.annotation.*

@RestController
class EmployeeController {

  private final EmployeeRepository repository

  EmployeeController(EmployeeRepository repository) {
    this.repository = repository
  }

  @GetMapping("/employees")
  List<Employee> all() {
    return repository.findAll()
  }

  @PostMapping("/employees")
  Employee newEmployee(@RequestBody Employee newEmployee) {
    return repository.save(newEmployee)
  }

  @GetMapping("/employees/{id}")
  Employee one(@PathVariable UUID id) {

    return repository.findById(id)
      .orElseThrow({ -> new EmployeeNotFoundException(id) })
  }

  @PutMapping("/employees/{id}")
  Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable UUID id) {
    Employee employee = repository.findById(id).orElseThrow({ -> new EmployeeNotFoundException(id) })
    employee.setName(newEmployee.getName())
    employee.setRole(newEmployee.getRole())
    return repository.save(employee)
  }

  @DeleteMapping("/employees/{id}")
  void deleteEmployee(@PathVariable UUID id) {
    repository.deleteById(id)
  }
}
