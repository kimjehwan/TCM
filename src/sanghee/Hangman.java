package sanghee;


import java.io.*;

public class Hangman 
{
	String hiddenString; //������ ���ڿ�(����)
	StringBuffer outputString;//�÷��̾��� �Է¿� ���� ����� ������ ���ڿ�
    StringBuffer inputString; //�÷��̾ �Է��� ���ڵ��� ����
    int remaninder; //���� ���ڿ� (�� ���߰� �����ִ� ������ ��)
    int failed; //������ Ƚ��
    
    public Hangman() throws IOException
    {
    	hiddenString="hello";//������ "hello"
    }
    
    public int playGame() throws IOException
    {
    	outputString = new StringBuffer();
    	
    	for(int i=0; i<hiddenString.length(); i++) { //hiddenString�� ���� ����ŭ '-'���
    		outputString.append('_');
    	}
    	
    	inputString = new StringBuffer();
    	
    	remaninder = hiddenString.length(); //hiddenString�� ���ڼ��� ���㹮���� ���ڼ� 
    	failed = 0;
    	
    	System.out.println("\n�ܾ�("+ hiddenString.length() + "����"+"): "+ outputString);  
    	drawMan();  //������ �׸��� 
    		
       do{
    	 checkChar(readChar()); //�� ���ڸ� �Է¹޾Ƽ� �������� Ȯ��
    	 System.out.println("\n�ܾ�("+ hiddenString.length() + "����"+"): "+ outputString);
    	 drawMan(); //�Է¹��ڿ� ���� ������ ���
         } while ((remaninder > 0) && (failed <6)); //������ ������ ���߰ų� 6���̻� Ʋ�������� �ݺ�
       
       return failed;   
    }
    
    public void checkChar (char guess)
    {
    	boolean already = false;
    	for (int i=0; i<inputString.length();i++) {
    		if(inputString.charAt(i)==guess) {  //�̹��Է��ߴ� �������� ����
    		  System.out.println("\n �̹� �Էµ� �����Դϴ� �ٽ� �Է����ּ���,");
    		  already = true;
    		}
    	}
    
    	if(!already) {
    		//�Է��� ���ڵ��� ���ӿ� �߰� 
    		inputString.append(guess);
    		
    		boolean success = false;
    		for(int i=0; i<hiddenString.length();i++) {
    	    			if(hiddenString.charAt(i)==guess){ //������ �ش繮�ڰ� �ִ��� ����  
    				outputString.setCharAt(i,guess);  //������ ���ڰ� ������ -�� ���ڷ� ����
    				remaninder--;   //���� ���ڼ� 1 ���� 
    			   success = true; //�Է��� ���ڰ� ������ �־����� ǥ��
    			}
    	   	}     
    		if(!success) failed++; 	//�Է��� ���ڰ� ������ ������ ����Ƚ���� 1����	
    	   }
       	}
    
    public void drawMan()
    {
     System.out.println("����������������");
     System.out.println("��      �� ");
     
     switch(failed) { //����Ƚ���� ���� �����뿡 ����� �׸�
      case 0:
    	  System.out.println("    �� ");
          System.out.println("    �� ");
    	  System.out.println("    �� ");
          System.out.println("    �� ");
    	  System.out.println("    �� ");
          System.out.println("    �� ");
          break;
     case 1:
   	     System.out.println(" ��  �� "); //1�� ������ ��� �Ӹ� �׸� 
         System.out.println("    �� ");
	     System.out.println("    �� ");
         System.out.println("    �� ");
	     System.out.println("    �� ");
         System.out.println("    �� ");
         break;
     case 2:
   	     System.out.println(" ��  �� "); 
         System.out.println(" ���� �� "); //2�� ������ ��� ���� �׸� 
	     System.out.println("    �� ");
         System.out.println("    �� ");
	     System.out.println("    �� ");
         System.out.println("    �� ");
         break; 
     case 3:
  	     System.out.println(" ��  �� "); 
         System.out.println("����  �� "); 
	     System.out.println(" ��  �� "); //3�� ������ ��� ���� �׸� 
         System.out.println("    �� ");
	     System.out.println("    �� ");
         System.out.println("    �� ");
         break; 
    case 4:
  	     System.out.println(" ��  �� "); 
         System.out.println("������ �� "); //4�� ������ ��� ������ �׸� 
	     System.out.println(" ��  �� "); 
         System.out.println("    �� ");
	     System.out.println("    �� ");
         System.out.println("    �� ");
         break; 
    case 5:
 	     System.out.println(" ��  �� "); 
         System.out.println("������ �� "); 
	     System.out.println(" ��  �� "); 
         System.out.println(" ��  �� "); //5�� ������ ��� ������ �׸� 
	     System.out.println(" ��  �� ");
         System.out.println("    �� ");
         break;   
    case 6:
	     System.out.println(" ��  �� "); 
         System.out.println("������ �� "); 
	     System.out.println(" ��  �� "); 
         System.out.println("������ �� "); //6�� ������ ��� ������ �׸� 
	     System.out.println("�� �� �� ");
         System.out.println("    �� ");
         break; 
    }  
  }
    
    public char readChar() throws IOException
    {
     BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
     String user;
     
     System.out.println("1 ���ڸ� �Է��ϼ���:");
     user = in.readLine(); //Ű����κ��� ������ �Է�
     return user.charAt(0); //�Է¹��� ���ڿ� �� ù��° ���ڸ� ��ȯ
    }

}