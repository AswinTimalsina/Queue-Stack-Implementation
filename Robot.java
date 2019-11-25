/**
Date:        11/02/2018
Course:      CSCI 2073 - 40416
Description: This progrom works as a robot arm. Strings are provided by the user, 
             the strings are put into stacks and according to the instruction provided
             in the txt file, the robot perform the work.
Name:        Aswin Timalsina
CWID:        30098693
*/


import java.lang.*;
import java.util.*;
import java.io.*;


public class Robot{

   StackInt<Character> one;
   StackInt<Character> two;
   StackInt<Character> three;
   
   StackInt<Character> one1;   
   StackInt<Character> two1;
   StackInt<Character> three1;
   
   StackInt<Character> backup;
   StackInt<Character> backup1;
   
   String argument;
   int stackNo;
   
   char foundx = ' ';
   int foundxStack = -1;
   int foundyStack = 0;
   boolean argumentxFlag = false;
   boolean argumentyFlag = false;

 /**
   *argument constructor to initialise various object of type StackInt and variables
   *also puts the user provided string in stack
*/
   public Robot(String blocks){         
   
      one = new LinkedStack<>();
      two = new LinkedStack<>();
      three = new LinkedStack<>();
      
      one1 = new LinkedStack<>();
      two1 = new LinkedStack<>();
      three1 = new LinkedStack<>();
      
      backup = new LinkedStack<>();
      backup1 = new LinkedStack<>();
      
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



   /**
   *Method to return boolean data type. It has one strings as parameter i.e filename. 
   *The method helps in running various other methods according to the text instructions
   *Returns true if the file opens and works successfully else false
   @param filename, the name of the file
   @returns if the file opens successfully or not 
*/
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
      
      in.close();
      } //try ends
      
