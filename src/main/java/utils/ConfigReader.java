package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;

    static {
        try {
            properties = new Properties();
            // Menggunakan lokasi file sesuai proyek Anda
            FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
            properties.load(fis);
            fis.close();
        } catch (IOException e) {
            // JANGAN langsung lempar RuntimeException agar runner GitHub Actions tidak crash saat file config absen
            System.out.println("⚠️ Warning: File config.properties tidak ditemukan di lokal. Mencoba membaca dari Environment Variables...");
        }
    }

    public static String getProperty(String key) {
        // 1. Cek apakah ada Environment Variable dari GitHub Actions terlebih dahulu
        String envValue = System.getenv(key);
        if (envValue != null && !envValue.isEmpty()) {
            return envValue;
        }

        // 2. Jika tidak ada (seperti saat running lokal di IntelliJ), baca dari file config.properties asli
        if (properties != null) {
            return properties.getProperty(key);
        }

        return null;
    }

    public static void printAllProperties() {
        if (properties != null) {
            properties.forEach((k, v) -> System.out.println(k + " = " + v));
        } else {
            System.out.println("Properties belum dimuat atau kosong.");
        }
    }
}
