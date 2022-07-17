package ru.magnitom.parsefiles.api;


import org.springframework.stereotype.Component;
import ru.magnitom.parsefiles.entity.Payments;

import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;
import java.util.List;

@Component
public interface PaymentParseFile {

    List<Payments> getFile();

    void parseFileAndWriteToDB(List<Payments> listPayXml) throws XMLStreamException, FileNotFoundException;
}