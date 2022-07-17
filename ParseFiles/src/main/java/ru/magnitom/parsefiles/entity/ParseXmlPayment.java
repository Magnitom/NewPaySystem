package ru.magnitom.parsefiles.entity;


import javax.persistence.*;

@Entity
@Table(name = "parsexmlpayment")
public class ParseXmlPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;

    @Column(name = "frompay")
    String fromPay;

    @Column(name = "topay")
    String toPay;

    @Column(name = "amount")
    Integer amount;

    public ParseXmlPayment(String fromPay, String toPay, Integer amount) {
        this.fromPay = fromPay;
        this.toPay = toPay;
        this.amount = amount;
    }

    public ParseXmlPayment() {

    }
}
