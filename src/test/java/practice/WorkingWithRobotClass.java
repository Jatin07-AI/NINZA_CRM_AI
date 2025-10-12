package practice;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WorkingWithRobotClass {

	public static void main(String[] args) throws AWTException {

		WebDriver driver=new ChromeDriver();
		driver.get("https://www.instagram.com/");
		
		Robot robot=new Robot();
		
		//Clicking on caps lock
		robot.keyPress(KeyEvent.VK_CAPS_LOCK);
		robot.keyRelease(KeyEvent.VK_CAPS_LOCK);
		
		//Click on tab to get control of username text field
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);
		
		//click on K
		robot.keyPress(KeyEvent.VK_K);
		robot.keyRelease(KeyEvent.VK_K);
		
		//click on A
		robot.keyPress(KeyEvent.VK_A);
		robot.keyRelease(KeyEvent.VK_A);
		
		//click on V
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		
		//click on Y
		robot.keyPress(KeyEvent.VK_Y);
		robot.keyRelease(KeyEvent.VK_Y);
		
		//click on A
		robot.keyPress(KeyEvent.VK_A);
		robot.keyRelease(KeyEvent.VK_A);
		
		//Copy
		//Click on control
		robot.keyPress(KeyEvent.VK_CONTROL);
		//click on A
		robot.keyPress(KeyEvent.VK_A);
		robot.keyRelease(KeyEvent.VK_A);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		
		robot.keyPress(KeyEvent.VK_CONTROL);
		//click on V
		robot.keyPress(KeyEvent.VK_C);
		robot.keyRelease(KeyEvent.VK_C);
		robot.keyRelease(KeyEvent.VK_CONTROL);
	
		
		//click on tab to get control of password text field
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);
		
		//Paste
		//Click on control
		robot.keyPress(KeyEvent.VK_CONTROL);
		//click on V
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		
	}

}
