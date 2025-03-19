package pp2;

import java.io.File;

public class maintest {
    public static void main(String[] args) {
        File folder = new File("./images/");
        File[] listOfFiles = folder.listFiles();
        if(listOfFiles != null) {
            for(int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile()) {
                    System.out.println("File " + listOfFiles[i].getName());
                } else if (listOfFiles[i].isDirectory()) {
                    System.out.println("Directory " + listOfFiles[i].getName());
                }
            }
        }
        System.out.println("aaaaa00");
    }
}
