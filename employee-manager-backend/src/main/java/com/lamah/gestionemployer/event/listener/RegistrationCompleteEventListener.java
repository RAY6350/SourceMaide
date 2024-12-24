package com.lamah.gestionemployer.event.listener;

import com.lamah.gestionemployer.email.EmailService;
import com.lamah.gestionemployer.event.RegistrationCompleteEvent;
import com.lamah.gestionemployer.model.EmployeeDTO;
import com.lamah.gestionemployer.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {

    private final EmployeeService employeeService;
    private final EmailService emailService;

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        EmployeeDTO employeeDTO = event.getEmployeeDTO();
        String token = UUID.randomUUID().toString();

        employeeService.validateVerificationToken(token, employeeDTO);

        String url = event.getApplicationUrl() + "api/v1/employees" + "/confirm?token=";

        emailService.sendSimpleMail(employeeDTO.name(), employeeDTO.email(), token, url);
    }
}
