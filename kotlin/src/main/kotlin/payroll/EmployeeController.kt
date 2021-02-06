package payroll

import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
internal class EmployeeController(private val repository: EmployeeRepository) {
  @GetMapping("/employees")
  fun all(): List<Employee?> {
    return repository.findAll()
  }

  @PostMapping("/employees")
  fun newEmployee(@RequestBody newEmployee: Employee): Employee {
    return repository.save(newEmployee)
  }

  @GetMapping("/employees/{id}")
  fun one(@PathVariable id: UUID): Employee {
    return repository.findById(id)
      .orElseThrow { EmployeeNotFoundException(id) }!!
  }

  @PutMapping("/employees/{id}")
  fun replaceEmployee(@RequestBody newEmployee: Employee, @PathVariable id: UUID): Employee {
    val employee = repository.findById(id).orElseThrow { EmployeeNotFoundException(id) }!!
    employee.name = newEmployee.name
    employee.role = newEmployee.role
    return repository.save(employee)
  }

  @DeleteMapping("/employees/{id}")
  fun deleteEmployee(@PathVariable id: UUID) {
    repository.deleteById(id)
  }
}
