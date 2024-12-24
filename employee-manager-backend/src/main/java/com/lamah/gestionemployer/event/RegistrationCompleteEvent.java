package com.lamah.gestionemployer.event;

import com.lamah.gestionemployer.model.EmployeeDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class RegistrationCompleteEvent extends ApplicationEvent {

    private EmployeeDTO employeeDTO;
    private String applicationUrl;

    public RegistrationCompleteEvent(EmployeeDTO employeeDTO, String applicationUrl) {
        super(employeeDTO);
        this.employeeDTO = employeeDTO;
        this.applicationUrl = applicationUrl;
    }
}
