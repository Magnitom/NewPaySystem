package ru.magnitom.parsefiles.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.magnitom.parsefiles.api.PaymentParseFile;
import ru.magnitom.parsefiles.entity.ParseXmlPayment;
import ru.magnitom.parsefiles.entity.Payments;
import ru.magnitom.parsefiles.repository.RepoGetPayment;
import ru.magnitom.parsefiles.repository.RepoParse;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Component
public class XMLParseFileImpl implements PaymentParseFile {

    @Autowired
    RepoParse repoParse;

    @Autowired
    RepoGetPayment repoGetPayment;

    @Override
    public List<Payments> getFile() {
        return repoGetPayment.findByTypeFile("xml");
    }

    @Override
    public void parseFileAndWriteToDB(List<Payments> listPayXml) throws XMLStreamException, FileNotFoundException {
        //        String inputFile = "D:\\IT\\project idea\\PaymentSystem\\payment.xml"; //for Windows path
        for (Payments paymentXml : listPayXml) {
            String pay = paymentXml.getPayment();
            boolean fromDone = false, toDone = false, amountDone = false;
            String[] arrPaymentXml = new String[3];
            try (FileInputStream fileInputStream = new FileInputStream(pay)) {
                XMLInputFactory f = XMLInputFactory.newFactory();
                XMLStreamReader sr = f.createXMLStreamReader(fileInputStream);
                sr.next();
                if (sr.isStartElement() && sr.getLocalName().equals("Payment")) {
                    sr.next();
                    while (sr.hasNext() || sr.getEventType() != 8) {
                        if (sr.isStartElement()) {
                            switch (sr.getLocalName()) {
                                case "From": {
                                    sr.next();
                                    arrPaymentXml[0] = sr.getText();
                                    fromDone = true;
                                    break;
                                }
                                case "To": {
                                    sr.next();
                                    arrPaymentXml[1] = sr.getText();
                                    toDone = true;
                                    break;
                                }
                                case "Amount": {
                                    sr.next();
                                    arrPaymentXml[2] = sr.getText();
                                    amountDone = true;
                                    break;
                                }
                                default:
                                    break;
                            }
                        } else {
                            sr.next();
                        }
                    }
                }
            } catch (XMLStreamException | IOException ignored) {
            }
            if (fromDone && toDone && amountDone) {
                repoParse.save(new ParseXmlPayment(arrPaymentXml[0], arrPaymentXml[1], Integer.parseInt(arrPaymentXml[2])));
                paymentXml.setPaymentCompleted(true);
                repoGetPayment.save(paymentXml);
            }
        }
    }
}
