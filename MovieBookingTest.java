import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.time.Duration;

public class MovieBookingTest {

    public static void main(String[] args) {
        String excelPath = "Template-TestCase.xlsx";
        String sheetName = "Sheet1";

        // Setup ChromeDriver
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        try {
            // Load Excel
            FileInputStream file = new FileInputStream(excelPath);
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheet(sheetName);

            // Open dummy BMS page
            driver.get("file:///" + System.getProperty("user.dir") + "/dummy_bms.html");

            System.out.println("===== TEST EXECUTION STARTED =====");

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
                    // Select Mumbai always for location
                    WebElement citySelect = driver.findElement(By.id("city"));
                    citySelect.sendKeys("MUMBAI");

                    // -----------------------------
                    // Number of Tickets
                    // -----------------------------
                    if (inputField.equalsIgnoreCase("Number of Tickets")) {
                        WebElement ticketsInput = driver.findElement(By.id("tickets"));
                        ticketsInput.clear();
                        ticketsInput.sendKeys(inputValue);

                        WebElement submitBtn = driver.findElement(By.id("submit"));
                        submitBtn.click();

                        WebElement resultText = driver.findElement(By.id("result"));
                        if (resultText.getText().contains("Tickets: Accepted"))
                            actualOutput = "Accepted";
                        else
                            actualOutput = "Rejected";
                    }

                    // -----------------------------
                    // Email Address
                    // -----------------------------
                    else if (inputField.equalsIgnoreCase("Email Address")) {
                        WebElement emailInput = driver.findElement(By.id("email"));
                        emailInput.clear();
                        emailInput.sendKeys(inputValue);

                        WebElement submitBtn = driver.findElement(By.id("submit"));
                        submitBtn.click();

                        WebElement resultText = driver.findElement(By.id("result"));
                        if (resultText.getText().contains("Email: Accepted"))
                            actualOutput = "Accepted";
                        else
                            actualOutput = "Rejected";
                    }

                    // Compare with expected
                    result = actualOutput.equalsIgnoreCase(expectedOutput) ? "Pass" : "Fail";

                } catch (Exception e) {
                    actualOutput = "Error";
                    result = "Fail";
                }

                // Write results back to Excel
                Cell actualCell = row.createCell(4);
                actualCell.setCellValue(actualOutput);

                Cell resultCell = row.createCell(5);
                resultCell.setCellValue(result);

                System.out.printf("%s -> %-20s %-10s => %-10s (%s)%n",
                        testCaseID, inputField, inputValue, actualOutput, result);
            }

            // Save Excel
            file.close();
            FileOutputStream outFile = new FileOutputStream(excelPath);
            workbook.write(outFile);
            outFile.close();
            workbook.close();

            System.out.println("===== TEST EXECUTION COMPLETED =====");
            System.out.println("Results updated in Template-TestCase.xlsx");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
