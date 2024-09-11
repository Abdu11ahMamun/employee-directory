package com.mislbd.com.employee_directory.controller;

import  com.mislbd.com.employee_directory.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/data")
    public List<String> getEmployeeData() throws IOException, GeneralSecurityException {
        return employeeService.getEmployeeDataFromGoogleSheets();
    }
}
