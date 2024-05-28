package jmaster.io.DTO;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDTO {
	private int id;
	@NotBlank
	private String name;
	private String username;	
	private String password;
	private String avatar;
	private String email;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm", timezone = "Asia/Ho_Chi_Minh") // Trường hợp đẩy lên hoặc trả về json
	@DateTimeFormat(pattern = "dd/MM/yyyy") // Sử dụng khi form data
	private Date birthdate;
	
	@JsonFormat(pattern = "dd/MM/yyyy", timezone = "Asia/Ho_Chi_Minh")
	private Date createdAt;
	private List<RoleDTO> roles;
	
	@JsonIgnore // Bỏ qua file
	private MultipartFile file;
}
