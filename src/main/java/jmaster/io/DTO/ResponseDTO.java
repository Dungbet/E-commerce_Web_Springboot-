package jmaster.io.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//Cho cac respon tra ve dang JSON

@Data
@Builder // Sử dụng cái này khi tao hàm chỉ cần chấm không cần new
@NoArgsConstructor // @NoArgsConstructor trong Java được sử dụng để tạo ra một constructor không có tham số,
@AllArgsConstructor //trong Java được sử dụng để tạo ra một constructor chứa tất cả các tham số của lớp
public class ResponseDTO<T> {

	private int status; //200/300/500
	private String msg;
	
	// Khi xét dữ  không null, null sẽ không trả về sẽ có respon trả về 
	@JsonInclude(Include.NON_NULL)
	private T data;

	public ResponseDTO(int status, String msg) {
		super();
		this.status = status;
		this.msg = msg;
	}
	
	
	
}
