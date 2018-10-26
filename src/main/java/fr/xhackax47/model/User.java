package fr.xhackax47.model;


import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "users")
public class User {

	@Id
	@Column(name = "id")
	@GeneratedValue
	private Integer id;

	@Column(name = "login")
	private String login;

	@Column(name = "password")
	private String password;
	
    @ManyToMany
    @JoinTable( 
        name = "users_role", 
        joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")}, 
        inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private Collection<Role> role;

	public User() {
		super();
	}
	
	

	public User(Integer id, String login, String password, Collection<Role> role) {
		super();
		this.id = id;
		this.login = login;
		this.password = password;
		this.role = role;
	}



	public int getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
