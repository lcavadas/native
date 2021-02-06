package payroll

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
internal interface EmployeeRepository : MongoRepository<Employee?, UUID?>
