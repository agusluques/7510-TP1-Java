package ar.uba.fi.tdd.rulogic;
import ar.uba.fi.tdd.rulogic.model.KnowledgeBase;

/**
 * Console application.
 *
 */
public class App
{
	public static void main(String[] args) {

		try{
			KnowledgeBase base = new KnowledgeBase();

			base.parseDb("src/main/resources/rules.db");

			System.out.println("\nI shall answer all your questions!");

			System.out.println("Press \"Enter\" to finish!\n");

			System.out.println("Enter query: ");

			String query = System.console().readLine();

			while(!query.isEmpty()){

				System.out.println("Your answer is: " + String.valueOf(base.answer(query)));

				System.out.println("\nEnter query: ");

				query = System.console().readLine();

			}

			System.out.println("\nSee you soon!");
		}catch(Exception e){
			System.out.println("\u001B[31m" + e.getMessage() + "\u001B[0m");
		}
		
    }
}
