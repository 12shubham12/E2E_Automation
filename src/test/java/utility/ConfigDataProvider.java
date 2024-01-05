package utility;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigDataProvider {

    Properties pro;

    //create one constructor that will load the config file

    public ConfigDataProvider(){
        File scr = new File("./Config/Config.properties");

        try{
            FileInputStream fis = new FileInputStream(scr);
            pro = new Properties();
            pro.load(fis);
        }
        catch(Exception e){
            System.out.println("Not able to load config file"+e.getMessage());
        }
    }

    public String getBrowser(){
        return pro.getProperty("Browser");
    }
    public String getqaURL(){
        return pro.getProperty("qaURL");
    }
}
