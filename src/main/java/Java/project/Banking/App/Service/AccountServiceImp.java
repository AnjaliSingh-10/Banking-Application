package Java.project.Banking.App.Service;

import Java.project.Banking.App.Entity.Account;
import Java.project.Banking.App.Payload.AccountDto;
import Java.project.Banking.App.Repository.AccountRepository;
import jakarta.persistence.Id;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImp implements Accountservice {

    private AccountRepository accountrepository;
    private  ModelMapper mapper;

    @Autowired
    public AccountServiceImp(AccountRepository accountrepository, ModelMapper mapper) {
        this.accountrepository = accountrepository;
        this.mapper = mapper;
    }

    @Override
    public AccountDto CreateAccount(AccountDto accountDto) {
        Account account = mapper.map(accountDto, Account.class);
        Account savedAccount = accountrepository.save(account);
        return mapper.map(savedAccount, AccountDto.class);
    }

    @Override
    public AccountDto getaccountById(Long id) {
       Account  account = accountrepository.findById(id).orElseThrow (()-> new RuntimeException("Account does not exist"));
        return mapper.map(account, AccountDto.class);
    }

    @Override
    public AccountDto deposit(Long id, double amount) {
        Account  account = accountrepository.
                findById(id).
                orElseThrow (()-> new RuntimeException("Account does not exist"));
                double total = account.getBalance() +amount;
                account.setBalance(total);
              Account savedaccount  = accountrepository.save(account);
             return mapper.map(savedaccount, AccountDto.class);
    }

    @Override
    public AccountDto withdraw(Long id, double amount) {
        Account  account = accountrepository.
                findById(id).
                orElseThrow (()-> new RuntimeException("Account does not exist"));
        if(account.getBalance()< amount){
            throw new RuntimeException("Insufficient amount");
        }
        double total =account.getAccountNumber() -amount;
        account.setBalance(total);
        Account savedAccount = accountrepository.save(account);
        return mapper.map(savedAccount, AccountDto.class);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
      List<Account>accounts = accountrepository.findAll();
        return accounts.stream().map((account)->mapper.map( account, AccountDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(Long id) {
        Account  account = accountrepository.
                findById(id).
                orElseThrow (()-> new RuntimeException("Account does not exist"));
        accountrepository.deleteById(id);
    }

    Account mapToEntity(AccountDto accountDto) {
        return mapper.map(accountDto, Account.class);
    }

     AccountDto mapToDto(Account account) {
        return mapper.map(account, AccountDto.class);
    }


}
