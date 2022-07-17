package ru.magnitom.scanpayment.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.magnitom.scanpayment.api.PaymentFileDB;
import ru.magnitom.scanpayment.api.ScanPaymentDirectory;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
public class RunApplication implements CommandLineRunner {

    @Autowired
    ScanPaymentDirectory scanPaymentDirectory;

    @Autowired
    PaymentFileDB paymentFileDB;

    @Override
    public void run(String... args) throws IOException {
        //getting the list of correct Payment File
        List<File> paymentFile = scanPaymentDirectory.scanDirectory();
        //add payment file to the DB
        paymentFileDB.addFilePayment(paymentFile);

    }
}