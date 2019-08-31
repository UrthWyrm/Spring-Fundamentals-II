package com.codingdojo.beltReviewer.models;

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
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Long id;
private String first_name;
private String last_name;
@Email(message="Email must be valid")
private String email;
@Size(min=5, message="Password must be greater than 5 characters")
private String password;
private String location;
private String state;


@Transient
private String passwordConfirmation;
@Column(updatable=false)
@DateTimeFormat(pattern="yyyy-MM-dd")
private Date createdAt;
@DateTimeFormat(pattern="yyyy-MM-dd")
private Date updatedAt;
//events
@OneToMany(mappedBy="host", fetch = FetchType.LAZY)
private List<Event> hosting;

@ManyToMany(fetch = FetchType.LAZY)
@JoinTable(
    name = "attending", 
    joinColumns = @JoinColumn(name = "user_id"), 
    inverseJoinColumns = @JoinColumn(name = "event_id")
    )
private List<Event> eventsJoined;
//messages
@OneToMany(mappedBy="user", fetch = FetchType.LAZY)
private List<Message> messages;

//constructors
public User() {
	this.createdAt = new Date();
	this.updatedAt = new Date();
	
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

public String getLocation() {
	return location;
}

public void setLocation(String location) {
	this.location = location;
}



public String getState() {
	return state;
}

public void setState(String state) {
	this.state = state;
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

public List<Event> getHosting() {
	return hosting;
}

public void setHosting(List<Event> hosting) {
	this.hosting = hosting;
}

public List<Event> getEventsJoined() {
	return eventsJoined;
}

public void setEventsJoined(List<Event> eventsJoined) {
	this.eventsJoined = eventsJoined;
}

public List<Message> getMessages() {
	return messages;
}

public void setMessages(List<Message> messages) {
	this.messages = messages;
}


}
