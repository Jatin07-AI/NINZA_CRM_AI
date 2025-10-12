package testNGPractice;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.testng.Assert; // TestNG Hard Assert

public class EasyAssertExamples {

    String str = null;

    // =========================
    // HARD ASSERT EXAMPLE
    // =========================
    @Test
    public void HardAssertExample() {
        System.out.println("Start Hard Assert Example");

        // 1. Assert equals - agar fail hua, test yahin stop ho jayega
        Assert.assertEquals("hdfc", "hdfc"); // Pass
        System.out.println("AssertEquals passed");

        // 2. Assert null - check if object is null
        Assert.assertNull(str); // Pass
        System.out.println("AssertNull passed");

        // 3. Assert not null - check if object is not null
        // Assert.assertNotNull(str); // Fail → Test stops here
        // System.out.println("AssertNotNull passed"); // Ye execute nahi hoga agar upar fail ho
    }

    // =========================
    // SOFT ASSERT EXAMPLE
    // =========================
    @Test
    public void SoftAssertExample() {
        System.out.println("Start Soft Assert Example");

        SoftAssert softAss = new SoftAssert();

        // 1. Soft assert equals - fail hone par bhi next line execute hogi
        softAss.assertEquals("hdfc", "hfdc123", "String comparison failed"); // Fail
        System.out.println("After SoftAssert assertEquals");

        // 2. Soft assert null
        softAss.assertNull(str, "String should be null"); // Pass
        System.out.println("After SoftAssert assertNull");

        // 3. Soft assert not null
        softAss.assertNotNull(str, "String should not be null"); // Fail
        System.out.println("After SoftAssert assertNotNull");

        // 4. Must call assertAll() at the end - ye sari failures report karega
        softAss.assertAll();
        System.out.println("This line won't execute if assertAll finds failures above");
    }

    // =========================
    // ASSERT TRUE EXAMPLE
    // =========================
    @Test
    public void AssertTrueExample() {
        System.out.println("Start AssertTrue Example");

        // Check if condition is true
        Assert.assertTrue("hdfc".equals("hdfc"), "Strings should be equal"); // Pass
        System.out.println("AssertTrue passed");

        Assert.assertTrue("hdfc".equals("hfdc"), "Strings should be equal"); // Fail → Test stops here
        System.out.println("This line won't execute if previous assertTrue fails");
    }

    // =========================
    // SOFT ASSERT FALSE EXAMPLE
    // =========================
    @Test
    public void SoftAssertFalseExample() {
        System.out.println("Start Soft AssertFalse Example");

        SoftAssert softAss = new SoftAssert();

        // Check if condition is false
        softAss.assertFalse("hdfc".equals("hfdc"), "Strings should not be equal"); // Fail
        System.out.println("After SoftAssert assertFalse");

        softAss.assertFalse("hdfc".equals("hfdc123"), "Strings should not be equal"); // Pass
        System.out.println("After SoftAssert assertFalse 2");

        // Must call assertAll to report all soft assertion failures
        softAss.assertAll();
        System.out.println("This line won't execute if assertAll finds failures above");
    }
}
