package ru.magnitom.scanpayment.api;

import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

@Component
public interface PaymentFileDB {

    void addFilePayment(List<File> listPayment);

}
