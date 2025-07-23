package com.poc.mongodb.model;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "customers")
public class Customers {
    @Id
    private Integer id;
    private String first_name;
    private String last_name;
    private String email;
    private String date_of_birth;
    private String address;
}
