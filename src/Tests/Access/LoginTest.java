package Tests.Access;
 
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Core.Generators.GeneratorUsers;
import Core.Pages.Page;
import Core.System.Browser;
import Tests.BaseTest;
 
public class LoginTest extends BaseTest {
   @BeforeTest
   public void setUp() throws Exception {
	   Browser.getInstance().goTo("https://www.tutorialspoint.com/selenium/practice/login.php");
   }
   
   @Test(description = "Test Login")
   public void logInTestCase() {
	   Page.LogInPage().logIn(GeneratorUsers.GenerateAdministrator());
       Assert.assertEquals(2, 2);
   }
   
   @AfterTest
   public void closeBrowser() {
       Browser.getInstance().close();
   }
}
