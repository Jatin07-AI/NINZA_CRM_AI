package testNGPractice;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class TestNG_Order {
  
  @BeforeMethod
  public void beforeMethod() {
	  System.out.println("Login");
  }

  @AfterMethod
  public void afterMethod() {
	  System.out.println("Logout");
  }

  @BeforeClass
  public void beforeClass() {
	  System.out.println("Launch the browser");
  }

  @AfterClass
  public void afterClass() {
	  System.out.println("Close the browser");
  }

  @BeforeTest
  public void beforeTest() {
	  System.out.println("Pre condition for parallel executions");
  }
  

  @AfterTest
  public void afterTest() {
	  System.out.println("Post condition for parallel execution");
  }

  @BeforeSuite
  public void beforeSuite() {
	  System.out.println("beforeSuite");
  }

  @AfterSuite
  public void afterSuite() {
	  System.out.println("afterSuite");
  }

}
