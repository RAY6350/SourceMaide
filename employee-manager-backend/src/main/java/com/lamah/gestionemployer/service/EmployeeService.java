package com.lamah.gestionemployer.service;

import com.lamah.gestionemployer.entity.Employee;
import com.lamah.gestionemployer.entity.VerificationToken;
import com.lamah.gestionemployer.model.EmployeeDTO;
import com.lamah.gestionemployer.model.EmployeeRequestBody;
import com.lamah.gestionemployer.model.EmployeeUpdateRequestBody;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    EmployeeDTO addEmployee(EmployeeRequestBody employeeRequestBody);

    List<EmployeeDTO> findAllEmployees();

    void updateEmployee(Long id, EmployeeUpdateRequestBody employeeUpdateRequestBody);

    void deleteEmployee(Long id);

    EmployeeDTO findEmployeeById(Long id);

    String validateVerificationToken(String token);

    void validateVerificationToken(String token, EmployeeDTO employeeDTO);

    VerificationToken generateNewVerificationToken(String oldToken);

    Employee findByEmailIgnoreCase(String email);

    void createPasswordResetToken(Employee employee, String token);

    String validatePasswordResetToken(String token);

    Optional<Employee> getUserByPasswordResetToken(String token);

    void changePassword(Employee employee, String newPassword);

    boolean checkIfValidOldPassword(Employee employee, String oldPassword);
}
