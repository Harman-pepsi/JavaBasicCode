package _01_进制转换;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Classname BaseConversion
 * @Description 进制转换，运用栈实现
 * @Created by XJC·AW
 * @Date 2020/6/27 9:55
 * @Version V1.0.0
 * @Since 1.0
 */
class Stack{
    private int iTop;
    private int[] iArray;

    private static final int DEFAULT_CAPACITY = 10;

    public Stack() {
        this(DEFAULT_CAPACITY);
    }

    public Stack(int iCapacity){
        this.iTop = 0;
        iCapacity = iCapacity > DEFAULT_CAPACITY ? iCapacity : DEFAULT_CAPACITY;
        this.iArray = new int[iCapacity];
    }

    //栈是否满
    public boolean isFull(){
        return iTop == iArray.length;
    }

    //入栈
    public void put(int iElem){
        if(isFull()){
            this.iArray = Arrays.copyOf(iArray,iArray.length + (iArray.length >> 1));
        }
        iArray[iTop] = iElem;
        iTop++;
    }

    public void show(){
        for (int i = iTop - 1; i >= 0 ; i--) {
          switch (iArray[i]){
              case 10: System.out.print("A"); break;
              case 11: System.out.print("B"); break;
              case 12: System.out.print("C"); break;
              case 13: System.out.print("D"); break;
              case 14: System.out.print("E"); break;
              case 15: System.out.print("F"); break;
              default: System.out.print(iArray[i]);
          }
        }
        System.out.println();
    }
}

public class BaseConversion {
    public static void main(String[] args) {
        char cFlag = 'y';
        while(cFlag == 'y'){
            Scanner scanner = new Scanner(System.in);
            System.out.print("输入待转换的数的进制:");
            int iFrom = scanner.nextInt();

            System.out.print("输入需要转换后的进制:");
            int iTo = scanner.nextInt();

            System.out.print("请输入需要转换的数:");
            int iNum = scanner.nextInt();

            if(!judge(iFrom,iNum)){
                System.out.println("你输入的不是"+ iFrom +"进制数,"+ iFrom +"进制数每个数应该小于"+iFrom);
                continue;
            }
            System.out.println(iFrom +"进制数"+ iNum +"转换为"+ iTo +"进制数后为:");
            decimalTooctonary(iTo,octonaryTodecimal(iFrom,iNum)).show();

            System.out.println("是否继续(y/n):");
            cFlag = scanner.next().charAt(0);
        }
        System.out.println("OVER!");
    }

    private static boolean judge(int iFrom,int iNum){
        String str = String.valueOf(iNum);
        char[] chArray = str.toCharArray();
        int iTag = iFrom;
        for (int i = 0; i < chArray.length; i++) {
            if(Integer.valueOf(chArray[i] + "") > iTag - 1){
                return false;
            }
        }
        return true;
    }

    //其它进制转为十进制
    private static int octonaryTodecimal(int iFrom,int iNum){
        int iTag = 0;
        int iIndividualBit = iNum % 10;
        int iSum = 0;
        while (iNum > 0){
            iSum += iIndividualBit * Math.pow(iFrom,iTag);
            iNum = iNum / 10;
            iIndividualBit = iNum % 10;
            iTag++;
        }
        return iSum;
    }

    //十进制转其它进制
    private static Stack decimalTooctonary(int iTo,int iNum) {
        Stack stack = new Stack();
        if (iNum == 0) {
            stack.put(0);
            return stack;
        }
        while (iNum > 0){
            stack.put(iNum % iTo);
            iNum = iNum /iTo;
        }
        return stack;
    }
}
