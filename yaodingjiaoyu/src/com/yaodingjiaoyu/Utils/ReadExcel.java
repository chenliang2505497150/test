package com.yaodingjiaoyu.Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.yaodingjiaoyu.datebase.pojo.Campus;
import com.yaodingjiaoyu.datebase.pojo.Channel;
import com.yaodingjiaoyu.datebase.pojo.Examples;
import com.yaodingjiaoyu.datebase.pojo.Level;
import com.yaodingjiaoyu.datebase.pojo.Probability;

public class ReadExcel {
	private Logger logger;

	/**
	 * read the Excel file
	 * 
	 * @param path
	 *            the path of the Excel file
	 * @return
	 * @throws IOException
	 */
	public List<Examples> readExcel(String path) {
		try {
			if (path == null || Common.EMPTY.equals(path)) {
				return null;
			} else {
				String postfix = Util.getInstance().getPostfix(path);
				if (!Common.EMPTY.equals(postfix)) {
					if (Common.OFFICE_EXCEL_2003_POSTFIX.equals(postfix)) {
						return readXls(path);
					} else if (Common.OFFICE_EXCEL_2010_POSTFIX.equals(postfix)) {
						return readXlsx(path);
					}
				} else {
					System.out.println(path + Common.NOT_EXCEL_FILE);
					return null;
				}
			}
			return null;
		} catch (Exception e) {
			// 初始化日志
			logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:运行失败。参数path:" + path + ",MESSAGE:" + e.getMessage());
			return null;
		}
	}

