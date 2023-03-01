
/**
 *
 * @author maha
 */

//Exception should be replaced by the appropriate specific exceptions
public class Project2 {



	public static void main(String[] args) {
		
		AuthDLList blockchain = new AuthDLList();
		
		String file1= "Hello!";
		String file2 = "Welcome to BlockChain!";
		String file3 = "This is Project 2";
		
		String proofCheck = null;
		

		
		try{
			System.out.println("start digest: " + AuthDLList.startDigest +"\n");
		
			
			System.out.println("Lets Insert the first file : \"Hello\" \n");
			proofCheck = blockchain.insertFileNode(file1, null);
			System.out.println("Proof check after the first inserton operation: " + proofCheck+ "\n");
			
			System.out.println("Insert the second file file 2 \"Welcome to BlockChain!\" "+ "\n");
			proofCheck = blockchain.insertFileNode(file2, proofCheck);
			System.out.println("Proof check after the second insertion operation: " + proofCheck+"\n");


			System.out.println("Insert the third file \"This is Project 2\" \n");
			proofCheck = blockchain.insertFileNode(file3, proofCheck);
			System.out.println("Proof after insert operation: " + proofCheck+"\n\n");
		}catch(Exception e){
			System.out.println(e+ "\n");
		}
		// Delete First Node
		try{
			proofCheck = blockchain.deleteFirstFile(proofCheck);
			System.out.println("This deletes the First Node"+ "\n");
			System.out.println("the value of ProofCheck after DeleteFirst: " + proofCheck +"\n\n");
		}catch(Exception e){
			System.out.println(e+ "\n");
		}


		// Delete Last Node
		try{
			proofCheck = blockchain.deleteLastFile(proofCheck);
			System.out.println("This Deletes the Last Node"+ "\n");
			System.out.println("The value of ProofCheck after DeleteLast: " + proofCheck +"\n\n");
		}catch(Exception e){
			System.out.println(e+ "\n");
		}

}
}
