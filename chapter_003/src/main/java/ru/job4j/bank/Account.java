package ru.job4j.bank;

import java.math.BigDecimal;

public class Account {
    private BigDecimal value = new BigDecimal("0.00");
    private String requisites;

    public Account(String requisites) {
        this.requisites = requisites;
    }

    Account(String requisites, BigDecimal value) {
        this.requisites = requisites;
        this.value = value;
    }

    public BigDecimal getValue() {
        return this.value;
    }

    public String getRequisites() {
        return this.requisites;
    }

    public void addAmount(BigDecimal amount) {
        this.value = this.value.add(amount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Account account = (Account) o;
        return requisites != null ? requisites.equals(account.requisites) : account.requisites == null;
    }

    @Override
    public int hashCode() {
        return requisites != null ? requisites.hashCode() : 0;
    }
}
