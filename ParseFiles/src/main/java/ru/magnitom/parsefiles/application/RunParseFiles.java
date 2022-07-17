package ru.magnitom.parsefiles.application;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.magnitom.commonentity.entity.Payments;
import ru.magnitom.parsefiles.api.PaymentParseFile;

import java.util.List;


@Component
public class RunParseFiles implements CommandLineRunner {

    @Autowired
    PaymentParseFile paymentParseFile;

    @Override
    public void run(String... args) throws Exception {
        //get XML payments from DB
        List<Payments> listXmlPay = paymentParseFile.getFile();
        //parse XML payments and write to DB
        paymentParseFile.parseFileAndWriteToDB(listXmlPay);
    }
}