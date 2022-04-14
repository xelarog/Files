import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Installation {
    private List<File> listDirs = Arrays.asList(
            new File("D://Games/src"),
            new File("D://Games/res"),
            new File("D://Games/savegames"),
            new File("D://Games/temp"),
            new File("D://Games/src/main"),
            new File("D://Games/src/test"),
            new File("D://Games/res/drawables"),
            new File("D://Games/res/vectors"),
            new File("D://Games/res/icons"));

    private List<File> listFiles = Arrays.asList(
            new File("D://Games/temp", "temp.txt"),
            new File("D://Games/src/main", "Main.java"),
            new File("D://Games/src/main", "Util.java"));

    public void start() {
        StringBuilder log = new StringBuilder();

        for (File dir : listDirs) {
            if (dir.mkdir()) {
                log.append(dir.getPath() + " - каталог успешно создан\n");
            } else {
                log.append(dir.getPath() + " - ошибка создания каталога\n");
            }
        }
        for (File file : listFiles) {
            try {
                if (file.createNewFile()) {
                    log.append(file.getPath() + " - файл успешно создан\n");
                }
            } catch (IOException ex) {
                log.append(ex.getMessage() + "\n");
            }
        }

        saveLog(log.toString());
    }

    private void saveLog (String log) {
        try (FileWriter fw = new FileWriter("D://Games/temp/temp.txt", false)) {
            fw.write(log);
            fw.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
