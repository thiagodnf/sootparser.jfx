package thiagodnf.sootparser.builder.generator;

import java.util.ArrayList;
import java.util.List;

import soot.SootClass;
import soot.SootMethod;
import soot.jimple.toolkits.callgraph.CallGraph;

public class MethodGenerator extends AbstractGenerator {

	public String isStatic(SootMethod method) {
		return method.isStatic() ? "Y" : "N";
	}

	public String isAbstract(SootMethod method) {
		return method.isAbstract() ? "Y" : "N";
	}
	
	public String getVisibility(SootMethod method) {
		if (method.isPrivate()) {
			return "Private";
		} else if (method.isPublic()) {
			return "Public";
		} else if (method.isProtected()) {
			return "Protected";
		} else {
			return "Public";
		}
	}
	
	protected String generate(SootMethod method) {

		StringBuilder builder = new StringBuilder();

		builder.append("Method(");
		builder.append(method.getNumber());
		builder.append(",");
		builder.append(method.getName());
		builder.append(",");
		builder.append(method.getReturnType());
		builder.append(",");
		builder.append(getVisibility(method));
		builder.append(",");
		builder.append(isStatic(method));
		builder.append(",");
		builder.append(isAbstract(method));
		builder.append(");");

		return builder.toString();
	}
	
	@Override
	public List<String> generate(CallGraph cg, SootClass cls, List<SootClass> classes) {
		
		List<String> lines = new ArrayList<String>();

		for (SootMethod method : cls.getMethods()) {
			
			if (method.getName().contains("<clinit>")) {
				continue;
			}
			
			if(method.getName().startsWith("access$")) {
				continue;
			}
			
			lines.add(generate(method));
		}

		return lines;
	}

}
