package ar.uba.fi.tdd.rulogic.model;

import java.io.*;
import java.util.*;
//colores: https://stackoverflow.com/a/5762502
public class KnowledgeBase {

	private List<Condition> listOfFactsAndRules = new ArrayList<Condition>();

	public boolean answer(String query) {
		boolean stop = false;
		try{
			for(Condition item : listOfFactsAndRules){
	    			stop = item.resolveYourself(listOfFactsAndRules, query);
	    			if (stop) {
	    				break;
	    			}
	    		}
	    }catch(Exception e){
	    	System.out.println("\u001B[31m" + e.getMessage() + "\u001B[0m");
	    	stop = false;
	    }
	    finally{
	    	return stop;
	    }
    	
	}

	private void parseLine(String line) throws Exception{
		if (line.indexOf(":-") >= 0) {
			if (line.indexOf(").") < 0) {
				throw new Exception("The rule: \""+line+"\" is not valid.");
			}
			
		}else{
			if (line.indexOf("(") < 0 || line.indexOf(").") < 0) {
				throw new Exception("The fact: \""+line+"\" is not valid.");
			}
		}
	}

	private void isFactOrRule(String line){
		if (line.indexOf(":-") >= 0) {
			listOfFactsAndRules.add(new Rule(line));
		}else{
			listOfFactsAndRules.add(new Fact(line));
		}
	}

	public void parseDb(String file) throws Exception{
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			System.out.println("\u001B[32m" + "################################");
			System.out.println("Parsing Db...\n\n");
    		while ((line = br.readLine()) != null) {
    			System.out.println(line);
    			parseLine(line);
    			isFactOrRule(line);

    		}
    	} catch(Exception e){
    		throw e;
    	}finally{
    		System.out.println("\n\nFinish parsing Db...");
    		System.out.println("################################" + "\u001B[0m");
    	}
	}

}