	/**
	 * Read the Excel 2010
	 * 
	 * @param path
	 *            the path of the excel file
	 * @return
	 * @throws IOException
	 */
	public List<Examples> readXlsx(String path) {
		try {
			InputStream is = new FileInputStream(path);
			XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
			Examples examples = null;
			List<Examples> list = new ArrayList<Examples>();
			// Read the Sheet
			for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
				XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
				if (xssfSheet == null) {
					continue;
				}
				// Read the Row
				for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
					XSSFRow xssfRow = xssfSheet.getRow(rowNum);
					if (xssfRow != null) {
						examples = new Examples();

						String name = getHSSTextString(xssfRow, 0);
						String school = getHSSTextString(xssfRow, 1);
						String level = getHSSTextString(xssfRow, 2);
						String now_class = getHSSTextString(xssfRow, 3);
						String phone1 = getHSSTextString(xssfRow, 4);
						String phone2 = getHSSTextString(xssfRow, 5);
						String address = getHSSTextString(xssfRow, 6);
						String zhuangtai = getHSSTextString(xssfRow, 7);
						String probability = getHSSTextString(xssfRow, 8);
						String channel = getHSSTextString(xssfRow, 9);
						String campus = getHSSTextString(xssfRow, 10);

						System.out.println(name + "\t :" + phone1 + "\t :" + phone2 + "\t:" + level);

						examples.setName(name);
						examples.setSchool(school);

						if (level == "") {
							Level level_temp = new Level();
							level_temp.setPId(1);
							examples.setLevel(level_temp);
						} else {
							Level level_temp = new Level();
							level_temp.setPId(Integer.parseInt(level));
							examples.setLevel(level_temp);
						}
						if (now_class == "") {
							examples.setNowClass(0);

						} else {
							examples.setNowClass(Integer.parseInt(now_class));

						}

						examples.setPhone1(phone1);
						examples.setPhone2(phone2);
						examples.setAddress(address);
						if (zhuangtai == "") {
							examples.setZhuangtai(0);
						} else {
							examples.setZhuangtai(Integer.parseInt(zhuangtai));
						}
						if (probability == "") {
							Probability probability_temp = new Probability();
							probability_temp.setPId(1);
							examples.setProbability(probability_temp);
						} else {
							Probability probability_temp = new Probability();
							probability_temp.setPId(Integer.parseInt(probability));
							examples.setProbability(probability_temp);
						}
						if (channel == "") {
							Channel channel_temp = new Channel();
							channel_temp.setPId(1);
							examples.setChannel(channel_temp);
						} else {
							Channel channel_temp = new Channel();
							channel_temp.setPId(Integer.parseInt(channel));
							examples.setChannel(channel_temp);
						}
						if (campus == "") {
							Campus campus_temp = new Campus();
							campus_temp.setPId(1);
							examples.setCampus(campus_temp);
						} else {
							Campus campus_temp = new Campus();
							campus_temp.setPId(Integer.parseInt(campus));
							examples.setCampus(campus_temp);
						}
						examples.setYouxiao(1);
						examples.setCreatTime(new Date());
						list.add(examples);

					}
				}
			}
			return list;
		} catch (Exception e) {
			// 初始化日志
			logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:运行失败。参数path:" + path + ",MESSAGE:" + e.getMessage());
			return null;
		}
	}

	public String getHSSTextString(Row row, int colNum) {

		try {
			Cell cell = row.getCell(colNum);
			if (null != cell) {
				switch (cell.getCellType()) {
				case HSSFCell.CELL_TYPE_NUMERIC:
					DecimalFormat df = new DecimalFormat("#");// 转换成整型
					return df.format(cell.getNumericCellValue());
				// return String.valueOf(cell.getNumericCellValue());

				case HSSFCell.CELL_TYPE_STRING:
					return cell.getStringCellValue().trim();
				case HSSFCell.CELL_TYPE_BLANK: // 空值
					return "";
				case HSSFCell.CELL_TYPE_ERROR: // 故障
					return "";
				default:
					return "";
				}
			} else {
				return "";
			}
		} catch (Exception e) {
			// 初始化日志
			logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:运行失败。参数row:" + row + ",colNum:" + colNum + ",MESSAGE:"
					+ e.getMessage());
			return "";
		}
	}

	/**
	 * Read the Excel 2003-2007
	 * 
	 * @param path
	 *            the path of the Excel
	 * @return
	 * @throws IOException
	 */
	public List<Examples> readXls(String path) {
		try {
			InputStream is = new FileInputStream(path);
			HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
			Examples examples = null;
			List<Examples> list = new ArrayList<Examples>();
			// Read the Sheet
			for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
				HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
				if (hssfSheet == null) {
					continue;
				}
				// Read the Row
				for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
					HSSFRow hssfRow = hssfSheet.getRow(rowNum);
					if (hssfRow != null) {
						examples = new Examples();

						String name = getHSSTextString(hssfRow, 0);
						String school = getHSSTextString(hssfRow, 1);
						String level = getHSSTextString(hssfRow, 2);
						String now_class = getHSSTextString(hssfRow, 3);
						String phone1 = getHSSTextString(hssfRow, 4);
						String phone2 = getHSSTextString(hssfRow, 5);
						String address = getHSSTextString(hssfRow, 6);
						String zhuangtai = getHSSTextString(hssfRow, 7);
						String probability = getHSSTextString(hssfRow, 8);
						String channel = getHSSTextString(hssfRow, 9);
						String campus = getHSSTextString(hssfRow, 10);

						System.out.println(name + "\t :" + phone1 + "\t :" + phone2 + "\t:" + level);

						examples.setName(name);
						examples.setSchool(school);

						if (level == "") {
							Level level_temp = new Level();
							level_temp.setPId(1);
							examples.setLevel(level_temp);
						} else {
							Level level_temp = new Level();
							level_temp.setPId(Integer.parseInt(level));
							examples.setLevel(level_temp);
						}
						if (now_class == "") {
							examples.setNowClass(0);

						} else {
							examples.setNowClass(Integer.parseInt(now_class));

						}

						examples.setPhone1(phone1);
						examples.setPhone2(phone2);
						examples.setAddress(address);
						if (zhuangtai == "") {
							examples.setZhuangtai(0);
						} else {
							examples.setZhuangtai(Integer.parseInt(zhuangtai));
						}
						if (probability == "") {
							Probability probability_temp = new Probability();
							probability_temp.setPId(1);
							examples.setProbability(probability_temp);
						} else {
							Probability probability_temp = new Probability();
							probability_temp.setPId(Integer.parseInt(probability));
							examples.setProbability(probability_temp);
						}
						if (channel == "") {
							Channel channel_temp = new Channel();
							channel_temp.setPId(1);
							examples.setChannel(channel_temp);
						} else {
							Channel channel_temp = new Channel();
							channel_temp.setPId(Integer.parseInt(channel));
							examples.setChannel(channel_temp);
						}
						if (campus == "") {
							Campus campus_temp = new Campus();
							campus_temp.setPId(1);
							examples.setCampus(campus_temp);
						} else {
							Campus campus_temp = new Campus();
							campus_temp.setPId(Integer.parseInt(campus));
							examples.setCampus(campus_temp);
						}
						examples.setYouxiao(1);
						examples.setCreatTime(new Date());
						list.add(examples);

					}
				}
			}
			return list;
		} catch (Exception e) {
			// 初始化日志
			logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:运行失败。参数path:" + path + ",MESSAGE:" + e.getMessage());
			return null;
		}
	}

	@SuppressWarnings({ "static-access", "unused" })
	private String getValue(XSSFCell xssfRow) {
		try {
			if (xssfRow.getCellType() == xssfRow.CELL_TYPE_BOOLEAN) {
				return String.valueOf(xssfRow.getBooleanCellValue());
			} else if (xssfRow.getCellType() == xssfRow.CELL_TYPE_NUMERIC) {
				return String.valueOf(xssfRow.getNumericCellValue());
			} else {
				return String.valueOf(xssfRow.getStringCellValue());
			}
		} catch (Exception e) {
			// 初始化日志
			logger = Logger.getLogger(this.getClass());
			logger.error(
					this.getClass().getName() + "-->execute:运行失败。参数xssfRow:" + xssfRow + ",MESSAGE:" + e.getMessage());
			return null;
		}
	}

	@SuppressWarnings({ "static-access", "unused" })
	private String getValue(HSSFCell hssfCell) {
		try {
			if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
				return String.valueOf(hssfCell.getBooleanCellValue());
			} else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
				return String.valueOf(hssfCell.getNumericCellValue());
			} else {
				return String.valueOf(hssfCell.getStringCellValue());
			}
		} catch (Exception e) {
			// 初始化日志
			logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:运行失败。参数hssfCell:" + hssfCell + ",MESSAGE:"
					+ e.getMessage());
			return null;
		}
	}
}
