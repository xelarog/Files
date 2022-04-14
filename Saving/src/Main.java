import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

    public static void main(String[] args) {
        GameProgress gp1 = new GameProgress(95, 4, 2, 234.35);
        GameProgress gp2 = new GameProgress(70, 6, 5, 456.21);
        GameProgress gp3 = new GameProgress(54, 8, 7, 620.58);

        saveGame("D://Games/savegames/save1.dat", gp1);
        saveGame("D://Games/savegames/save2.dat", gp2);
        saveGame("D://Games/savegames/save3.dat", gp3);

        zipFiles("D://Games/savegames/savezip.zip",
                "D://Games/savegames/save1.dat",
                "D://Games/savegames/save2.dat",
                "D://Games/savegames/save3.dat");
    }

    public static void saveGame(String saveName, GameProgress gp) {

        try (FileOutputStream fos = new FileOutputStream(saveName);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gp);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void zipFiles(String archiveName, String ... fileNames) {

        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(archiveName))) {
            for (String fileName : fileNames) {
                File file = new File(fileName);
                FileInputStream fis = new FileInputStream(fileName);
                ZipEntry entry = new ZipEntry(file.getName());
                zout.putNextEntry(entry);
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                zout.write(buffer);
                zout.closeEntry();

                fis.close();
                file.delete();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
