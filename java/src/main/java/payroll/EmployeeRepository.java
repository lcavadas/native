package payroll;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
interface EmployeeRepository extends MongoRepository<Employee, UUID> {}
//class EmployeeRepository {
//  final MongoTemplate mongoTemplate;
//
//  public EmployeeRepository(MongoTemplate mongoTemplate) {
//    this.mongoTemplate = mongoTemplate;
//  }
//
//  public List<Employee> findAll() {
//    return mongoTemplate.findAll(Employee.class);
//  }
//
//  public Employee create(Employee employee) {
//    employee.setId(UUID.randomUUID());
//    return mongoTemplate.save(employee);
//  }
//
//  public Employee update(Employee employee) {
//    return mongoTemplate.save(employee);
//  }
//
//  public Optional<Employee> findById(UUID id) {
//    return Optional.ofNullable(mongoTemplate.findById(id, Employee.class));
//  }
//
//  public void deleteById(UUID id) {
//    mongoTemplate.findAndRemove(BasicQuery.query(Criteria.where("id").is(id)), Employee.class);
//  }
//
//}
