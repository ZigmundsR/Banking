import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {
    private Customer customer;
    private static Customer test;

    @BeforeAll
    public static void testing(){
        test = new Customer();
        test.addBankAccount();
        test.addBankAccount();

        assertTrue(test.getBankAccounts().size() == 2, "Pre-condition must be met before running tests");
    }

    @BeforeEach
    public void setUp() {
        customer = new Customer("Zigmunds");
        customer.addBankAccount();
        customer.addBankAccount();
    }

    @Nested
    @DisplayName("Tests for the deposit method")
    class deposit {
        @ParameterizedTest
        @DisplayName("Deposit a valid amount")
        @CsvSource({
                "0, 100, 100",  // Account 0, Deposit 100, Expected balance 100
                "1, 200, 200",  // Account 1, Deposit 200, Expected balance 200
                "0, 0, 0"       // Account 0, Deposit 0, Expected balance 0
        })
        void testDeposit(int accountID, int depositAmount, int expectedBalance) {
            customer.deposit(accountID, depositAmount);
            assertEquals(expectedBalance, customer.getBankAccounts().get(accountID).getBalance());
        }

        @ParameterizedTest
        @DisplayName("Deposit an invalid amount")
        @CsvSource({
                "0, -100",  // Account 0, Deposit -100
                "1, -50",    // Account 1, Deposit -50
                "1, 0"    // Account 1, Deposit 0
        })
        void testInvalidDeposit(int accountID, int depositAmount) {
            customer.deposit(accountID, depositAmount);
            assertEquals(0, customer.getBankAccounts().get(accountID).getBalance());
        }

        @ParameterizedTest
        @DisplayName("Deposit to invalid account")
        @CsvSource({
                "2, 100",  // Non-existent account index 2
                "-1, 50"   // Invalid account index -1
        })
        void testDepositToInvalidAccount(int accountID, int depositAmount) {
            assertThrows(IndexOutOfBoundsException.class, () -> customer.deposit(accountID, depositAmount));
        }
    }

    @Nested
    @DisplayName("Tests for the withdraw method")
    class withdraw {
        @ParameterizedTest
        @DisplayName("Withdraw a valid amount")
        @CsvSource({
                "0, 50, 10",   // Account 0, Withdraw 50, Expected remaining 60 after depositing 60
                "1, 100, 10"  // Account 1, Withdraw 100, Expected remaining 10 after depositing 110
        })
        void testWithdraw(int accountID, int withdrawAmount, int expectedBalance) {
            customer.deposit(accountID, withdrawAmount + 10);  // Ensure sufficient balance for the test
            customer.withdraw(accountID, withdrawAmount);
            assertEquals(expectedBalance, customer.getBankAccounts().get(accountID).getBalance());
        }

        @ParameterizedTest
        @DisplayName("Withdraw more than balance")
        @CsvSource({
                "0, 1000",  // Withdraw more than current balance in account 0
                "1, 500"    // Withdraw more than current balance in account 1
        })
        void testWithdrawMoreThanBalance(int accountID, int withdrawAmount) {
            customer.deposit(accountID, withdrawAmount - 10);  // Ensure insufficient balance for the test
            customer.withdraw(accountID, withdrawAmount);
            assertEquals(withdrawAmount - 10, customer.getBankAccounts().get(accountID).getBalance());
        }

        @ParameterizedTest
        @DisplayName("Withdraw to invalid account")
        @CsvSource({
                "2, 100",  // Non-existent account index 2
                "-1, 50"   // Invalid account index -1
        })
        void testWithdrawToInvalidAccount(int accountID, int withdrawAmount) {
            assertThrows(IndexOutOfBoundsException.class, () -> customer.withdraw(accountID, withdrawAmount));
        }
    }

    @Nested
    @DisplayName("Tests for the transfer method")
    class transfer {
        @ParameterizedTest
        @DisplayName("Transfer a valid amount")
        @CsvSource({
                "0, 1, 100, 100, 100",  // Transfer 100 from account 0 to 1, expected balances 100 and 100
                "1, 0, 50, 50, 50"     // Transfer 50 from account 1 to 0, expected balances 150 and 50
        })
        void testValidTransfer(int fromAccountID, int toAccountID, int transferAmount, int expectedFromBalance, int expectedToBalance) {
            customer.deposit(fromAccountID, transferAmount * 2);  // Ensure sufficient balance for the test
            customer.transfer(fromAccountID, toAccountID, transferAmount);
            assertEquals(expectedFromBalance, customer.getBankAccounts().get(fromAccountID).getBalance());
            assertEquals(expectedToBalance, customer.getBankAccounts().get(toAccountID).getBalance());
        }
        @ParameterizedTest
        @DisplayName("Transfer more than balance")
        @CsvSource({
                "1, 0, 1000, 100, 50 ",  // Transfer 1000 from account 0 to 1, expected balances 100 and 50
                "0, 1, 500, 35, 20 ",    // Transfer 500 from account 0 to 1, expected balances 35 and 20
                "0, 1, 100, 0, 0 "    // Transfer 100 from account 0 to 1, expected balances 0 and 0
        })
        void testTransferMoreThanBalance(int fromAccountID, int toAccountID, int transferAmount, int expectedFromBalance, int expectedToBalance) {
            customer.deposit(fromAccountID, expectedFromBalance);  // Ensure insufficient balance for the test
            customer.deposit(toAccountID, expectedToBalance);
            customer.transfer(fromAccountID, toAccountID, transferAmount);
            assertEquals(expectedFromBalance, customer.getBankAccounts().get(fromAccountID).getBalance());
            assertEquals(expectedToBalance, customer.getBankAccounts().get(toAccountID).getBalance());
        }
    }

}