package com.lamah.gestionemployer.auth;

import com.lamah.gestionemployer.entity.Employee;
import com.lamah.gestionemployer.exceptions.AccountNotVerifiedException;
import com.lamah.gestionemployer.jwt.JwtUtil;
import com.lamah.gestionemployer.model.EmployeeDTO;
import com.lamah.gestionemployer.model.EmployeeDTOMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final AuthenticationManager manager;
    private final EmployeeDTOMapper dtoMapper;
    private final JwtUtil jwtUtil;

    public AuthenticationService (
            AuthenticationManager manager,
            EmployeeDTOMapper dtoMapper,
            JwtUtil jwtUtil) {
        this.manager = manager;
        this.dtoMapper = dtoMapper;
        this.jwtUtil = jwtUtil;
    }

    public AuthenticationResponse login(AuthenticationRequest request) {
        Authentication authentication = manager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()
                )
        );
        Employee principal = (Employee) authentication.getPrincipal();
        EmployeeDTO employeeDTO = dtoMapper.apply(principal);
        if (!principal.getEnabled()) {
            throw new AccountNotVerifiedException(
                    "Employee with email " + employeeDTO.email() + " is not verified.");
        }
        String token = jwtUtil.issueToken(employeeDTO.email(), employeeDTO.roles());
        return new AuthenticationResponse(token, employeeDTO);
    }
}








