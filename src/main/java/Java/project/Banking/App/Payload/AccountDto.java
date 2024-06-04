package Java.project.Banking.App.Payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountDto {
    private long id;
    private String accountHolderName ;
    private double balance ;
    private long accountNumber;
    private long mobileNumber;
}

