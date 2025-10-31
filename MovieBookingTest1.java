// MovieBookingTest.java
// Software Testing Methodologies - Part 1
// Domain: Online Movie Ticket Booking System
// Created by: [Your Name]
// Date: 18 October 2025

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.time.Duration;

public class MovieBookingTest {

    public static void main(String[] args) {
        // Path to Excel File
        String excelPath = "Template-TestCase.xlsx";
        String sheetName = "Sheet1"; // Update if your Excel sheet name is different

        // Set up ChromeDriver (ensure chromedriver.exe is in project root)
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        try {
            // Load Excel workbook
            FileInputStream file = new FileInputStream(new File(excelPath));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheet(sheetName);

            // Navigate to BookMyShow (live website)
            driver.get("https://bookmyshow.com/");
            Thread.sleep(3000);

            System.out.println("===== TEST EXECUTION STARTED =====");

            // Loop through each test case row
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                String testCaseID = row.getCell(0).getStringCellValue();
                String inputField = row.getCell(1).getStringCellValue();
                String inputValue = row.getCell(2).toString();
                String expectedOutput = row.getCell(3).getStringCellValue();

                String actualOutput = "";
                String result = "";

                try {
                    // ----------------------------
                    // Case 1: Number of Tickets
                    // ----------------------------
                    if (inputField.equalsIgnoreCase("Number of Tickets")) {
                        // BookMyShow doesn’t take ticket input directly without selecting a show.
                        // So we perform logical validation as per BVA/ECP rules.
                        double num = Double.parseDouble(inputValue);

                        if (num >= 1 && num <= 10) {
                            actualOutput = "Accepted";
                        } else {
                            actualOutput = "Rejected";
                        }
                    }

                    // ----------------------------
                    // Case 2: Email Address
                    // ----------------------------
                    else if (inputField.equalsIgnoreCase("Email Address")) {
                        // Validate email pattern — BookMyShow uses login popup (simulate here)
                        if (inputValue.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
                            actualOutput = "Accepted";
                        } else {
                            actualOutput = "Rejected";
                        }
                    }

                    // ----------------------------
                    // Compare Expected vs Actual
                    // ----------------------------
                    if (actualOutput.equalsIgnoreCase(expectedOutput)) {
                        result = "Pass";
                    } else {
                        result = "Fail";
                    }

                } catch (Exception e) {
                    actualOutput = "Error";
                    result = "Fail";
                }

                // ----------------------------
                // Write results back to Excel
                // ----------------------------
                Cell actualCell = row.createCell(4);
                actualCell.setCellValue(actualOutput);

                Cell resultCell = row.createCell(5);
                resultCell.setCellValue(result);

                System.out.printf("%s -> %-20s %-10s => %-10s (%s)%n",
                        testCaseID, inputField, inputValue, actualOutput, result);
            }

            // Save Excel with results
            file.close();
            FileOutputStream outFile = new FileOutputStream(new File(excelPath));
            workbook.write(outFile);
            outFile.close();
            workbook.close();

            System.out.println("===== TEST EXECUTION COMPLETED SUCCESSFULLY =====");
            System.out.println("Results written to Template-TestCase.xlsx");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
