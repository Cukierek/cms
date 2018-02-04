package pl.bottega.cms.model;

import javax.persistence.*;

@Embeddable
@Table(name = "customers")
public class Customer {


    private String firstName;
    private String lastName;
    private String email;
    private String phone;


}
