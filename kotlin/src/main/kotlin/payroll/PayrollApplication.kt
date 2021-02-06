package payroll

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class PayrollApplication

fun main(args: Array<String>) {
  SpringApplication.run(PayrollApplication::class.java, *args)
}
