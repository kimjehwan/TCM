package sanghee.hangman;

public class hangmanMain {

	  public static void main(String[] args)  {
		  run();
	 }

	public static void run(){
		 Hangman hangman = new Hangman();
	        System.out.println("��Ǹ���");
	        int result = hangman.playGame(); //������ ���� 
	        
	         System.out.println();
	         if(result<=2) { //����Ƚ���� ���� �޼��� ���
	        	 System.out.println("�ڡ� ��   �� �١�");
	        	 System.out.println("   �� ���߾��!");
	          }else if (result<=3) {
		         System.out.println("�ڡ� ��   �� �١�");
	        	 System.out.println("     ���߾��!");
	          }else if (result<=4) {
		         System.out.println("�ڡ� ��   �� �١�");
	        	 System.out.println("   �����̳׿�!");
	          }else {
	        	 System.out.println("�ڰ� �� �� ����");
	        	 System.out.println("  �й��ϼ���!");
	          }
		
	}

}
