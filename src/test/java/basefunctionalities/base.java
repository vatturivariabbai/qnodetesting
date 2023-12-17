package basefunctionalities;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.microsoft.schemas.office.visio.x2012.main.CellType;

public class base {
	
	private static final CellType STRING = null;
	private static final CellType NUMERIC = null;
	private static final CellType BOOLEAN = null;
	WebDriver driver;
	public static ExtentReports extentreports;
	public static ExtentTest extentTest;
	public Properties prop;
	
	public base() {
		prop = new Properties();
		File propfile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\configfile\\config.properties");
		
		try {
		FileInputStream fis = new FileInputStream(propfile);
		prop.load(fis);
	} catch(Throwable e) {
		e.printStackTrace();
	}
		
	}
	
	public WebDriver browserinitate(String browser) {
		if (browser.equals("chrome")) {
		    driver = new ChromeDriver();
		}
		else if(browser.equals("firefox")) {
			driver = new FirefoxDriver();
		}
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
		return driver;
	}
	
	public static String generateemailwithtimestamp() {
		Date date = new Date();
		String stringdate = date.toString().replace(" ","_").replace(":","_");
		String randomemail = "demouseruser1"+stringdate+"@gmail.com";
		return randomemail;
	}

	public static Object[][] getTestDataFromExcelSheet(String sheetName) {
		//XSSFWorkbook workbook = new XSSFWorkbook();
		File excelFile = new File(System.getProperty("user.dir"+"\\src\\main\\java\\com\\configfile\\testdata.xlsx"));
		XSSFWorkbook workbook = null;
		try {
			FileInputStream fisExcel = new FileInputStream(excelFile);
			workbook = new XSSFWorkbook(fisExcel);

		} catch (Throwable e) {
			e.printStackTrace();
		}
		XSSFSheet sheet = workbook.getSheet(sheetName);

		// find no. of rows and columns
		// Find out how much rows in excel sheet

		int rows = sheet.getLastRowNum();
		int cols = sheet.getRow(0).getLastCellNum();

		Object[][] data = new Object[rows][cols];

		for (int i = 0; i < rows; i++) {

			XSSFRow row = sheet.getRow(i + 1);

			for (int j = 0; j < cols; j++) {

				XSSFCell cell = row.getCell(j);
				org.apache.poi.ss.usermodel.CellType cellType = cell.getCellType();

				switch (cellType) {

				case STRING:
					data[i][j] = cell.getStringCellValue();
					break;
				case NUMERIC:
					data[i][j] = Integer.toString((int) cell.getNumericCellValue());
					break;
				case BOOLEAN:
					data[i][j] = cell.getBooleanCellValue();
					break;
					}
				}
            }
		return data;
      }

	// Methods for extent reporting
	
	@BeforeTest
	public void getnameMethod(ITestContext context) {
		extentTest = extentreports.createTest(context.getName());
	}
	
	@BeforeSuite
	public void InitExentReport() {
		ExtentSparkReporter sparkreporter = new ExtentSparkReporter("testreport_extent.html");
		extentreports = new ExtentReports();
		extentreports.attachReporter(sparkreporter);
		
		extentreports.setSystemInfo("Operating Sytsem : ", System.getProperty("os.name"));
		extentreports.setSystemInfo("JAVA Version : ", System.getProperty("java.version"));
	}
	
	// To generate extent reports
	
	@AfterSuite
	public void generateReports() throws IOException{
		extentreports.flush();
		Desktop.getDesktop().browse(new File("testreport_extent.html").toURI());
	}
	
	@AfterMethod
	public void generateStatus(Method m, ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {

			System.out.println("Caputre the screenshot on failure");
			extentTest.fail(result.getThrowable());

		} else if (result.getStatus() == ITestResult.SUCCESS) {

			extentTest.pass(m.getName() + "  is passed");
		}

	}

}

