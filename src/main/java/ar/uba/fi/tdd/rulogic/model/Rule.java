package ar.uba.fi.tdd.rulogic.model;

import java.util.*;

public class Rule implements Condition {

	private String name;
	private String args;
	private String facts;

	public Rule(String line){
		this.name = line.substring(0, line.indexOf("("));
		this.args = line.substring(line.indexOf("(") + 1, line.indexOf(")"));
		this.facts = line.substring(line.indexOf(":-") + 2, line.indexOf(").") +1);
		
	}

	private String replaceArgs(String args){
		List<String> listOfArgsTo = Arrays.asList(args.split(","));
		List<String> listOfArgsFrom = Arrays.asList(this.args.split(","));

		String result = this.facts;
		for (int i = 0; i < listOfArgsTo.size(); i++) {
		 	result = result.replaceAll(listOfArgsFrom.get(i).trim(), listOfArgsTo.get(i).trim());
		}

		return result;
	}

	public boolean resolveYourself(List<Condition> lista ,String query) throws Exception{
		String name = "";
		String args = "";
		try{
			name = query.substring(0, query.indexOf("("));
			args = query.substring(query.indexOf("(") + 1, query.indexOf(")."));
		}catch(Exception e){
			throw new Exception("The Query Rule is not valid.");
		}
		boolean stop = false;
		if (name.equals(this.name)){
				String argsReplaced = replaceArgs(args);
				List<String> listOfFactsReplaced = Arrays.asList(argsReplaced.split("\\),"));

				for (String item : listOfFactsReplaced) {
					String fact = item;
					fact = fact.trim();
					if (!fact.substring(fact.length() - 1).equals(")")){
						fact += ").";
					}
					else {
						fact += ".";
					}
					for (Condition cond : lista ) {
						stop = cond.resolveYourself(lista, fact);
						if (stop) {
	    					break;
	    				}
					}
				}
				return stop;
			}
		return stop;
	}
}