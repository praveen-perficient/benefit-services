package com.POC.ProducerDataBase.Model;

/*public class Customer {
}*/
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("customers")
public class Customer {
    @Id
    private Integer id;
    private String first_name;
    private String last_name;
    private String email;
    private String date_of_birth;
    private String address;
}

