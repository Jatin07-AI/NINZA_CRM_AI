package iRetryImplementation;

import org.testng.Assert;
import org.testng.annotations.Test;

public class IRetryAnalyerListenerImplementation {

	@Test(retryAnalyzer = genericutility.IRetryAnalyzerImplementation.class)
	public void test() {
		Assert.assertEquals("hdfc","hfdc");
	}
}
