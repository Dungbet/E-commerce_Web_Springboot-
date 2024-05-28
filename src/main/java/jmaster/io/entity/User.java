package jmaster.io.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
//đối tượng của lớp con được so sánh và xác định sự duy nhất dựa trên cả thuộc tính của lớp cha.
@EqualsAndHashCode(callSuper = true)
public class User extends TimeAuditable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	
	@Column(unique = true)
	private String username;
	private String password;
	private String avatar;
	private String email;
	
	@Temporal(TemporalType.DATE)
	private Date birthdate;

	@ManyToMany(fetch = FetchType.EAGER)
	//@JoinTable: Đây là một annotation để xác định bảng trung gian, bảng trung gian có tên là "user_role".
	//joinColumns: là một phần của @JoinTable để xác định cột trong bảng trung gian liên kết với đối tượng User. 
	//cột "user_id" trong bảng trung gian sẽ liên kết với khóa chính của đối tượng User.
	//"role_id" trong bảng trung gian sẽ liên kết với khóa chính của đối tượng Role.
	@JoinTable(name = "user_role",
		joinColumns = @JoinColumn(name = "user_id"), 
		inverseJoinColumns = @JoinColumn(name = "role_id")
	)
	//private List<Role> roles: Đây là thuộc tính của đối tượng User để đại diện cho mối quan hệ Many-to-Many với các đối tượng Role
	private List<Role> roles;
	
	
	
}
