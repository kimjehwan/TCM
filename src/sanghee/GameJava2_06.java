package sanghee;

import java.io.IOException;

public class GameJava2_06 {

	  public static void main(String[] args) throws IOException
	  {
        Hangman hangman = new Hangman();
        
        int result = hangman.playGame(); //게임을 실행 
        
         System.out.println();
         if(result<=2) { //실패횟수에 따라 메세지 출력
        	 System.out.println("참 잘했어요!");
          }else if (result<=3) {
        	 System.out.println("잘했어요!");
          }else if (result<=4) {
        	 System.out.println("보통이네요!");
          }else {
        	 System.out.println("분별하세요!");
          }
         
	 }

}
