package com.lenovo.sonar.rules;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.JavaFileScanner;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.BaseTreeVisitor;
import org.sonar.plugins.java.api.tree.ClassTree;
import org.sonar.plugins.java.api.tree.VariableTree;
import org.sonar.squidbridge.annotations.SqaleConstantRemediation;
import org.sonar.squidbridge.annotations.SqaleSubCharacteristic;

/**
 * @author bixy2
 *
 */
@Rule(key = "LayerCodingRule", name = "layer coding rule", description = "Calls can only happened on this layer or next layer", tags = { "example" })
@SqaleSubCharacteristic(RulesDefinition.SubCharacteristics.ARCHITECTURE_CHANGEABILITY)
@SqaleConstantRemediation("10min")
public class HybrisLayerCodingRule extends BaseTreeVisitor implements JavaFileScanner {
	private JavaFileScannerContext context;
	private List<String> layerChain = new LinkedList<>(Arrays.asList("CONTROLLER", "FACADE", "SERVICE", "DAO"));
	private List<String> cannotHave;
	private String clsName;
	private int layerIndex = -1;

	@Override
	public void scanFile(JavaFileScannerContext context) {
		this.context = context;
		scan(context.getTree());
	}

	@Override
	public void visitClass(ClassTree tree) {
		String classname = null;
		if (tree.simpleName() != null)
			classname = tree.simpleName().name();
		if (classname != null)
			for (int i = 0; i < layerChain.size(); i++) {
				if (classname.toUpperCase().contains(layerChain.get(i))) {
					this.clsName = classname;
					this.layerIndex = i;
					break;
				}
			}
		cannotHave = new ArrayList<>();
		for (int i = 0; i < layerChain.size(); i++) {
			if (i < layerIndex || i > layerIndex + 1)
				cannotHave.add(layerChain.get(i));
		}
		super.visitClass(tree);
	}

	@Override
	public void visitVariable(VariableTree tree) {
		String varClsName = tree.type().toString();
		if (cannotHave != null && !cannotHave.isEmpty())
			for (String name : cannotHave) {
				if (varClsName.toUpperCase().contains(name)) {
					int linenum = tree.endToken() == null ? 0 : tree.endToken().line();
					context.addIssue(linenum, this, String.format("%s layer should not have %s layer reference", clsName, name));
				}
			}
		super.visitVariable(tree);
	}

}
