// Ozan Çatalorman, 21COMP1032
// Emine Ekin, COMP1111.4
// 20.11.2022
/* This is an arithmetic's exercise program which allows user to answer as many as user can
in 90 seconds */

package project2nd_winter202223;

import java.util.Scanner;
import java.lang.Math;

public class Project2nd_Winter202223 {

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        boolean advance1 = false;
        boolean advance2 = false;
        boolean uin = introduction();
        
        while(uin == true){
            if(advance1 == false){
                advance1 = execution(1);
            }
            else{
                advance2 = execution(2);
            }
            System.out.println("Input 'q' to quit or any key to restart...");
            String usr = scanner.nextLine();
            if(usr.equals("q")){
                uin = false;
            }
            else{
                uin =true;
            }    
        }
        
        System.out.println("Goodbye...");   
    }
    
    //This function is to welcome the user.
    public static boolean introduction(){
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("  Welcome to Arithmetic's Exercise Program");
        System.out.println("--------------------------------------------");
        System.out.println("You have 90 seconds to answer as many questions as possible.");
        System.out.println("You will get 5 seconds bonus if you answer 5 questions in a row.");
        System.out.println("Use java arithmetic precedence rules to find answers!");
        System.out.println("Press 'q' to quit or any key to start...");
        
        String inp = scanner.next();
        if(inp.equals("q")){
            return false;
        }
        else{
            return true;
        }
    }
    
    //This function is to keep track of time
    public static long timeElapsed(long a, long b){
        long timeElapsed = a - b;
        return timeElapsed;
    }
    
    //This function is for generating operands.
    public static int operands(){
        int operand = (int)(Math.random()*100);
        return operand;
    }
    
    //This one is for generating random operations for the calculations.
    public static String operations(){
        int op =(int)(Math.random() * 4); 
        String sop;
        
        switch(op){
            case 0:
                sop = "+";
                break;
            case 1:
                sop = "-";
                break;
            case 2:
                sop = "*";
                break;    
            default:
                sop = "/";
        }
        
        return sop;
    }
    
    // This function is fır the first level calculations.
    public static long firstlevelcalculation(){
        int a = operands();
        int b = operands();
        String op = operations();
        while(op == "/" && b == 0){
            b = operands();
        }

        System.out.print(a + op + b + " = ");
        long answer ;
        
        switch(op){
            case "+":answer = a + b; break;
            case "-":answer = a - b; break;
            case "*":answer = a * b;break;
            default:answer = a / b;
        }
        
        return answer;    
    }
    
    // This function is fır the first level calculations.
    public static long secondlevelcalculation(){
        int a = operands();
        int b = operands();
        int c = operands();
        String op1 = operations();
        String op2 = operations();
        while((op1 == "/" && b == 0) || (op2 == "/" && c == 0)){
            if(b == 0){
                b = operands();
            }
            if(c == 0){
                c = operands();
            }
        }
        
        System.out.print(a + op1 + b + op2 + c + " = " );
        long answer;
        
        switch(op1){
            case "+":
                switch(op2){
                    case "+": answer = a + b + c; break;
                    case "-": answer = a + b - c; break;
                    case "*": answer = a + b * c; break;
                    default: answer = a + b / c;
                }break;
            case "-":
                switch(op2){
                    case "+": answer = a - b + c; break;
                    case "-": answer = a - b - c; break;
                    case "*": answer = a - b * c; break;
                    default: answer = a - b / c;
                }break;
            case "*":
                switch(op2){
                    case "+": answer = a * b + c; break;
                    case "-": answer = a * b - c; break;
                    case "*": answer = a * b * c; break;
                    default: answer = a * b / c;
                }break;
            default:
                switch(op2){
                    case "+": answer = a / b + c; break;
                    case "-": answer = a / b - c; break;
                    case "*": answer = a / b * c; break;
                    default: answer = a / b / c;
                }
        }
        
        return answer;
    }
    
    //This function executes the exercise.
    public static boolean execution(int l){
        Scanner scanner = new Scanner(System.in);
        
        int totalQ = 0;
        int correctA = 0;
        int correctAR = 0;
        long timeElapsed = 0;
        long maxTime = 90000;
        boolean level = false;
        
        do{
        long t0 = System.currentTimeMillis();
        long canswer;
        if(l == 1){
            canswer = firstlevelcalculation();
        }
        else{
            canswer = secondlevelcalculation();
        }
       
        totalQ ++;
        int answer = scanner.nextInt();
        if(answer == canswer){
            System.out.print("Correct!");
            System.out.println();
            correctA ++;correctAR++;
            if(correctAR % 5 == 0){
                maxTime += 5000;
                System.out.println("Five questions in a row!!! 5 seconds are added to your time.");
            }
        
            if(correctAR %3 == 0){
                starpattern(correctAR);
            }
        }
        
        else{
            System.out.print("Wrong!");
            System.out.println();
            correctAR = 0;
        }
        
        long t1 = System.currentTimeMillis();
        timeElapsed += timeElapsed(t1,t0);
        }while(timeElapsed < maxTime);
        
        System.out.println("TIME IS UP !");
        System.out.println();
        System.out.println("Correct Answers: " + correctA);
        System.out.println("Total Questions: " + totalQ);
        System.out.println("Total Time: " + maxTime/1000 + " seconds");
        System.out.println();
        
        if(correctA/(double)totalQ > 0.6){
            level = true;
            if(l == 2){
                System.out.println("Congratulations!!! You completed the program...");
            }
            else{
                System.out.println("Congratulations!!! You can advance to the next level!");
                System.out.println();
            } 
        }
        else{
            if(l == 2){
                System.out.println("Sorry!!! You could not complete the program...");
            }
            else{
                System.out.println("Sorry!!! You cannot advance to the next level !");
            }
        }
        
        return level;
    }
    
    //This one is for printing message to congratulate correct answers in a row.
    public static void starpattern(int num){
        
        if(num <= 6){
            if(num == 3){
            System.out.println(" * * *   GOOD  * * *");
        }
        else if(num == 6){
            System.out.println(" * * * * * *                     VERY GOOD                        * * * * * *");
        }
        }
        else{
            num = 6;
            String cong; 
            int con = (int)(Math.random()*5);
            switch(con){
                case 0:cong = "AMAZINGGG";break;
                case 1:cong = "GREATTTTT";break;
                case 2:cong = "STUNNINGG";break;
                case 3:cong = "SUPERBBBB";break;
                default:cong = "GOOD JOBB";
            }
            
            System.out.println(" * * * * * *                       "+cong+"                      * * * * * *");
        }
        
        for(int i = num; i >= 1; i--){
            for(int j = 1; j <= num; j++){
                System.out.print(" ");
                for(int l = num; l > i; l--){
                    System.out.print(" ");
                }
                for(int k = 1; k <= i; k++){
                    System.out.print("* ");
                }
                for(int l = num; l > i; l--){
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

}
    
    
    
    
    

    

