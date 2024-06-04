package Java.project.Banking.App.Service;


import Java.project.Banking.App.Payload.AccountDto;

import java.util.List;

public interface Accountservice {

    AccountDto CreateAccount(AccountDto accountDto);

    AccountDto getaccountById(Long id);

    AccountDto deposit(Long id, double amount);

    AccountDto withdraw(Long id, double amount);

    List<AccountDto> getAllAccounts();

    void deleteAccount(Long id);

}

   