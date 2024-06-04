package Java.project.Banking.App.Controller;
import Java.project.Banking.App.Payload.AccountDto;
import Java.project.Banking.App.Service.Accountservice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Accounts")
public class AccountController {
    public Accountservice accountservice;

    public AccountController(Accountservice accountservice) {
        this.accountservice = accountservice;
    }

    @PostMapping
    public ResponseEntity<AccountDto> CreateAccount(@RequestBody AccountDto accountDto) {
        AccountDto dto = accountservice.CreateAccount(accountDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getaccountById(@PathVariable Long id) {
        AccountDto dto = accountservice.getaccountById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> deposit(@PathVariable Long id,
                                              @RequestBody Map<String, Double> request) {
        Double amount = request.get("amount");
        AccountDto accountDto = accountservice.deposit(id, amount);
        return new ResponseEntity<>(accountDto, HttpStatus.OK);
    }
      @PutMapping("{id}/withdraw")
    public ResponseEntity<AccountDto> withdraw(@PathVariable Long id,
                                               @RequestBody Map<String, Double> request) {
        double amount = request.get("amount");
        AccountDto accountDto = accountservice.withdraw(id, amount);
        return new ResponseEntity<>(accountDto, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<AccountDto>>getAllAccount(){
        List<AccountDto>accounts =accountservice.getAllAccounts();
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String>deleteAccount(@PathVariable Long id){
        accountservice.deleteAccount(id);
        return ResponseEntity.ok("Account is deleted Successfully !");
    }
}