      catch(Exception e){
         return false;
      }
      return true;       
   } //readActions ends

   /**
   *Method to move the characters in the stack according to the instruction. 
   @param x, the first character to be used according to the instruction 
   @param y, the second character to be used according to the instruction  
   */
   public void moveMethod(char x, char y){
   
      foundx = ' ';
      foundxStack = -1;
      foundyStack = 0;
      argumentxFlag = false;
      argumentyFlag = false;
      
      
      while(!one.empty()){
         if(one.peek()==x){
            argumentxFlag = true;
            break;
         }
         
         else{
         one1.push(one.pop());
         }
      }
      
      while(!one1.empty()){
      one.push(one1.pop());
      }
      
      if(argumentxFlag == false){
      
      while(!two.empty()){
         if(two.peek()==x){
            argumentxFlag = true;
            break;
         }
         
         else{
         two1.push(two.pop());
         }
      }
      
      while(!two1.empty()){
      two.push(two1.pop());
      }
      }
      
      if(argumentxFlag == false){
      
      while(!three.empty()){
         if(three.peek()==x){
            argumentxFlag = true;
            break;
         }
         
         else{
         three1.push(three.pop());
         }
      }
      
      while(!three1.empty()){
      three.push(three1.pop());
      }
      } // end of x
      
      while(!one.empty()){
         if(one.peek()==y){
            argumentyFlag = true;
            break;
         }
         
         else{
         one1.push(one.pop());
         }
      }
      
      while(!one1.empty()){
      one.push(one1.pop());
      }
      
      if(argumentyFlag == false){
      
      while(!two.empty()){
         if(two.peek()==y){
            argumentyFlag = true;
            break;
         }
         
         else{
         two1.push(two.pop());
         }
      }
      
      while(!two1.empty()){
      two.push(two1.pop());
      }
      }  //y search in two
      
      if(argumentyFlag == false){   //three
      
      while(!three.empty()){
         if(three.peek()==y){
            argumentyFlag = true;
            break;
         }
         
         else{
         three1.push(three.pop());
         }
      }
      
      while(!three1.empty()){
      three.push(three1.pop());
      }

      
      
      }//yflag
      
      
      
      
      
      
      
      // for(char moveA:one ){
//       if(moveA== x){
//          argumentxFlag = true;
//       }
//       }
//       
//       for(char moveA: two){
//       if(moveA == x){
//          argumentxFlag = true;
//       }
//       }
//       
//       for(char moveA: three){
//       if(move==x){
//          argumentxFlag = true;
//       }
//       }
//       
//       for(char moveB: one){
//       if(moveB == y){
//          argumentyFlag = true;
//       }
//       }
//       
//       for(char moveB: two){
//       if(moveB == y){
//          argumentyFlag = true;
//       }
//       }
//       
//       for(char moveB: three){
//       if(moveB == y){
//          argumentyFlag = true;
//       }
//       }
      
      // for(int i =0; i<argument.length(); i++){
//          if(x==argument.charAt(i)){
//             argumentxFlag = true;
//          }
//          
//          if(y==argument.charAt(i)){
//             argumentyFlag = true;
//          }
//       }
   
      if(x!=y && argumentxFlag && argumentyFlag){             //x and y are equal to one of the strings by user
         while(!one.empty()){                                 //(a)
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
      //if foundxStack == 1 
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
            
           
               while(!backup1.empty()){
                  three.push(backup1.pop());
               }
            
               if(foundyStack == 3){
                  three.push(foundx);
               }
            
            }//else ends
         
         }//if x==1 ends
         
         //if foundxStack == 2 x dui
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
      
        
      
   /**
   *Method to pile the characters in the stack according to the instruction. 
   @param x, the first character to be used according to the instruction 
   @param y, the second character to be used according to the instruction  
   */

      public void pileMethod(char x, char y){
      foundx = ' ';
      foundxStack = -1;
      foundyStack = 0;
      argumentxFlag = false;
      argumentyFlag = false;
        
      while(!one.empty()){
         if(one.peek()==x){
            argumentxFlag = true;
            break;
         }
         
         else{
         one1.push(one.pop());
         }
      }
      
      while(!one1.empty()){
      one.push(one1.pop());
      }
      
      if(argumentxFlag == false){
      
      while(!two.empty()){
         if(two.peek()==x){
            argumentxFlag = true;
            break;
         }
         
         else{
         two1.push(two.pop());
         }
      }
      
      while(!two1.empty()){
      two.push(two1.pop());
      }
      }
      
      if(argumentxFlag == false){
      
      while(!three.empty()){
         if(three.peek()==x){
            argumentxFlag = true;
            break;
         }
         
         else{
         three1.push(three.pop());
         }
      }
      
      while(!three1.empty()){
      three.push(three1.pop());
      }
      } // end of x
      
      while(!one.empty()){
         if(one.peek()==y){
            argumentyFlag = true;
            break;
         }
         
         else{
         one1.push(one.pop());
         }
      }
      
      while(!one1.empty()){
      one.push(one1.pop());
      }
      
      if(argumentyFlag == false){
      
      while(!two.empty()){
         if(two.peek()==y){
            argumentyFlag = true;
            break;
         }
         
         else{
         two1.push(two.pop());
         }
      }
      
      while(!two1.empty()){
      two.push(two1.pop());
      }
      }  //y search in two
      
      if(argumentyFlag == false){   //three
      
      while(!three.empty()){
         if(three.peek()==y){
            argumentyFlag = true;
            break;
         }
         
         else{
         three1.push(three.pop());
         }
      }
      
      while(!three1.empty()){
      three.push(three1.pop());
      }

      
      
      }//yflag
      
         
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
      
   /**
   *Method to discard the character in the stack
   @param x, the first character to be discarded from the stack 
   */
   public void discardMethod(char x){
      foundxStack = -1;
      argumentxFlag = false;
      
    while(!one.empty()){
         if(one.peek()==x){
            argumentxFlag = true;
            break;
         }
         
         else{
         one1.push(one.pop());
         }
      }
      
      while(!one1.empty()){
      one.push(one1.pop());
      }
      
      if(argumentxFlag == false){
      
      while(!two.empty()){
         if(two.peek()==x){
            argumentxFlag = true;
            break;
         }
         
         else{
         two1.push(two.pop());
         }
      }
      
      while(!two1.empty()){
      two.push(two1.pop());
      }
      }
      
      if(argumentxFlag == false){
      
      while(!three.empty()){
         if(three.peek()==x){
            argumentxFlag = true;
            break;
         }
         
         else{
         three1.push(three.pop());
         }
      }
      
      while(!three1.empty()){
      three.push(three1.pop());
      }
      }
      
      
                 
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
         
         if(foundxStack == -1){
         
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
    
   /**
   *Method to return the values in different piles 
   */
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
     
