package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;

    static {
        try {
            properties = new Properties();
            FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
            properties.load(fis);
            fis.close();
            System.out.println("Sukses: File config.properties berhasil dimuat.");
        } catch (Throwable t) {
            // Menangkap SEGALA jenis error (termasuk runtime exception akibat salah ketik di properti)
            // sehingga pembacaan via Environment Variables di GitHub Actions/Gradle tetap bisa berjalan aman.
            properties = null;
            System.out.println("Warning: File config.properties tidak dapat dimuat (" + t.getMessage() + "). Mencoba fallback ke Environment Variables...");
        }
    }

    public static String getProperty(String key) {
        // 1. Cek apakah ada Environment Variable (untuk GitHub Actions)
        String envValue = System.getenv(key);
        if (envValue != null && !envValue.isEmpty()) {
            return envValue;
        }

        // 2. Jika tidak ada, baca dari file config.properties lokal
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
