package ru.magnitom.checkpayments.api;

import org.springframework.stereotype.Component;
import ru.magnitom.commonentity.entity.Payments;

@Component
public interface GetFailPaymnets {

    Iterable<Payments> getStatusPayments();

    void writeStatusPaymnetToLog(Iterable<Payments> listPay);

}
