package payroll

import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.Exchange
import org.springframework.amqp.rabbit.annotation.Queue
import org.springframework.amqp.rabbit.annotation.QueueBinding
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.messaging.Message
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
internal class EmployeeController(private val repository: EmployeeRepository, private val template: RabbitTemplate) {
  @GetMapping("/employees")
  fun all(): List<Employee?> {
    template.convertAndSend("native", "employee.list", "list!!!!")
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

  @RabbitListener(
    bindings = [QueueBinding(
      value = Queue("native-kotlin"),
      exchange = Exchange(value = "native", type = "topic"),
      key = ["employee.list"]
    )]
  )
  fun listed(message: Message<String?>) {
    LoggerFactory.getLogger(this.javaClass).info(message.payload)
  }
}
