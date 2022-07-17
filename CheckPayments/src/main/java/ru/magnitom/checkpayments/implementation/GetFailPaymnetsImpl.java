package ru.magnitom.checkpayments.implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.magnitom.checkpayments.api.GetFailPaymnets;
import ru.magnitom.checkpayments.repository.GetFailPaymentsRepo;
import ru.magnitom.commonentity.entity.Payments;

@Component
public class GetFailPaymnetsImpl implements GetFailPaymnets {

    @Autowired
    GetFailPaymentsRepo getFailPaymentsRepo;
    private static final Logger log = LoggerFactory.getLogger(GetFailPaymnetsImpl.class);

    @Override
    public Iterable<Payments> getStatusPayments() {
        return getFailPaymentsRepo.findAllByTypeFile("xml");

    }

    @Override
    public void writeStatusPaymnetToLog(Iterable<Payments> listPay) {
        for (Payments pay :
                listPay) {
            if (!pay.isPaymentCompleted()) {
                String failPay = "Платёж не совершён, ошибочное содержимое платежа: " + pay.getPayment();
                log.error(failPay);
            }
        }
    }
}
