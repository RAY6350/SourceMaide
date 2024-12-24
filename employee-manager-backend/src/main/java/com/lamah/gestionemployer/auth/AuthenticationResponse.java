package com.lamah.gestionemployer.auth;


import com.lamah.gestionemployer.model.EmployeeDTO;

public record AuthenticationResponse(
        String token,
        EmployeeDTO employeeDTO
) {
}
