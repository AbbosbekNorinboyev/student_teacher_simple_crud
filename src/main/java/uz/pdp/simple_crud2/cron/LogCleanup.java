package uz.pdp.simple_crud2.cron;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

@Component
public class LogCleanup {
    private static final Logger logger = LoggerFactory.getLogger(LogCleanup.class);
    private static final String LOG_FILE_PATH = "C:\\Abbos\\Spring Project\\Test Projects\\SimpleCRUDTeacherAndStudent\\logs\\SimpleCRUDTeacherAndStudent.log";

    @Scheduled(cron = "*/20 * * * * *")
    public void cleanLogFile() {
        File file = new File(LOG_FILE_PATH);
        if (file.exists()) {
            try {
                Files.write(file.toPath(), new byte[0], StandardOpenOption.TRUNCATE_EXISTING);
                logger.info("✅ Log fayl tozalandi: "+LOG_FILE_PATH);
                System.out.println("clearLogFile ishlamoqda...");
            } catch (IOException e) {
                logger.error("❌ Log faylni tozalashda xatolik: " + e.getMessage());
            }
        } else {
            logger.warn("⚠ Log fayl topilmadi: " + LOG_FILE_PATH);
        }
    }
}
