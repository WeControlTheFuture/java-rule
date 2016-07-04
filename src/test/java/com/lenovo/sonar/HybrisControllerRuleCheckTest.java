package com.lenovo.sonar;

import org.junit.Test;
import org.sonar.java.checks.verifier.JavaCheckVerifier;

import com.lenovo.sonar.rules.HybrisLayerCodingRule;

public class HybrisControllerRuleCheckTest {

	@Test
	  public void check() {
	    // Verifies that the check will raise the adequate issues with the expected message.
	    // In the test file, lines which should raise an issue have been commented out
	    // by using the following syntax: "// Noncompliant {{EXPECTED_MESSAGE}}"
	    JavaCheckVerifier.verify("src/test/files/HybrisControllerRuleCheckController.java", new HybrisLayerCodingRule());
	  }
}
