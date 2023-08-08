package com.mangoservice.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document("trx_users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBean {
    @Id
    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private Date createOn = new Date();
    private int status = 1;
}
