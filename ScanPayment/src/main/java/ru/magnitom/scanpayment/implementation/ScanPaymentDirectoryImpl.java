package ru.magnitom.scanpayment.implementation;

import org.springframework.stereotype.Component;
import ru.magnitom.scanpayment.api.ScanPaymentDirectory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class ScanPaymentDirectoryImpl implements ScanPaymentDirectory {

    @Override
    public List<File> scanDirectory() throws IOException {
        File folder = new File(tempDirectory);
        //create arrays with folders and files in the C://Temp
        File[] arrFiles = folder.listFiles();
        List<File> listScanFile = new ArrayList<>(Arrays.asList(arrFiles));
        //delete folders in the list:
        listScanFile.removeIf(file -> !file.isFile());
        //delete !(xml||txt||json) files in the list and in the folder
        listScanFile = new ArrayList<>(fileIsCorrect(listScanFile));
        //move files xml||txt||json to the C:\Temp\PaymentDirectory
        //and delete this in the C:\Temp
        return new ArrayList<>(removeAndPullFile(listScanFile));
    }

    @Override
    public List<File> fileIsCorrect(List<File> listScanFile) throws IOException {
        for (int i = 0; i < listScanFile.size(); i++) {
            String fileName = listScanFile.get(i).getName();
            if (!(fileName.endsWith(".txt") || fileName.endsWith(".json") || fileName.endsWith(".xml"))) {
                Files.delete(Paths.get(listScanFile.get(i).getPath()));
                listScanFile.remove(i);
                i--;
            }
        }
        return listScanFile;
    }

    @Override
    public List<File> removeAndPullFile(List<File> listScanFile) throws IOException {
        for (File file : listScanFile) {
            Files.copy(file.toPath(), Paths.get(paymentDirectory.concat(File.separator + file.getName())), StandardCopyOption.REPLACE_EXISTING);
            Files.delete(Paths.get(file.getPath()));
        }
        File folder = new File(paymentDirectory);
        File[] arrPayment = folder.listFiles();
        return new ArrayList<>(Arrays.asList(arrPayment));
    }
}
