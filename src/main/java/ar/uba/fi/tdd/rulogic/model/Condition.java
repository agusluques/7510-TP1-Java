package ar.uba.fi.tdd.rulogic.model;

import java.util.*;


interface Condition {
	public boolean resolveYourself(List<Condition> lista ,String query) throws Exception;
}