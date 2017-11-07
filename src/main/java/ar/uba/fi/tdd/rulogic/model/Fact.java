package ar.uba.fi.tdd.rulogic.model;

import java.util.*;

public class Fact implements Condition {

	private String name;
	private String args;

	public Fact(String line){
		this.name = line.substring(0, line.indexOf("("));
		this.args = line.substring(line.indexOf("(") + 1, line.indexOf(")."));
		
	}

	public boolean resolveYourself(List<Condition> lista ,String query) throws Exception{
		String name = "";
		String args = "";
		try{
			name = query.substring(0, query.indexOf("("));
			args = query.substring(query.indexOf("(") + 1, query.indexOf(")."));
		}catch(Exception e){
			throw new Exception("The Query Fact is not valid.");
		}
		if (name.trim().equals(this.name.trim()) && args.trim().equals(this.args.trim())){
			return true;
		}else{
			return false;
		}

	}
}