package fr.xhackax47.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import fr.xhackax47.validator.Price;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name="videogames")
public class VideoGame {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Price
	@Column(name="price")
	private Integer price;
	@Column(name="platform")
	private String platform;
	@Column(name="name")
	@NotNull
	private String name;
	@Column(name="genre")
	private String genre;
	
	public VideoGame() {
		
	}

	public VideoGame(Integer price, String platform, String name, String genre) {
		super();
		this.price = price;
		this.platform = platform;
		this.name = name;
		this.genre = genre;
	}
	
	public VideoGame(Long id,Integer price, String platform, String name, String genre) {
		super();
		this.price = price;
		this.platform = platform;
		this.name = name;
		this.genre = genre;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	@Override
	public String toString() {
		return "VideoGame [id=" + id + ", price=" + price + ", platform=" + platform + ", name=" + name + ", genre="
				+ genre + "]";
	}

}
