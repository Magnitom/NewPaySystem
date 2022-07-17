package ru.magnitom.checkpayments.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.magnitom.checkpayments.api.GetFailPaymnets;
import ru.magnitom.checkpayments.entity.Payments;


@Component
public class RunCheckPayments implements CommandLineRunner {

    @Autowired
    GetFailPaymnets getFailPaymnets;

    @Override
    public void run(String... args) throws Exception {
        //get list of payments files from DB
        Iterable<Payments> listPay = getFailPaymnets.getStatusPayments();
        //write fails payments to log file log.txt
        getFailPaymnets.writeStatusPaymnetToLog(listPay);
    }
}