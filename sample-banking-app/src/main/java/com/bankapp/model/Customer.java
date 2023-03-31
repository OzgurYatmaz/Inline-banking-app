package com.bankapp.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.Date;
import java.util.Set;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    @Column(name = "customer_id")
    private int id;

    @Size(min=3, message = "At least 3 characters")
    private String name;

    private String email;

    @Column(name = "mobile_number")
    private String mobileNumber;

   // @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Size(min=3, message = "At least 3 characters")
    private String password;

    private String role;

    
    @Column(name = "create_date")
    private Date createDate;
    
    private double money;
    
//    @OneToMany(mappedBy = "customer")//, fetch = FetchType.EAGER) //mappedBy should be the field name in other table
////    @JsonIgnore//so that this field will be hidden in responses
//    private Set<Authority> authorities;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private Set<Account> accounts;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private Set<AccountTransactions> accountTransactions;

    public Set<AccountTransactions> getAccountTransactions() {
		return accountTransactions;
	}
    

	public void setAccountTransactions(Set<AccountTransactions> accountTransactions) {
		this.accountTransactions = accountTransactions;
	}

	
	public double getMoney() {
		return money;
	}


	public void setMoney(double money) {
		this.money = money;
	}


	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

   

    public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

//	public Set<Authority> getAuthorities() {
//		return authorities;
//	}
//
//	public void setAuthorities(Set<Authority> authorities) {
//		this.authorities = authorities;
//	}

	public Set<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}
    
    
}