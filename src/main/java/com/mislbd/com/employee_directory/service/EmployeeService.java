package com.mislbd.com.employee_directory.service;

import com.google.api.services.docs.v1.Docs;
import com.google.api.services.docs.v1.model.Document;
import com.google.api.services.docs.v1.model.ParagraphElement;
import com.google.api.services.docs.v1.model.StructuralElement;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@Service
public class EmployeeService {

    private final String SPREADSHEET_ID = "1V_lCoMNRudrcSjSmTr-DGuCbnxlrVQBhqPALlhm_2RU";  // Spreadsheet ID
    private final String RANGE = "Sheet1!A1:B1";

    private final Sheets sheetsService;

    public EmployeeService(Sheets sheetsService) {
        this.sheetsService = sheetsService;
    }

    public List<String> getEmployeeDataFromGoogleSheets() throws IOException, GeneralSecurityException {
        ValueRange response = sheetsService.spreadsheets().values()
                .get(SPREADSHEET_ID, RANGE)
                .execute();

        List<List<Object>> values = response.getValues();
        List<String> employeeData = new ArrayList<>();

        if (values == null || values.isEmpty()) {
            employeeData.add("No data found.");
        } else {
            for (List<Object> row : values) {
                // Check and log each row
                System.out.println("Row: " + row);
                if (row.size() > 1) {
                    employeeData.add(String.format("A1: %s, B1: %s", row.get(0), row.get(1)));
                } else if (row.size() > 0) {
                    employeeData.add(String.format("A1: %s, B1: Not present", row.get(0)));
                } else {
                    employeeData.add("Empty row");
                }
            }
        }
        System.out.println(employeeData);
        return employeeData;
    }
}
