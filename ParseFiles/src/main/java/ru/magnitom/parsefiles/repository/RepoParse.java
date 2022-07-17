package ru.magnitom.parsefiles.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.magnitom.parsefiles.entity.ParseXmlPayment;


@Repository
public interface RepoParse extends CrudRepository<ParseXmlPayment, Integer> {
}