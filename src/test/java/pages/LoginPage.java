package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utility.BaseClass;

public class LoginPage extends BaseClass {

    //declaration of locator - In page object model, it is good to use @FindBy

       // public LoginPage(WebDriver ldriver) {
     //   this.driver=ldriver;
  //  }
        @FindBy(name="username") WebElement userName;
        @FindBy(name="password") WebElement passWord;
        @FindBy(xpath="//input[@class='button']") WebElement loginbutton;

        //create method for each locator
        public void loginToApp(String uname, String pass){
            userName.sendKeys(uname);
            passWord.sendKeys(pass);
            loginbutton.click();
        }
}
