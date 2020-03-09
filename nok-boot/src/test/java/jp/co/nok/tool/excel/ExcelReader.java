package jp.co.nok.tool.excel;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import jp.co.nok.common.log.Logger;
import jp.co.nok.common.log.LoggerFactory;
import jp.co.nok.tool.excel.type.CellPositionType;
import jp.co.nok.tool.gen.ToolProperty;

/**
 * 自動生成ツールExcelの読込を行うクラス
 *
 * @version 1.0.0
 */
public class ExcelReader {

	/** LOG */
	private static final Logger LOG = LoggerFactory.getLogger(ExcelReader.class);

	/**
	 * 指定された自動生成ツール設定ファイルからExcelの読込を行う
	 *
	 * @param prop
	 *            自動生成ツール設定ファイル
	 * @return Excel
	 */
	public Excel read(ToolProperty prop) {

		Iterator<org.apache.poi.ss.usermodel.Sheet> sheetIte;
		try (Workbook wb = WorkbookFactory.create(new File(prop.getExcelPath()))) {
			sheetIte = wb.sheetIterator();
		} catch (EncryptedDocumentException | IOException e) {
			LOG.error("excelファイル読込エラー", e);
			return null;
		}

		Excel excel = new Excel();

		// シート毎の処理
		while (sheetIte.hasNext()) {
			Sheet excelSheet = new Sheet();
			org.apache.poi.ss.usermodel.Sheet sheet = sheetIte.next();

			if (!"TABLE_LIST".equals(sheet.getSheetName())) {
				// ”TABLE_LIST” シートでない場合、次のシートへ
				break;
			}

			excelSheet.setName(sheet.getSheetName());
			Iterator<org.apache.poi.ss.usermodel.Row> rowIte = sheet.iterator();

			// 行毎の処理
			while (rowIte.hasNext()) {
				Row excelRow = new Row();
				org.apache.poi.ss.usermodel.Row row = rowIte.next();
				Arrays.asList(CellPositionType.class.getEnumConstants()).stream()
						.forEach(e -> {
							String cellValue = row.getCell(e.getPosition())
									.getStringCellValue();
							Cell cell = new Cell(cellValue);
							excelRow.addCell(cell);
						});
				excelSheet.addRow(excelRow);
			}
			excel.addSheet(excelSheet);
		}

		// 現在のシートをTABLE_LISTに設定
		excel.activeSheet("TABLE_LIST");
		return excel;
	}

}
