package com.lenovo.sonar.rules;

import org.sonar.check.Rule;
import org.sonar.plugins.java.api.JavaFileScanner;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.BaseTreeVisitor;
import org.sonar.plugins.java.api.tree.IfStatementTree;
import org.sonar.plugins.java.api.tree.Tree.Kind;

@Rule(key = "EmptyStatementAfterIf", name = "Empty statement after if rule", description = "Empty statement should not appear after if statement", tags = {
		"li-checklist" })
public class EmptyStatementAfterIf extends BaseTreeVisitor implements JavaFileScanner {
	private JavaFileScannerContext context;

	@Override
	public void scanFile(JavaFileScannerContext context) {
		this.context = context;
		scan(context.getTree());
	}

	@Override
	public void visitIfStatement(IfStatementTree tree) {
		if (tree.thenStatement().kind().equals(Kind.EMPTY_STATEMENT))
			context.reportIssue(this, tree, "If Statement should not follow with empty statement directly");
		super.visitIfStatement(tree);
	}

}
