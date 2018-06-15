package ru.job4j.bank;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.math.BigDecimal;

/**
 * @author Aleksey Kochetkov
 */
public class Bank {
    private Map<User, List<Account>> accounts = new HashMap<>();

    public void addUser(User user) {
        this.accounts.putIfAbsent(user, new ArrayList<>());
    }

    public void deleteUser(User user) {
        this.accounts.remove(user);
    }

    public void addAccountToUser(String passport, Account account) {
        this.accounts.get(this.findByPassport(passport)).add(account);
    }

    public void deleteAccountFromUser(String passport, Account account) {
        this.accounts.get(findByPassport(passport)).remove(account);
    }

    public List<Account> getUserAccounts(String passport) {
        return this.accounts.get(this.findByPassport(passport));
    }

    public boolean transferMoney(String srcPassport, String srcRequisites,
                                 String dstPassport, String dstRequisites, BigDecimal amount) {
        boolean result = false;
        Account srcAccount = this.findAccount(srcPassport, srcRequisites);
        Account dstAccount = this.findAccount(dstPassport, dstRequisites);
        if (srcAccount != null && dstAccount != null) {
            srcAccount.addAmount(amount.negate());
            dstAccount.addAmount(amount);
            result = true;
        }
        return result;
    }

    private User findByPassport(String passport) {
        User result = null;
        for (User u : this.accounts.keySet()) {
            if (u.getPassport().equals(passport)) {
                result = u;
                break;
            }
        }
        return result;
    }

    private Account findAccount(String passport, String requisites) {
        Account result = null;
        User user = this.findByPassport(passport);
        if (user != null) {
            int index = this.accounts.get(user).indexOf(new Account(requisites));
            if (index > -1) {
                result = this.accounts.get(user).get(index);
            }
        }
        return result;
    }

    int accountsSize() {
        return this.accounts.size();
    }
}
