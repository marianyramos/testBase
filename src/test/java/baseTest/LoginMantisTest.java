package baseTest;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import suporte.Generator;
import suporte.Screenshot;

@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths ="LoginMantisTest.csv")


public class LoginMantisTest {
	private WebDriver navegador;
	
	@Rule
	public TestName testes =new TestName();
	
	@Before
	public void setUp(){
		navegador = new ChromeDriver();
		navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		navegador.get("http://mantis-prova.base2.com.br");
		
	}
	@Test
	public void validarLoginIncorretoMantis(@Param (name="username")String login, @Param (name="password")String senha) {
	
		
		navegador.findElement(By.name("username")).sendKeys(login);
		navegador.findElement(By.name("password")).sendKeys(senha);
		navegador.findElement(By.className("button")).click();
	
	
		String textoElement = navegador.findElement(By.xpath("html/body/div/font[text()=\"Your account may be disabled or blocked or the username/password you entered is incorrect.\"]")).getText();
        assertEquals("Your account may be disabled or blocked or the username/password you entered is incorrect.", textoElement);
		
		Screenshot.print(navegador, "C:\\Users\\Mariany Ramos\\Documents\\EvidenciaTestes" + Generator.dataHora() + testes.getMethodName() +".png" );
		
	}

	@Test
	public void validarLoginCorretoMantis(){
		
		
		navegador.findElement(By.name("username")).sendKeys("mariany.ramos");
		navegador.findElement(By.name("password")).sendKeys("04101990");
		navegador.findElement(By.className("button")).click();
		
		WebElement small = navegador.findElement(By.className("small"));
		String textoElementoSmall = small.getText();
		assertEquals ("(mariany ramos - reporter)",textoElementoSmall);
		
		Screenshot.print(navegador, "C:\\Users\\Mariany Ramos\\Documents\\EvidenciaTestes" + Generator.dataHora() + testes.getMethodName() +".png" );
	}
	@After
	public void tearDown (){
		navegador.close();
	}
	
	

}
