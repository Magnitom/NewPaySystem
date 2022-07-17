package ru.magnitom.scanpayment.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.magnitom.commonentity.entity.Payments;
import ru.magnitom.scanpayment.api.PaymentFileDB;
import ru.magnitom.scanpayment.repository.PaymentFileRepository;

import java.io.File;
import java.util.List;


@Service
public class PaymentFileDbImpl implements PaymentFileDB {

    @Autowired
    PaymentFileRepository paymentFileRepository;

    @Override
    public void addFilePayment(List<File> listPayment) {
        for (File file : listPayment) {
            String extension;
            if (file.getName().endsWith("txt")){
                extension = "txt";
            } else if (file.getName().endsWith("json")){
                extension = "json";
            } else {
                extension = "xml";
            }
            paymentFileRepository.save(new Payments(file.getPath(), extension));
        }
    }
}