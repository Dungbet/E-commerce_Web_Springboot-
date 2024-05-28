package jmaster.io.jobscheduler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jmaster.io.DTO.BillStatisticDTO;
import jmaster.io.Repository.BillRepo;

@Component
public class Jobscheduler {
	
	@Autowired
	BillRepo billRepo;
	
	public void exportExcel() throws IOException {
		
		// Create and style header row
		Workbook workbook = new XSSFWorkbook();

		Sheet sheet = workbook.createSheet("Pr3");
		sheet.setColumnWidth(0, 6000);
		sheet.setColumnWidth(1, 4000);

		Row header = sheet.createRow(0);

		CellStyle headerStyle = workbook.createCellStyle();
		headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
		headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		XSSFFont font = ((XSSFWorkbook) workbook).createFont();
		font.setFontName("Arial");
		font.setFontHeightInPoints((short) 16);
		font.setBold(true);
		headerStyle.setFont(font);

		Cell headerCell = header.createCell(0);
		headerCell.setCellValue("Quantity");
		headerCell.setCellStyle(headerStyle);

		headerCell = header.createCell(1);
		headerCell.setCellValue("Month");
		headerCell.setCellStyle(headerStyle);
		
		headerCell = header.createCell(2);
		headerCell.setCellValue("Year");
		headerCell.setCellStyle(headerStyle);
		
		// Xuất dữ liệu từ danh sách vào file Excel
		CellStyle style = workbook.createCellStyle();
		style.setWrapText(true);

		List<BillStatisticDTO> billStatisticDTOs = billRepo.thongKeBill2();
		
		
		for (int j = 0; j < billStatisticDTOs.size(); j++) {
			// Dòng thứ 3
			Row row = sheet.createRow(j+2);
			for(int i = 0 ; i < 3; i++) {
				//Tạo một ô mới trong dòng hiện tại (row) ở vị trí thứ i.
				Cell cell = row.createCell(i);
				if(i==0)cell.setCellValue(billStatisticDTOs.get(j).getQuantity());
				if(i==1)cell.setCellValue(billStatisticDTOs.get(j).getMonth());
				if(i==2)cell.setCellValue(billStatisticDTOs.get(j).getYear());
			}
		}
		
		// Luu lại
		File currDir = new File(".");
		String path = currDir.getAbsolutePath();
		String fileLocation = path.substring(0, path.length() - 1) + "temp.xlsx";

		FileOutputStream outputStream = new FileOutputStream(fileLocation);
		workbook.write(outputStream);
	}
}
