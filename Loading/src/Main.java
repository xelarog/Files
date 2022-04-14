import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Main {

    public static void main(String[] args) {

        openZip("D://Games/savegames/savezip.zip", "D://Games/savegames");
        GameProgress gp;
        gp = openProgress("D://Games/savegames/save3.dat");
        System.out.println(gp);
    }

    public static void openZip(String zipFilePath, String unzipDir) {

        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(zipFilePath))) {
            ZipEntry entry;
            String fileName;
            while ((entry = zin.getNextEntry()) != null) {
                fileName = entry.getName();
                FileOutputStream fout = new FileOutputStream(unzipDir + "/" + fileName);
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static GameProgress openProgress(String fileName) {

        GameProgress result = null;
        try (FileInputStream fis = new FileInputStream(fileName);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            result = (GameProgress) ois.readObject();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return result;
    }

}
