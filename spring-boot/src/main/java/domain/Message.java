package domain;

import java.util.Collection;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name="message")
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String text;
	private String tag;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="user_id")
	private User author;
	
	@ElementCollection(targetClass = Status.class, fetch = FetchType.EAGER)
	
	@CollectionTable(name="message_status", joinColumns =@JoinColumn(name = "message_id"))
	@Enumerated(EnumType.STRING)
	private Set<Status> statuses;
	

	public Message() {
	}
	
	
	public Message(String text, String tag, User user) {
		this.author = user;
		this.text = text;
		this.tag = tag;
	}
	
	
	public Set<Status> getStatuses() {
		return statuses;
	}


	public void setStatuses(Set<Status> statuses) {
		this.statuses = statuses;
	}


	public User getAuthor() {
		return author;
	}


	public void setAuthor(User author) {
		this.author = author;
	}

	public String getAuthorName() {
		return author != null ? author.getUsername() : "<none>";
	}
	

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return getStatuses();
	}
	
	
}
