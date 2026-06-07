package tests;

import core.BaseTests;
import utils.Utils;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

public class CreateTicketTests extends BaseTests {
    // 1. Cukup deklarasikan variabel kosong di luar method
    private String[][] testData;

    @BeforeMethod
    public void setupTestData() {

        // PASTI KAN JALUR FILENYA TERTULIS LENGKAP SEPERTI INI:
        testData = Utils.getTestData("src/test/resources/data/data-driven.xlsx", "user-data");

    }

    @Test
    public void createTicket() {
        // 3. Gunakan data dengan aman di sini
        System.out.println("Data yang digunakan: " + testData[0][0]);
    }
}
