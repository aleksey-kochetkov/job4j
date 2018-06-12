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
        Account src;
        Account dst;
        User srcUser = this.findByPassport(srcPassport);
        User dstUser = this.findByPassport(dstPassport);
        if (srcUser != null && dstUser != null) {
            int srcIndex = this.accounts.get(srcUser).indexOf(new Account(srcRequisites));
            if (srcIndex > -1) {
                src = this.accounts.get(srcUser).get(srcIndex);
                int dstIndex = this.accounts.get(dstUser).indexOf(new Account(dstRequisites));
                if (dstIndex > -1) {
                    dst = this.accounts.get(dstUser).get(dstIndex);
                    if (src != null && dst != null) {
                        src.addAmount(amount.negate());
                        dst.addAmount(amount);
                        result = true;
                    }
                }
            }
        }
        return result;
    }

    User findByPassport(String passport) {
        User result = null;
        for (User u : this.accounts.keySet()) {
            if (u.getPassport().equals(passport)) {
                result = u;
                break;
            }
        }
        return result;
    }

    int accountsSize() {
        return this.accounts.size();
    }
}
