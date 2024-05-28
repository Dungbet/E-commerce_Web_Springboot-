package jmaster.io.entity;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass //được sử dụng để đánh dấu một lớp cha abstract trong JPA.
@EntityListeners(AuditingEntityListener.class)
public abstract class TimeAuditable {

	@CreatedDate
	@Column(updatable = false)
	private Date createdAt;
	
	@LastModifiedDate
	private Date updateAt;
}
