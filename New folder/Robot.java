import java.util.*;
import java.io.*;

public class Robot{

   Stack<Character> one;
   Stack<Character> two;
   Stack<Character> three;
   
   Stack<Character> backup;
   Stack<Character> backup1;
   
   String argument;
   int stackNo;
   
   char foundx = ' ';
   int foundxStack = -1;
   int foundyStack = 0;
   boolean argumentxFlag = false;
   boolean argumentyFlag = false;

 
   public Robot(String blocks){         //("a,dcb,fe")           //first mai comma aayo bhaney
   
      one = new Stack<>();
      two = new Stack<>();
      three = new Stack<>();
      
      backup = new Stack<>();
      backup1 = new Stack<>();
      
      argument = blocks;
   
      int comma = 0;
   
      for(int i=0; i<blocks.length(); i++){
         char user = blocks.charAt(i);
         if(user == ','){
            comma++;
         }//if ends
         
         else if(Character.isLetter(user)){
            if(comma == 0){
               one.push(user);
            }
            
            else if(comma == 1){
               two.push(user);
            }
            
            else if(comma == 2){
               three.push(user);
            }
         }  
      }//for ends
   
   
   }  //constructor that initializes the robot's world


   public boolean readActions(String filename){
   
      try{
         Scanner in = new Scanner(new File(filename));
      
         String[] command = new String[10];
         String line;
      
         while(in.hasNextLine()){
            line = in.nextLine();
            command = line.split(" ");
            String user1;
            String user2;
         
            char userX;
            char userY;
         
            if(command[0].equals("move")){
               user1 = command[1];
               user2 = command[command.length - 1];
            
               userX = user1.charAt(0);
               userY = user2.charAt(0);
             
               moveMethod(userX, userY);           //calls move method
            }//moveifends  
            
            else if(command[0].equals("pile")){
               user1 = command[1];
               user2 = command[command.length - 1];
            
               userX = user1.charAt(0);          //converting String to char
               userY = user2.charAt(0);
            
            
               pileMethod(userX, userY);
            } //pileifends
            
            
            else if(command[0].equals("discard")){
               user1 = command[command.length - 1];
            
               userX = user1.charAt(0);
            
               discardMethod(userX);
            } //else if ends
            
            
            else if(command[0].equals("quit"));
            
            
            else{
               return false;
            }
         
         }//while ends
      
      
      
      } //try ends
      catch(Exception e){
         return false;
      }
      return true;       
   } //readActions ends

   
   public void moveMethod(char x, char y){
   
      foundx = ' ';
      foundxStack = -1;
      foundyStack = 0;
      argumentxFlag = false;
      argumentyFlag = false;
      for(int i =0; i<argument.length(); i++){
         if(x==argument.charAt(i)){
            argumentxFlag = true;
         }
         
         if(y==argument.charAt(i)){
            argumentyFlag = true;
         }
      }
   
      if(x!=y && argumentxFlag && argumentyFlag){                       //x and y are equal to one of the strings by user
         while(!one.empty()){               //(a)stack one ma check garcha x ko laagi, bhettayo bhaney backup ma raakheko cha, yedi bhettayo bhaney break huncha
            if(one.peek() == x){
               foundx = one.pop();
               foundxStack = 1;
               break;
            }
            
            else{
               backup.push(one.pop());
            
            }
         
         } //while ends                        //(a)     
      
         while(!backup.empty()){
            one.push(backup.pop());
         }       
      
         if(foundxStack == -1){
         
            while(!two.empty()){   
               if(two.peek() == x){
                  foundx = two.pop();
                  foundxStack = 2;
                  break;
               }
               
               else{
                  backup.push(two.pop());
               }
            
            } //while ends
         
            while(!backup.empty()){
               two.push(backup.pop());
            }       
         
         
         }//if foundxStack==-1
      
         if(foundxStack == -1){
         
            while(!three.empty()){   
               if(three.peek() == x){
                  foundx = three.pop();
                  foundxStack = 3;
                  break;
               }
               
               else{     
                  backup.push(three.pop());
               }
            
            } //while ends
         
            while(!backup.empty()){
               three.push(backup.pop());
            }       
         
         }//foundxStack==-1
      
      //search of y
      //foundx
      //yedi foundxStack = 1 cha bhaney
         if(foundxStack == 1){
            while(!two.empty()){
               if(two.peek() == y){
                  foundyStack = 2;
                  break;
               }
               
               else{
                  backup1.push(two.pop());
               }
            
            }//while(!two.empty())
         
         //flag liyo ra sabbai elements lai firta pathayo
            while(!backup1.empty()){
               two.push(backup1.pop());
            }
         
            if(foundyStack == 2){
               two.push(foundx);
            }
            
            else{
            
               while(!three.empty()){
                  if(three.peek() == y){
                     foundyStack = 3;
                     break;
                  }
                  
                  else{
                     backup1.push(three.pop());
                  }
               
               }//while(!three.empty())
            
            //flag liyo ra sabbai elements lai firta pathayo
               while(!backup1.empty()){
                  three.push(backup1.pop());
               }
            
               if(foundyStack == 3){
                  three.push(foundx);
               }
            
            }//else ends
         
         }//yedi x one ma cha bhaney ends   
         
         //if foundxStack == 2 x dui ma cha bhaney
         else if(foundxStack == 2){
         
            while(!one.empty()){
               if(one.peek() == y){
                  foundyStack = 1;
                  break;
               }
               
               else{
                  backup1.push(one.pop());
               }
            
            }//while(!one.empty())
         
         //flag liyo ra sabbai elements lai firta pathayo
            while(!backup1.empty()){
               one.push(backup1.pop());
            }
         
            if(foundyStack == 1){
               one.push(foundx);
            }
            
            else{
            
               while(!three.empty()){
                  if(three.peek() == y){
                     foundyStack = 3;
                     break;
                  }
                  
                  else{
                     backup1.push(three.pop());
                  }
               
               }//while(!three.empty())
            
            //flag liyo ra sabbai elements lai firta pathayo
               while(!backup1.empty()){
                  three.push(backup1.pop());
               }
            
               if(foundyStack == 3){
                  three.push(foundx);
               }
            
            }//else ends
         
         
         } //elseiffoundxStack==2      
         
         //if foundxStack == 3               
         else if(foundxStack == 3){
         
            while(!one.empty()){
               if(one.peek() == y){
                  foundyStack = 1;
                  break;
               }
               
               else{
                  backup1.push(one.pop());
               }
            
            }//while(!one.empty())
         
         //flag liyo ra sabbai elements lai firta pathayo
            while(!backup1.empty()){
               one.push(backup1.pop());
            }
         
            if(foundyStack == 1){
               one.push(foundx);
            }
            
            else{
            
               while(!two.empty()){
                  if(two.peek() == y){
                     foundyStack = 2;
                     break;
                  }
                  
                  else{
                     backup1.push(two.pop());
                  }
               
               }//while(!three.empty())
            
            //flag liyo ra sabbai elements lai firta pathayo
               while(!backup1.empty()){
                  two.push(backup1.pop());
               }
            
               if(foundyStack == 2){
                  two.push(foundx);
               }
            
            }//else ends
         
         
         }//foundxstack ==3 ends
      
      
      
      }   //x and y condition to get into       
   } //moveMethod ends
      
        
      
      
   public void pileMethod(char x, char y){
      
      foundx = ' ';
      foundxStack = -1;
      foundyStack = 0;
      argumentxFlag = false;
      argumentyFlag = false;
        
      for(int i =0; i<argument.length(); i++){
         if(x==argument.charAt(i)){
            argumentxFlag = true;
         }
         
         if(y==argument.charAt(i)){
            argumentyFlag = true;
         }
      }
   
      if(x!=y && argumentxFlag && argumentyFlag){                      
         while(!one.empty()){               
            if(one.peek() == x){
            
               foundxStack = 1;
               backup.push(one.pop());
               break;
            }
            
            else{
               backup.push(one.pop());
            
            }
         
         } //while ends                        //(a)     
      
      
      
         if(foundxStack == -1){
            while(!backup.empty()){
               one.push(backup.pop());
            }
         
         
            while(!two.empty()){   
               if(two.peek() == x){
                  foundxStack = 2;
                  backup.push(two.pop());
                  break;
               }
               
               else{
                  backup.push(two.pop());
               
               }         
            } //while ends
           
          
         }//if foundxStack==-1
      
         if(foundxStack == -1){
            while(!backup.empty()){
               two.push(backup.pop());
            }
         
            while(!three.empty()){   
               if(three.peek() == x){
                  foundxStack = 1;
                  backup.push(three.pop());
                  break;
               }
               
               else{
                  backup.push(three.pop());
               
               }         
            } //while ends
           
         
         }//foundxStack==-1
      
         if(foundxStack==-1){
            while(!backup.empty()){
               three.push(backup.pop());
            }
         
         }
      //search of y
      //foundx
      //if foundxStack = 1
         if(foundxStack == 1){
            while(!two.empty()){
               if(two.peek() == y){
                  foundyStack = 2;
                  break;
               }
               
               else{
                  backup1.push(two.pop());
               }
            
            }//while(!two.empty())
         
         //flag recorded and puts back all the elements
            while(!backup1.empty()){
               two.push(backup1.pop());
            }
         
         
            if(foundyStack == 2){
               while(!backup.empty()){
                  two.push(backup.pop());
               }
            
            }
            
            else{
            
               while(!three.empty()){
                  if(three.peek() == y){
                     foundyStack = 3;
                     break;
                  }
                  
                  else{
                     backup1.push(three.pop());
                  }
               
               }//while(!three.empty())
            
            //flag recorded and puts back all the elements
               while(!backup1.empty()){
                  three.push(backup1.pop());
               }
            
               if(foundyStack == 3){
                  while(!backup.empty()){
                     three.push(backup.pop());
                  }
               
               }
            
            }//else ends
         
         }
         
         //if foundxStack == 2 x dui ma cha bhaney
         else if(foundxStack == 2){
         
            while(!one.empty()){
               if(one.peek() == y){
                  foundyStack = 1;
                  break;
               }
               
               else{
                  backup1.push(one.pop());
               }
            
            }//while(!one.empty())
         
         //flag recorded and puts back all the elements
            while(!backup1.empty()){
               one.push(backup1.pop());
            }
         
         
            if(foundyStack == 1){
               while(!backup.empty()){
                  one.push(backup.pop());
               }
            
            }
            
            else{
            
               while(!three.empty()){
                  if(three.peek() == y){
                     foundyStack = 3;
                     break;
                  }
                  
                  else{
                     backup1.push(three.pop());
                  }
               
               }//while(!three.empty())
            
            //flag recorded and puts back all the elements
               while(!backup1.empty()){
                  three.push(backup1.pop());
               }
            
               if(foundyStack == 3){
                  while(!backup.empty()){
                     three.push(backup.pop());
                  }
               
               }
            
            }//else ends
         
         }  //elseiffoundxStack==2      
         
         //if foundxStack == 3               
         else if(foundxStack == 3){
         
            while(!two.empty()){
               if(two.peek() == y){
                  foundyStack = 2;
                  break;
               }
               
               else{
                  backup1.push(two.pop());
               }
            
            }//while(!two.empty())
         
         //flag recorded and puts back all the elements
            while(!backup1.empty()){
               two.push(backup1.pop());
            }
         
         
            if(foundyStack == 2){
               while(!backup.empty()){
                  two.push(backup.pop());
               }
            
            }
            
            else{
            
               while(!one.empty()){
                  if(one.peek() == y){
                     foundyStack = 1;
                     break;
                  }
                  
                  else{
                     backup1.push(one.pop());
                  }
               
               }//while(!three.empty())
            
            //flag recorded and puts back all the elements
               while(!backup1.empty()){
                  one.push(backup1.pop());
               }
            
               if(foundyStack == 1){
                  while(!backup.empty()){
                     one.push(backup.pop());
                  }
               
               }
            
            }      
         
         }//foundxstack ==3 ends
      
      }   //x and y condition to get into       
   
      
   } //pileMethod ends
      
      
   public void discardMethod(char x){
      foundxStack = -1;
      argumentxFlag = false;
      
      for(int i = 0; i<argument.length(); i++){
         if(x == argument.charAt(i)){
            argumentxFlag = true;
         }
      } //for ends
      
      if(argumentxFlag){
         while(!one.empty()){
            if(one.peek() == x){
               foundxStack = 1;
               one.pop();
               break;
            }
            
            else{
               backup.push(one.pop());
            }
         
         }//while ends
         
         while(!backup.empty()){
            one.push(backup.pop());
         } //while ends
         
         
         if(foundxStack==-1){
            while(!two.empty()){
               if(two.peek() == x){
                  foundxStack = 2;
                  two.pop();
                  break;
               }
               
               else{
                  backup.push(two.pop());
               }
            
            }//while ends
         
            while(!backup.empty()){
               two.push(backup.pop());
            } //while ends
         
         }//else if ends
         
         if(foundxStack ==-1){
         
            while(!three.empty()){
               if(three.peek() == x){
                  foundxStack = 3;
                  three.pop();
                  break;
               }
               
               else{
                  backup.push(three.pop());
               }
            
            }//while ends
         
            while(!backup.empty()){
               three.push(backup.pop());
            } //while ends
         
         }//else ends
      
      }//x to be true to pass
      
   }//discardMethod ends
     
   public String toString(){
      String finalone = "";
      String finaltwo = "";
      String finalthree = "";
     
      while(!one.empty()){
         finalone = finalone + one.pop() + " ";
      }
     
      while(!two.empty()){
         finaltwo = finaltwo + two.pop() + " ";
      }
     
      while(!three.empty()){
         finalthree = finalthree + three.pop() + " ";
      }
     
      return "PILE 1: " + finalone + "\nPILE 2: " + finaltwo + "\nPILE 3: " + finalthree; 
     
   }//toString ends
     
    
     
}//class ends
     
