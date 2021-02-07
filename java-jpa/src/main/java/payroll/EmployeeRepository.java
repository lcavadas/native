package payroll;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
interface EmployeeRepository extends JpaRepository<Employee, UUID> {
}
