package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

	@LocalServerPort
	private int port;

	private WebDriver driver;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	@Test
	public void getLoginPage() {
		driver.get("http://localhost:" + this.port + "/login");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	public void unauthorizedHomePageNavigateToLoginPage() {
		driver.get("http://localhost:" + this.port + "/home.html");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	public void testSignUpSuccess(){
		getLoginPage();
		driver.manage().window().maximize();
		WebElement signupLink = driver.findElement(By.id("sign-up"));
		signupLink.click();
		Assertions.assertEquals("Sign Up", driver.getTitle());
		WebElement firstName = driver.findElement(By.id("inputFirstName"));
		firstName.sendKeys("sunil");
		WebElement lastName= driver.findElement(By.id("inputLastName"));
		lastName.sendKeys("babu");
		WebElement username= driver.findElement(By.id("inputUsername"));
		username.sendKeys("sunil");
		WebElement password=driver.findElement(By.id("inputPassword"));
		password.sendKeys("babu");
		WebElement submitButton=driver.findElement(By.id("signup-button"));
		submitButton.click();
		WebElement successDiv =driver.findElement(By.id("success"));
		Assertions.assertEquals("You successfully signed up! Please continue to the login page.",successDiv.getText());
		WebElement loginLink =driver.findElement(By.id("login"));
		loginLink.click();
		WebElement usernameLogin = driver.findElement(By.name("username"));
		usernameLogin.sendKeys("sunil");
		WebElement passwordLogin = driver.findElement(By.name("password"));
		passwordLogin.sendKeys("babu");
		WebElement login = driver.findElement(By.id("loginButton"));
		login.click();
		Assertions.assertEquals("Home", driver.getTitle());
	}

	private void Login(String fName, String lName, String uName, String pass){
			getLoginPage();
			driver.manage().window().maximize();
			WebElement signupLink = driver.findElement(By.id("sign-up"));
			signupLink.click();
			Assertions.assertEquals("Sign Up", driver.getTitle());
			WebElement firstName = driver.findElement(By.id("inputFirstName"));
			firstName.sendKeys(fName);
			WebElement lastName= driver.findElement(By.id("inputLastName"));
			lastName.sendKeys(lName);
			WebElement username= driver.findElement(By.id("inputUsername"));
			username.sendKeys(uName);
			WebElement password=driver.findElement(By.id("inputPassword"));
			password.sendKeys(pass);
			WebElement submitButton=driver.findElement(By.id("signup-button"));
			submitButton.click();
			WebElement successDiv =driver.findElement(By.id("success"));
			Assertions.assertEquals("You successfully signed up! Please continue to the login page.",successDiv.getText());
			WebElement loginLink =driver.findElement(By.id("login"));
			loginLink.click();
			WebElement usernameLogin = driver.findElement(By.name("username"));
			usernameLogin.sendKeys(uName);
			WebElement passwordLogin = driver.findElement(By.name("password"));
			passwordLogin.sendKeys(pass);
			WebElement login = driver.findElement(By.id("loginButton"));
			login.click();
			Assertions.assertEquals("Home", driver.getTitle());

	}

	@Test
	public void testSignUpAndLogoutSuccess(){
		getLoginPage();
		driver.manage().window().maximize();
		Login("abc","abc","abc","abc");
		WebElement logoutLink = driver.findElement(By.id("logout"));
		logoutLink.click();
		Assertions.assertEquals("Login", driver.getTitle());
		unauthorizedHomePageNavigateToLoginPage();
	}

	@Test
	public void testNoteCreation()throws InterruptedException {
		testNoteCreationParams("bcd","bcd","bcd","bcd");
	}

	private void testNoteCreationParams(String fName,String lName, String uName, String pass) {
		Login(fName,lName,uName,pass);
		WebDriverWait wait = new WebDriverWait (driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("nav-notes-tab")))).click();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("Add-note")))).click();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("note-title"))));
		WebElement noteTitle = driver.findElement(By.id("note-title"));
		noteTitle.sendKeys("sunil-Babu");
		WebElement noteDescription = driver.findElement(By.id("note-description"));
		noteDescription.sendKeys("Sunil-babu");
		noteDescription.click();
		WebElement noteSubmit = driver.findElement(By.id("submitNote"));
		noteSubmit.click();
		WebElement successDiv =driver.findElement(By.id("successMessage"));
		Assertions.assertEquals("Your changes were successfully saved. Click here to continue.",successDiv.getText());
		WebElement navigateHome = driver.findElement(By.id("navigate-home"));
		navigateHome.click();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("nav-notes-tab")))).click();
		Assertions.assertTrue(driver.getPageSource().contains("sunil-Babu"));
	}
	@Test
	public void testNoteEdit() throws InterruptedException {
		testNoteCreationParams("fgh","fgh","fgh","fgh");
		WebDriverWait wait = new WebDriverWait (driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("editButtonNote")))).click();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("note-title"))));
		WebElement noteTitle = driver.findElement(By.id("note-title"));
		noteTitle.clear();
		noteTitle.sendKeys("sunil-Merlin");
		WebElement noteSubmit = driver.findElement(By.id("submitNote"));
		noteSubmit.click();
		WebElement successDiv =driver.findElement(By.id("successMessage"));
		Assertions.assertEquals("Your changes were successfully saved. Click here to continue.",successDiv.getText());
		WebElement navigateHome = driver.findElement(By.id("navigate-home"));
		navigateHome.click();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("nav-notes-tab")))).click();
		Assertions.assertTrue(driver.getPageSource().contains("sunil-Merlin"));

	}

	@Test
	public void testNoteDelete() throws InterruptedException {
		testNoteCreationParams("hij","hij","hij","hij");
		WebDriverWait wait = new WebDriverWait (driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("deleteButtonNote")))).click();
		WebElement successDiv =driver.findElement(By.id("successMessage"));
		Assertions.assertEquals("Your changes were successfully saved. Click here to continue.",successDiv.getText());
		WebElement navigateHome = driver.findElement(By.id("navigate-home"));
		navigateHome.click();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("nav-notes-tab")))).click();
		Assertions.assertFalse(driver.getPageSource().contains("sunil-Babu"));

	}

	@Test
	public void testCredentialsCreation() throws InterruptedException {
		testCredentialCreationParams("def","def","def","def");

	}

	private void testCredentialCreationParams(String fName,String lName,String uName, String pass){
		Login(fName,lName,uName,pass);
		WebDriverWait wait = new WebDriverWait (driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("nav-credentials-tab")))).click();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("Add-credential")))).click();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("credential-url"))));
		WebElement credUrl = driver.findElement(By.id("credential-url"));
		credUrl.sendKeys("sunilbabu");
		WebElement credUsername = driver.findElement(By.id("credential-username"));
		credUsername.sendKeys("sunilbabu");
		WebElement credPassword = driver.findElement(By.id("credential-password"));
		credPassword.sendKeys("sunilbabuPassword");
		WebElement credentialSubmit = driver.findElement(By.id("submitCredential"));
		credentialSubmit.click();
		WebElement successDiv =driver.findElement(By.id("successMessage"));
		Assertions.assertEquals("Your changes were successfully saved. Click here to continue.",successDiv.getText());
		WebElement navigateHome = driver.findElement(By.id("navigate-home"));
		navigateHome.click();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("nav-credentials-tab")))).click();
		Assertions.assertTrue(driver.getPageSource().contains("sunilbabu"));
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("Add-credential")))).click();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("credential-url"))));
		WebElement credUrl1 = driver.findElement(By.id("credential-url"));
		credUrl1.sendKeys("orange");
		WebElement credUsername1 = driver.findElement(By.id("credential-username"));
		credUsername1.sendKeys("orange");
		WebElement credPassword1 = driver.findElement(By.id("credential-password"));
		credPassword1.sendKeys("orangePassword");
		WebElement credentialSubmit1 = driver.findElement(By.id("submitCredential"));
		credentialSubmit1.click();
		WebElement successDiv1 =driver.findElement(By.id("successMessage"));
		Assertions.assertEquals("Your changes were successfully saved. Click here to continue.",successDiv1.getText());
		WebElement navigateHome1 = driver.findElement(By.id("navigate-home"));
		navigateHome1.click();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("nav-credentials-tab")))).click();
		Assertions.assertTrue(driver.getPageSource().contains("orange"));
	}
	@Test
	public void testCredentialsEdit() throws InterruptedException {
		testCredentialCreationParams("jkl","jkl","jkl","jkl");
		WebDriverWait wait = new WebDriverWait (driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("editButtonCredential")))).click();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("credential-url"))));
		WebElement credUrl = driver.findElement(By.id("credential-url"));
		credUrl.clear();
		credUrl.sendKeys("appleSunil");
		WebElement credentialSubmit = driver.findElement(By.id("submitCredential"));
		credentialSubmit.click();
		WebElement successDiv =driver.findElement(By.id("successMessage"));
		Assertions.assertEquals("Your changes were successfully saved. Click here to continue.",successDiv.getText());
		WebElement navigateHome = driver.findElement(By.id("navigate-home"));
		navigateHome.click();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("nav-credentials-tab")))).click();
		Assertions.assertTrue(driver.getPageSource().contains("appleSunil"));

	}

	@Test
	public void testCredentialsDelete() throws InterruptedException {
		testCredentialCreationParams("lmn","lmn","lmn","lmn");
		WebDriverWait wait = new WebDriverWait (driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("deleteButtonCredential")))).click();
		WebElement successDiv =driver.findElement(By.id("successMessage"));
		Assertions.assertEquals("Your changes were successfully saved. Click here to continue.",successDiv.getText());
		WebElement navigateHome = driver.findElement(By.id("navigate-home"));
		navigateHome.click();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("nav-credentials-tab")))).click();
		Assertions.assertFalse(driver.getPageSource().contains("sunilbabu"));

	}

}
