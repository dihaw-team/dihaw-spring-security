package com.dihaw.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "USERS")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private int id;
	
	@Column(name = "FIRST_NAME")
	@NotBlank
	@Size(min = 3, max = 10)
	private String firstName;

	@Column(name = "USERNAME")
	@NotBlank
	@Size(min = 3, max = 10)
	private String username;

	@Column(name = "EMAIL")
	@NotBlank
	@Size(min = 5, max = 30)
	private String email;
	
	@Column(name = "PASSWORD")
	@NotBlank
	@Size(min = 5, max = 10)
	private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "GENDER")
    private Gender gender;
    
    @ManyToOne(optional = true)
    @JoinColumn(name = "CITY_ID")
	private City city;
    
	@Column(name = "CREATION_DATE")
	private Date creationDate;
	
	@Column(name = "LAST_CONNECTION")
	@Generated(GenerationTime.ALWAYS)
	private Date lastConnection;
	
	@Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private UserStatus status;					//status: Enabled or Disabled
    
    @Column(name = "ACCOUNT_NON_EXPIRED")
    private int accountNonExpired;				//account not expired
    
    @Column(name = "ACCOUNT_NON_LOKED")
    private int accountNonLocked;				//account non locked
    
    @Column(name = "CREDENTIALS_NON_EXPIRED")
    private int credentialsNonExpired;			//credentials non expired
    
    /**
     * role = 1 ->	ROLE_ADMIN + ROLE_USER	
     * role = 2 ->	ROLE_USER
     * role = 3 ->
     */
    
	@OneToOne(mappedBy="user", cascade={CascadeType.ALL})
	private UserRole userRole;

	/**
     * Protected constructor for ORM.
     */
	public User() {
    }
	
    public User(String firstName, String username, 
    			String email, String password,
    			Gender gender, UserStatus status,
    			City city, Date creationDate ){
    	
    	this.firstName = firstName;
    	this.username = username;
    	this.email = email;
    	this.password = password;
    	this.gender=gender;
    	this.status = status;
    	this.city=city;
    	this.creationDate=creationDate;
    	
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public Date getLastConnection() {
		return lastConnection;
	}

	public void setLastConnection(Date lastConnection) {
		this.lastConnection = lastConnection;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public int getAccountNonExpired() {
		return accountNonExpired;
	}

	public void setAccountNonExpired(int accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public int getAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(int accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public int getCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public void setCredentialsNonExpired(int credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}
	
	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole role) {
		this.userRole = role;
	}

}