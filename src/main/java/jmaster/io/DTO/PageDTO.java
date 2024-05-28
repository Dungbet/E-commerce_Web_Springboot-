package jmaster.io.DTO;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PageDTO<T> {

	private int totalPages;
	private long totalElements;
	private List<T> data;
}
