/**
 * @author Konrad Zdanowicz (zdanowicz.konrad@gmail.com)
 * 
 */
package softcomputing.task2.carpurchase.engine.rules;

import java.util.ArrayList;
import java.util.List;

import softcomputing.task2.carpurchase.engine.api.Rule;

public class RuleFactory {

	public static List<Rule> create(String rules) {
		String[] ruleArray = rules.split(",");
		List<Rule> parsedRules = new ArrayList<Rule>();
		for (int i = 0; i < ruleArray.length; i++) {
			Rule rule = getRule(ruleArray[i]);
			parsedRules.add(rule);
		}
		return parsedRules;
	}

	private static Rule getRule(String attr) {
		if (attr.startsWith("custom:")) {
			String customRules = attr.substring(attr.indexOf("{") + 1, attr.indexOf("}"));
			return new CustomRule(customRules);
		}
		if (attr.equals("-"))
			return new ZeroRule();
		else if (attr.substring(0, 1).equals("/"))
			return new ReverseRule(Double.parseDouble(attr.substring(1)));
		else
			return new ProportionalRule(Double.parseDouble(attr));
	}

}
