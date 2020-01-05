package suporte;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


public class Screenshot {

	public static void print (WebDriver navegador, String arquivo){
		File screenshot = ((TakesScreenshot)navegador).getScreenshotAs(OutputType.FILE);
		try{
			FileUtils.copyFile(screenshot, new File(arquivo));
		}catch (Exception e){
			System.out.println("Erro ao copiar aquivo para pasta:" + e.getMessage());
		}
		
	}
}
