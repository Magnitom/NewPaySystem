package ru.magnitom.checkpayments.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.magnitom.commonentity.entity.Payments;

import java.util.List;


@Repository
public interface GetFailPaymentsRepo extends CrudRepository<Payments, Integer> {
    List<Payments> findAllByTypeFile(String typeFile);
}