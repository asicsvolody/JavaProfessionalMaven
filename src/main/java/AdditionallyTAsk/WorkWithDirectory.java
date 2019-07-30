package AdditionallyTAsk;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class WorkWithDirectory {
    private File[] classFilesArr;

    public WorkWithDirectory(String urlDirectory) {
        classFilesArr = getClassFilesFromDir(new File(urlDirectory));
        printAllFiles(classFilesArr);
    }

    private File[] getClassFilesFromDir (File dir) {
        ArrayList<File> filesArrList =new ArrayList<>();
        if(dir.isDirectory()){
            File[] filesArr = dir.listFiles();
            for (File file: filesArr) {
                if(file.getName().endsWith(".class")){
                    filesArrList.add(file);
                }
            }
        }
        File[] classFilesFromDirArr = new File[filesArrList.size()];
        filesArrList.toArray(classFilesFromDirArr);
        return classFilesFromDirArr;
    }

    private void printAllFiles(File[] arrFiles){
        for (File f: arrFiles) {
            System.out.println(f.getName());
        }
    }
}
