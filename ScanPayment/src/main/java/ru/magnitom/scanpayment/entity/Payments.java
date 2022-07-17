package ru.magnitom.scanpayment.entity;


import javax.persistence.*;

@Entity
@Table(name = "payments")
public class Payments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "payment")
    private String payment;
    @Column(name = "type_file")
    private  String typeFile;

    @Column(name = "payment_completed")
    boolean paymentCompleted = false;

    public Payments(String payment, String typeFile) {
        this.payment = payment;
        this.typeFile = typeFile;
    }

    public Payments() {
    }

    @Override
    public String toString() {
        return "PaymentFile{" +
                "id=" + id +
                ", payment='" + payment + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public boolean isPaymentCompleted() {
        return paymentCompleted;
    }

    public void setPaymentCompleted(boolean paymentCompleted) {
        this.paymentCompleted = paymentCompleted;
    }
}