package ru.magnitom.checkpayments.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.magnitom.checkpayments.entity.Payments;

import java.util.List;


@Repository
public interface GetFailPaymentsRepo extends CrudRepository<Payments, Integer> {
    List<Payments> findAllByTypeFile(String typeFile);
}