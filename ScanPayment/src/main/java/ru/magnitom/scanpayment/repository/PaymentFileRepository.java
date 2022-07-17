package ru.magnitom.scanpayment.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.magnitom.commonentity.entity.Payments;

@Repository
public interface PaymentFileRepository extends CrudRepository<Payments, Integer> {
}