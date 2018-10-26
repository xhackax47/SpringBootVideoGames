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
@Table(name = "roles")
public class Role {

	@Id
	@Column(name = "id")
	@GeneratedValue
	private Long id;

	@Column(name = "role")
	private String role;

	@ManyToMany(mappedBy = "role")
	private Collection<User> users;

	@ManyToMany
    @JoinTable(
        name = "roles_privileges", 
        joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}, 
        inverseJoinColumns = {@JoinColumn(name = "privilege_id", referencedColumnName = "id")})
    private Collection<Privilege> privileges;

	public Role() {
		super();
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return this.role;
	}

	public void setLogin(String role) {
		this.role = role;
	}

}
