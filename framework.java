package com.webdriver;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import com.thoughtworks.selenium.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.server.*;
import org.testng.annotations.*;

public class framework {

	Sheet s, s1;
	WritableWorkbook wwb;
	Workbook w, w1;
	WritableSheet ws;
	int no_rows, no_cols, no_rows1, no_cols1, no_rows2, no_cols2, i2, j2,
			new_col;
	WebDriver driver;
	String Object_Type, fails = "false";

	@BeforeClass
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
	}

	@Test
	public void testImportexport1() throws Exception {

		// Read data from excel sheet

		FileInputStream fi = new FileInputStream("D:\\Book121.xls");
		w = Workbook.getWorkbook(fi);
		s = w.getSheet(0);
		no_rows = s.getRows();
		no_cols = s.getColumns();
		new_col = no_cols + 1;
		System.out.println(no_rows);
		System.out.println(no_cols);
		System.out.println(new_col);
		String a[][] = new String[s.getRows()][s.getColumns()];
		// Write the input data into another excel file
		FileOutputStream fo = new FileOutputStream("D:\\test121.xls");
		wwb = Workbook.createWorkbook(fo);
		ws = wwb.createSheet("loginresult1", 0);

		/*
		 * finding number of rows in excel sheet
		 */

		/*
		 * To find number of blank in column 0 of excel sheet.... this for loop
		 * executes until number of rows and we eliminating first rows because
		 * its header(int i=1)
		 */
		int j = 1;
		for (int i = 1; i < s.getRows(); i++) {
			String rows_data = s.getCell(0, i).getContents();

			if (rows_data.equals("")) {
				j++;

			}
		}
		/*
		 * find number of testcase by subracting number of blank from number of
		 * rows
		 */
		int no_testcase = no_rows - j;
		System.out.println("Number of test case" + no_testcase);

		for (int i = 0; i < s.getRows(); i++) {

			for (int j1 = 0; j1 < s.getColumns(); j1++) {
				a[i][j1] = s.getCell(j1, i).getContents();
				
				Label l = new Label(j1, i, a[i][j1]);
				Label l1 = new Label(new_col - 1, 0, "Result");
				ws.addCell(l);
				ws.addCell(l1);
			}
		}
		no_rows1 = ws.getRows();
		no_cols1 = ws.getColumns();
		System.out.println(no_rows1 + " " + no_cols1);

		/*
		 * Read rows'data from excel sheet except first rows because its
		 * header..this for loop executes until number of rows and we
		 * eliminating first rows because its header(int i=1)
		 */

		for (int i = 1; i < s.getRows(); i++) {
			/*
			 * read data from "keyword" Header only i.e from first column and
			 * storing in keyword_header string.... Syntax for reading
			 * particular data from excel sheet:
			 * "s.getCell(columnumber,rownumber)" eg s.getCell(1, i) means first
			 * column and i th row data
			 */
			if (fails.equals("true")) {
                i--;  
				String check2=s.getCell(1,i).getContents();
				
				
				while(!(check2.equals("stop")))
				{
				i++;
				check2=s.getCell(1,i).getContents();
				System.out.println(i);
				
				}
				Label l3 = new Label(new_col - 1, i, "testcasefails");
				ws.addCell(l3);
				fails = "false";
				i++;

			}
			String keyword_header = s.getCell(1, i).getContents();
			/* passing this string with its position */
			valuecheck_firstcolumn(keyword_header, i);

		}

	}

	public void valuecheck_firstcolumn(String keyword_header_1,
			int Row_position_keyword_header) throws Exception {

		if (keyword_header_1.equals("open")) {
			/*
			 * if keyword is "open",then it read corresponding content in
			 * "value" Header and stores in keyword_header_value...for reading
			 * data from "value" header then we have to use
			 * s.getCell(2,Row_position_keyword_header). getContents(); i.e 2nd
			 * column data for corresponding row position of "Keyword_header"
			 */
			String keyword_header_value = s.getCell(2,
					Row_position_keyword_header).getContents();
			String keyword_header_screenshot = s.getCell(4,
					Row_position_keyword_header).getContents();

			/*
			 * pass string to openfun() for performing "selenium.open()"
			 * function
			 */
			new OpenFun().openfun(keyword_header_value,
					keyword_header_screenshot, Row_position_keyword_header,
					driver);

		}
		if (keyword_header_1.equals("type")) {
			/*
			 * if keyword is "type",then it read corresponding content in
			 * "value" and "object" Header and stores in keyword_header_value
			 * and keyword_header_object respectively..... for reading data from
			 * "value" header then we have to use
			 * s.getCell(2,Row_position_keyword_header). getContents(); i.e 2nd
			 * column data for corresponding row position of "Keyword_header"
			 * for reading data from "object" header then we have to use
			 * s.getCell(3,Row_position_keyword_header). getContents(); i.e 3nd
			 * column data for corresponding row position of "Keyword_header"
			 */
			String keyword_header_object1 = s.getCell(2,
					Row_position_keyword_header).getContents();
			String keyword_header_value = s.getCell(3,
					Row_position_keyword_header).getContents();
			String keyword_header_screenshot = s.getCell(4,
					Row_position_keyword_header).getContents();
			if (!(keyword_header_object1.isEmpty())) {
				Object_Type = "Id";
				try {
					driver.findElements(By.id(keyword_header_object1));
					new TypeFun().typefun(keyword_header_object1,
							keyword_header_value, keyword_header_screenshot,
							Row_position_keyword_header, Object_Type, driver);
					Label l3 = new Label(new_col - 1,
							Row_position_keyword_header, "pass");
					ws.addCell(l3);

				} catch (Exception e) {
					Label l3 = new Label(new_col - 1,
							Row_position_keyword_header, "fails");
					ws.addCell(l3);
					fails = "true";

				}

			}
			String keyword_header_object2 = s.getCell(5,
					Row_position_keyword_header).getContents();
			if (!(keyword_header_object2.isEmpty())) {
				Object_Type = "Css";
				try {
					driver.findElements(By.id(keyword_header_object1));
					new TypeFun().typefun(keyword_header_object1,
							keyword_header_value, keyword_header_screenshot,
							Row_position_keyword_header, Object_Type, driver);
					Label l3 = new Label(new_col - 1,
							Row_position_keyword_header, "pass");
					ws.addCell(l3);

				} catch (Exception e) {
					Label l3 = new Label(new_col - 1,
							Row_position_keyword_header, "fails");
					ws.addCell(l3);
					fails = "true";

				}
			}
			String keyword_header_object3 = s.getCell(6,
					Row_position_keyword_header).getContents();
			if (!(keyword_header_object3.isEmpty())) {
				Object_Type = "Classname";
				new TypeFun().typefun(keyword_header_object3,
						keyword_header_value, keyword_header_screenshot,
						Row_position_keyword_header, Object_Type, driver);
			}
			String keyword_header_object4 = s.getCell(7,
					Row_position_keyword_header).getContents();
			if (!(keyword_header_object4.isEmpty())) {
				Object_Type = "Xpath";
				new TypeFun().typefun(keyword_header_object4,
						keyword_header_value, keyword_header_screenshot,
						Row_position_keyword_header, Object_Type, driver);
			}

			/*
			 * pass string to typefun() for performing "selenium.type()"
			 * function
			 */

		}
		if (keyword_header_1.equals("click")) {
			/*
			 * if keyword is "click",then it read corresponding content in
			 * "object" Header and stores in keyword_header_object for reading
			 * data from "object" header then we have to use
			 * s.getCell(3,Row_position_keyword_header). getContents(); i.e 3nd
			 * column data for corresponding row position of "Keyword_header"
			 */
			String keyword_header_object1 = s.getCell(2,
					Row_position_keyword_header).getContents();
			String keyword_header_screenshot = s.getCell(4,
					Row_position_keyword_header).getContents();
			if (!(keyword_header_object1.isEmpty())) {
				Object_Type = "Id";
				new ClickFun().clickfun(keyword_header_object1,
						keyword_header_screenshot, Row_position_keyword_header,
						Object_Type, driver);
			}
			String keyword_header_object2 = s.getCell(5,
					Row_position_keyword_header).getContents();
			if (!(keyword_header_object2.isEmpty())) {
				Object_Type = "Css";
				new ClickFun().clickfun(keyword_header_object2,
						keyword_header_screenshot, Row_position_keyword_header,
						Object_Type, driver);
			}
			String keyword_header_object3 = s.getCell(6,
					Row_position_keyword_header).getContents();
			if (!(keyword_header_object3.isEmpty())) {
				Object_Type = "Classname";
				new ClickFun().clickfun(keyword_header_object3,
						keyword_header_screenshot, Row_position_keyword_header,
						Object_Type, driver);
			}
			String keyword_header_object4 = s.getCell(7,
					Row_position_keyword_header).getContents();
			if (!(keyword_header_object4.isEmpty())) {
				Object_Type = "Xpath";
				new ClickFun().clickfun(keyword_header_object4,
						keyword_header_screenshot, Row_position_keyword_header,
						Object_Type, driver);

			}

			/*
			 * pass string to typefun() for performing "selenium.click()"
			 * function
			 */

		}
		/*
		 * if (keyword_header_1.equals("selectAndWait")) { String
		 * keyword_header_value = s.getCell(2,
		 * Row_position_keyword_header).getContents(); String
		 * keyword_header_object = s.getCell(3,
		 * Row_position_keyword_header).getContents(); String
		 * keyword_header_screenshot = s.getCell(4,
		 * Row_position_keyword_header).getContents(); //new
		 * SelectFun().selectfun
		 * (keyword_header_object,keyword_header_value,keyword_header_screenshot
		 * ,Row_position_keyword_header); }
		 */

	}

	/*
	 * public void find_testcaseid(int postion_keyword_header_1) { int j = 0;
	 * 
	 * for (int i = 1; i <= postion_keyword_header_1;
	 * postion_keyword_header_1--) { String column1_data = s.getCell(1,
	 * postion_keyword_header_1).getContents(); String column0_data =
	 * s.getCell(0, postion_keyword_header_1).getContents();
	 * 
	 * if (j <= 0) { if (!(column1_data.equals(""))) { if
	 * (!(column0_data.equals(""))) { j++;
	 * System.out.println("test case is passed" + column0_data); } } } } }
	 */

	@AfterClass
	public void tearDown() throws Exception {

		wwb.write();
		wwb.close();
		driver.quit();

	}
}

