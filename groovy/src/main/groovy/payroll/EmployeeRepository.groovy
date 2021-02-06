package payroll

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Component

@Component
interface EmployeeRepository extends MongoRepository<Employee, UUID> {}
