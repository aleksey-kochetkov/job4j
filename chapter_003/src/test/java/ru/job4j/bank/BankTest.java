package ru.job4j.bank;

import org.junit.Test;
import java.math.BigDecimal;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class BankTest {

    @Test
    public void whenAddNewUser() {
        Bank bank = new Bank();
        User one = new User("One", "001");
        User two = new User("Two", "002");
        bank.addUser(one);
        bank.addUser(two);
        assertEquals(2, bank.accountsSize());
    }

    @Test
    public void whenAddExistingUser() {
        Bank bank = new Bank();
        User one = new User("One", "001");
        User two = new User("Two", "002");
        bank.addUser(one);
        bank.addAccountToUser("001", new Account("001001"));
        bank.addUser(two);
        bank.addUser(one);
        assertEquals(2, bank.accountsSize());
        assertEquals(1, bank.getUserAccounts("001").size());
    }

    @Test
    public void whenDeleteUser() {
        Bank bank = new Bank();
        User one = new User("One", "001");
        User two = new User("Two", "002");
        User three = new User("Three", "003");
        bank.addUser(one);
        bank.addUser(two);
        bank.addUser(three);
        bank.deleteUser(two);
        assertEquals(2, bank.accountsSize());
    }

    @Test
    public void whenAddAccountToUser() {
        Bank bank = new Bank();
        User one = new User("One", "001");
        User two = new User("Two", "002");
        bank.addUser(one);
        bank.addUser(two);
        bank.addAccountToUser(one.getPassport(), new Account("001001"));
        bank.addAccountToUser(one.getPassport(), new Account("001002"));
        bank.addAccountToUser(two.getPassport(), new Account("002001"));
        assertEquals(2, bank.getUserAccounts("001").size());
    }

    @Test
    public void whenDeleteAccountFromUser() {
        Bank bank = new Bank();
        User one = new User("One", "001");
        User two = new User("Two", "002");
        bank.addUser(one);
        bank.addUser(two);
        bank.addAccountToUser(one.getPassport(), new Account("001001"));
        Account account = new Account("001002");
        bank.addAccountToUser(one.getPassport(), account);
        bank.addAccountToUser(one.getPassport(), new Account("001003"));
        bank.addAccountToUser(two.getPassport(), new Account("002001"));
        bank.deleteAccountFromUser(one.getPassport(), account);
        assertEquals(2, bank.getUserAccounts("001").size());
    }

    @Test
    public void whenGetUserAccounts() {
        Bank bank = new Bank();
        User one = new User("One", "001");
        User two = new User("Two", "002");
        bank.addUser(one);
        bank.addUser(two);
        assertEquals(0, bank.getUserAccounts("002").size());
    }

    @Test
    public void whenTransferMoneyThenTrue() {
        Bank bank = new Bank();
        User one = new User("One", "001");
        User two = new User("Two", "002");
        bank.addUser(one);
        bank.addUser(two);
        Account src = new Account("001001", new BigDecimal("3.45"));
        Account dst = new Account("002001");
        bank.addAccountToUser("001", src);
        bank.addAccountToUser("002", dst);
        boolean result =
            bank.transferMoney("001", "001001", "002", "002001", new BigDecimal("1.23"));
        assertTrue(result);
        assertThat(src.getValue(), is(new BigDecimal("2.22")));
        assertThat(dst.getValue(), is(new BigDecimal("1.23")));
    }

    @Test
    public void whenTransferMoneySrcUserNotExistsThenFalse() {
        Bank bank = new Bank();
        User one = new User("One", "001");
        User two = new User("Two", "002");
        bank.addUser(one);
        bank.addUser(two);
        Account src = new Account("001001", new BigDecimal("3.45"));
        Account dst = new Account("002001");
        bank.addAccountToUser("001", src);
        bank.addAccountToUser("002", dst);
        boolean result =
            bank.transferMoney("003", "001001", "002", "002001", new BigDecimal("1.23"));
        assertFalse(result);
        assertThat(src.getValue(), is(new BigDecimal("3.45")));
        assertThat(dst.getValue(), is(new BigDecimal("0.00")));
    }

    @Test
    public void whenTransferMoneyDstUserNotExistsThenFalse() {
        Bank bank = new Bank();
        User one = new User("One", "001");
        User two = new User("Two", "002");
        bank.addUser(one);
        bank.addUser(two);
        Account src = new Account("001001", new BigDecimal("3.45"));
        Account dst = new Account("002001");
        bank.addAccountToUser("001", src);
        bank.addAccountToUser("002", dst);
        boolean result =
            bank.transferMoney("001", "001001", "003", "002001", new BigDecimal("1.23"));
        assertFalse(result);
        assertThat(src.getValue(), is(new BigDecimal("3.45")));
        assertThat(dst.getValue(), is(new BigDecimal("0.00")));
    }

    @Test
    public void whenTransferMoneySrcAccountNotExistsThenFalse() {
        Bank bank = new Bank();
        User one = new User("One", "001");
        User two = new User("Two", "002");
        bank.addUser(one);
        bank.addUser(two);
        Account src = new Account("001001", new BigDecimal("3.45"));
        Account dst = new Account("002001");
        bank.addAccountToUser("001", src);
        bank.addAccountToUser("002", dst);
        boolean result =
                bank.transferMoney("001", "001002", "002", "002001", new BigDecimal("1.23"));
        assertFalse(result);
        assertThat(src.getValue(), is(new BigDecimal("3.45")));
        assertThat(dst.getValue(), is(new BigDecimal("0.00")));
    }

    @Test
    public void whenTransferMoneyDstAccountNotExistsThenFalse() {
        Bank bank = new Bank();
        User one = new User("One", "001");
        User two = new User("Two", "002");
        bank.addUser(one);
        bank.addUser(two);
        Account src = new Account("001001", new BigDecimal("3.45"));
        Account dst = new Account("002001");
        bank.addAccountToUser("001", src);
        bank.addAccountToUser("002", dst);
        boolean result =
                bank.transferMoney("001", "001001", "002", "002002", new BigDecimal("1.23"));
        assertFalse(result);
        assertThat(src.getValue(), is(new BigDecimal("3.45")));
        assertThat(dst.getValue(), is(new BigDecimal("0.00")));
    }
}
