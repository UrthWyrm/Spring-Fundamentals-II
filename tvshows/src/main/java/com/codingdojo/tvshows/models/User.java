package com.codingdojo.tvshows.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="users")
public class User {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
@Size(min = 1, message = "Name must be valid")
private String first_name;
@Size(min = 1, message = "Name must be valid")
private String last_name;
@Email(message = "Email must be valid")
private String email;
@Size(min = 8, message = "Password must be at least 8 characters")
private String password;

@Transient
private String passwordConfirmation;
@Column(updatable=false)
@DateTimeFormat(pattern="yyyy-MM-dd")
private Date createdAt;
@DateTimeFormat(pattern="yyyy-MM-dd")
private Date updatedAt;
//// Television Shows ////////////////////
@OneToMany(mappedBy = "producer", fetch = FetchType.LAZY)
private List<Show> producing;

@ManyToMany(fetch = FetchType.LAZY)
@JoinTable(
		name = "watching",
		joinColumns = @JoinColumn(name = "user_id"),
		inverseJoinColumns = @JoinColumn(name = "show_id")
		)
private List<Show> showsWatched;
///////////////// Getters & Setters ///////////////////////////////

public User() {
	
}
public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getFirst_name() {
	return first_name;
}

public void setFirst_name(String first_name) {
	this.first_name = first_name;
}

public String getLast_name() {
	return last_name;
}

public void setLast_name(String last_name) {
	this.last_name = last_name;
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

public String getPasswordConfirmation() {
	return passwordConfirmation;
}

public void setPasswordConfirmation(String passwordConfirmation) {
	this.passwordConfirmation = passwordConfirmation;
}

public Date getCreatedAt() {
	return createdAt;
}

public void setCreatedAt(Date createdAt) {
	this.createdAt = createdAt;
}

public Date getUpdatedAt() {
	return updatedAt;
}

public void setUpdatedAt(Date updatedAt) {
	this.updatedAt = updatedAt;
}

public List<Show> getProducing() {
	return producing;
}

public void setProducing(List<Show> producing) {
	this.producing = producing;
}

public List<Show> getShowsWatched() {
	return showsWatched;
}

public void setShowsWatched(List<Show> showsWatched) {
	this.showsWatched = showsWatched;
}




}



