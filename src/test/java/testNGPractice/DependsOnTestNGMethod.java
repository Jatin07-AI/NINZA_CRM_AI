package testNGPractice;

import org.testng.annotations.Test;

public class DependsOnTestNGMethod {

	@Test(dependsOnMethods = {"Register","OpenApplication"})
	public void login() {
		System.out.println("Login Done");
	}
	
	@Test(dependsOnMethods = {"OpenApplication"})
	public void Register() {
		System.out.println("Register Done");
	}
	
	@Test
	public void OpenApplication() {
		System.out.println("Application Open");
	}
}
