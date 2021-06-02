package com.lenovo.sonar;

import java.util.List;

import org.sonar.plugins.java.api.JavaCheck;

import com.google.common.collect.ImmutableList;
import com.lenovo.sonar.rules.EmptyStatementAfterIf;

/**
 * @author bixy2
 *
 */
public final class RulesList {

	private RulesList() {
	}

	public static List<Class<?>> getChecks() {
		return ImmutableList.<Class<?>> builder().addAll(getJavaChecks()).addAll(getJavaTestChecks()).build();
	}

	public static List<Class<? extends JavaCheck>> getJavaChecks() {
		return ImmutableList.<Class<? extends JavaCheck>> builder()
				.add(EmptyStatementAfterIf.class).build();
	}

	public static List<Class<? extends JavaCheck>> getJavaTestChecks() {
		return ImmutableList.<Class<? extends JavaCheck>> builder().build();
	}
}
