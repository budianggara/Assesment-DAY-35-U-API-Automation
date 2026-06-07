package core;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import utils.ConfigReader;

public class BaseTests {

    @BeforeClass
    public void setup() {
        // 1. Ambil nilai URL dari ConfigReader
        String targetBaseURI = ConfigReader.getProperty("baseUrl");

        // 2. Antisipasi / Fallback jika nilainya null (terutama saat running di lingkungan CI/CD)
        if (targetBaseURI == null || targetBaseURI.isEmpty()) {
            // Sediakan URL default di sini agar pengujian tidak langsung crash karena kehabisan alamat API
            targetBaseURI = "https://api-target-anda.com";
            System.out.println("Warning: baseUrl tidak ditemukan di properti/env, menggunakan URL fallback default.");
        }

        // 3. Set nilai RestAssured baseURI secara aman
        RestAssured.baseURI = targetBaseURI;
        System.out.println("RestAssured Base URI di-set ke: " + RestAssured.baseURI);
    }
}
