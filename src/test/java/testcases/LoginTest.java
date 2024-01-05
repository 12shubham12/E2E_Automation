package testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import pages.LoginPage;
import utility.BaseClass;
import utility.BrowserFactory;
import utility.ExcelDataProvider;
import utility.Helper;

public class LoginTest extends BaseClass { //achieving inheritance

    @Test
    public void loginApp(){
        //Use Pagefactory class to initialize the page using initElements method
        LoginPage loginpage = PageFactory.initElements(driver, LoginPage.class);

        //From below line we are achieving abstraction, as we are showing the essential features
        //and hiding the background details
        loginpage.loginToApp(excel.getUserName("LoginDetails",1,0),
                excel.getPassword("LoginDetails",1,1));
        Helper.takeSS_URLtoExcel("./Screenshot_Folder/ST_Env/ss.png", "./qaSanity.xlsx","Login");
    }
}
