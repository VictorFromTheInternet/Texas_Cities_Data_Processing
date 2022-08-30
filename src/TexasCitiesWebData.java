import java.io.*;
import java.util.*;
import javax.swing.*;

class TexasCitiesWebData {
  
  // main
  public static void main(String[] args) throws FileNotFoundException{
    PrintWriter outfile = new PrintWriter("output_file.txt");
      
    // create TexasCity ArrayList to store input
    ArrayList<TexasCity> TexasCitiesList = new ArrayList<TexasCity>();

    // read file, input to list
    readFile(TexasCitiesList);

    // output ArrayList (unordered) 
    for(TexasCity city : TexasCitiesList){
      System.out.println(city);
    }

    // create new array for sorting and searching
    TexasCity[] TexasCitiesArray = new TexasCity[TexasCitiesList.size()];
    TexasCitiesList.toArray(TexasCitiesArray);
    
    // prompt for sorting (alphabetically, 2020 pop, growth)
    String prompt1 = String.format("How would you like to sort the list: \n") +
                    String.format("1: Ascending order, A-Z\n") +
                    String.format("2: Descending order, Z-A\n");
    String prompt2 = String.format("By what value: \n") +
                    String.format("1: Name\n") +
                    String.format("2: 2010 population\n")+
                    String.format("3: 2020 population\n")+
                    String.format("4: Growth\n");
    
    int choice1 = Integer.parseInt(JOptionPane.showInputDialog(prompt1));
    int choice2 = Integer.parseInt(JOptionPane.showInputDialog(prompt2));

    // 8 possible sorting combinations
    if(choice1 == 1){ // Ascending
        switch(choice2){
            case 1: // name
                String[] nameArr = new String[TexasCitiesArray.length];
                for(int i = 0; i < TexasCitiesArray.length; i++)
                    nameArr[i] = TexasCitiesArray[i].getName();
                quickSort(TexasCitiesArray, nameArr, true, 0, TexasCitiesArray.length-1);
                break;
            case 2: // 2010 pop
                Integer[] pop2010Arr = new Integer[TexasCitiesArray.length];
                for(int i = 0; i < TexasCitiesArray.length; i++)
                    pop2010Arr[i] = TexasCitiesArray[i].getPop2010();
                quickSort(TexasCitiesArray, pop2010Arr, true, 0, TexasCitiesArray.length-1);
                break;
            case 3: // 2020 pop
                Integer[] pop2020Arr = new Integer[TexasCitiesArray.length];
                for(int i = 0; i < TexasCitiesArray.length; i++)
                    pop2020Arr[i] = TexasCitiesArray[i].getPop2020();
                quickSort(TexasCitiesArray, pop2020Arr, true, 0, TexasCitiesArray.length-1);
                break;
            case 4: // growth
                Double[] growthArr = new Double[TexasCitiesArray.length];
                for(int i = 0; i < TexasCitiesArray.length; i++)
                    growthArr[i] = TexasCitiesArray[i].getGrowth();
                quickSort(TexasCitiesArray, growthArr, true, 0, TexasCitiesArray.length-1);
                break;
        }
    }
        
    else if(choice1 == 2){
        switch(choice2){ // Descending
            case 1: // name
                String[] nameArr = new String[TexasCitiesArray.length];
                for(int i = 0; i < TexasCitiesArray.length; i++)
                    nameArr[i] = TexasCitiesArray[i].getName();
                quickSort(TexasCitiesArray, nameArr, false, 0, TexasCitiesArray.length-1);
                break;
            case 2: // 2010 pop
                Integer[] pop2010Arr = new Integer[TexasCitiesArray.length];
                for(int i = 0; i < TexasCitiesArray.length; i++)
                    pop2010Arr[i] = TexasCitiesArray[i].getPop2010();
                quickSort(TexasCitiesArray, pop2010Arr, false, 0, TexasCitiesArray.length-1);
                break;
            case 3: // 2020 pop
                Integer[] pop2020Arr = new Integer[TexasCitiesArray.length];
                for(int i = 0; i < TexasCitiesArray.length; i++)
                    pop2020Arr[i] = TexasCitiesArray[i].getPop2020();
                quickSort(TexasCitiesArray, pop2020Arr, false, 0, TexasCitiesArray.length-1);
                break;
            case 4: // growth
                Double[] growthArr = new Double[TexasCitiesArray.length];
                for(int i = 0; i < TexasCitiesArray.length; i++)
                    growthArr[i] = TexasCitiesArray[i].getGrowth();
                quickSort(TexasCitiesArray, growthArr, false, 0, TexasCitiesArray.length-1);
                break;
        }
    }
    
    // output message & array
    String str = String.format("Sorting complete, check 'output_file.txt'");
    JOptionPane.showMessageDialog(null, str, "Quick Sort", JOptionPane.INFORMATION_MESSAGE);
    
    for(TexasCity city : TexasCitiesArray){
      outfile.println(city);
    }
    
    
        // sort list 
        String[] nameArr = new String[TexasCitiesArray.length];
        for(int i = 0; i < TexasCitiesArray.length; i++)
                    nameArr[i] = TexasCitiesArray[i].getName();
        quickSort(TexasCitiesArray, nameArr, true, 0, TexasCitiesArray.length-1); // sort A-Z rq
    
    // prompt: search for city (name)
    String searchItem = JOptionPane.showInputDialog("Enter a name to search for: ");
    binarySearch(TexasCitiesArray, 0, TexasCitiesArray.length, searchItem);
    
    
    outfile.close();
  } // end of main

  // read file (pass in TexasCity ArrayList)
  public static void readFile(ArrayList<TexasCity> list) throws FileNotFoundException{
    Scanner infile = new Scanner(new FileReader("TexasCitiesWebData.csv"));  

    // step through the file
    while(infile.hasNext()){
      // create a temp city
      TexasCity temp = new TexasCity();
      
      // grab a line, input as separate strings in array
      // index 0=Name, 1=2010pop, 2=2020pop
      String[] strArr = new String[3];
      String str = infile.nextLine();
      strArr = str.split(",", 3);
      
        // test input
        /*
        System.out.print(strArr[0]+" "+strArr[1]+" "+strArr[2]+" ");
            String emptyTest1 = (strArr[1].length() == 0) ? " --------------- 2010 empty":" full";
            String emptyTest2 = (strArr[2].length() == 0) ? " --------------- 2020 empty":" full";
            System.out.print(emptyTest1+emptyTest2+"\n");
        */
      
      // set name
      temp.setName(strArr[0]);
      
      // set 2010, 2020 pop
      // check for blank data, if blank init to 0
      int num1=0, num2=0;
      
      if(strArr[1].length()==0)
          temp.setPop2010(0);
      else{
          num1 = Integer.parseInt(strArr[1]);
          temp.setPop2010(num1);  
      }
      
      if(strArr[2].length()==0)
          temp.setPop2020(0);
      else{
          num2 = Integer.parseInt(strArr[2]);
          temp.setPop2020(num2);  
      }
      
      list.add(temp);
    }
    
    infile.close();
  }// end of readFile()

  // sorting function, sorts arrays in parallel
  public static <Type extends Comparable> void quickSort(TexasCity[] TexasCitiesArr, 
          Type[] TypeArr, boolean AtoZ, int l, int r){
    
    if( l < r){
        // find pivot's index
        int pivot = partition(TexasCitiesArr, TypeArr, AtoZ, l, r);
        
        quickSort(TexasCitiesArr, TypeArr, AtoZ, l, pivot-1); // sort left side
        quickSort(TexasCitiesArr, TypeArr, AtoZ, pivot+1, r); // sort right side
    }
    
  } // end of quickSort
  
  public static <Type extends Comparable> int partition(
          TexasCity[] TexasCitiesArr, Type[] TypeArr, boolean AtoZ, int l, int r){
    Type pivot = TypeArr[r];
    int J = l;   // 'J' will step through arr
    int I = l-1; // 'I' will be pivot's final index
    
    for(J = l; J < TypeArr.length; J++){
        // boolean AtoZ implemented here
        
        // Ascending
        if(AtoZ == true){
            // if J < pivot 
            if(TypeArr[J].compareTo(pivot) < 0){
                I++;
                swap(TexasCitiesArr,TypeArr, J, I);
                //swap(TexasCitiesArr, J, I);
            }
        }
        
        // Descending
        else{
            // if J > pivot 
            if(TypeArr[J].compareTo(pivot) > 0){
                I++;
                swap(TexasCitiesArr, TypeArr, J, I);
                //swap(TexasCitiesArr,I,J);
            }
        }
        
    }
    
    // swap TypeArr and TexasCitiesArr indexes
    I++;
    swap(TexasCitiesArr, TypeArr, I, r);
    //swap(TexasCitiesArr, I, r);

    return I;
  } // end of partition
  
  static public <Type extends Comparable> void swap(TexasCity[] TexasCitiesArr, Type[] TypeArr, int l, int r){
      Type temp = TypeArr[l];
      TypeArr[l] = TypeArr[r];
      TypeArr[r] = temp;
      
      TexasCity tempCity = TexasCitiesArr[l];
      TexasCitiesArr[l] = TexasCitiesArr[r];
      TexasCitiesArr[r] = tempCity;
  } // end of swap
  
  
  // searching function
  public static void binarySearch(TexasCity[] arr, int l, int r, String searchItem){
    if(r >= l){
      int mid = (l+r) /2;
      String name = arr[mid].getName();
      int result = searchItem.compareTo(name);
      //System.out.println(result);
      
      // check mid
      if(0 == result){
        String str = String.format("Search item '"+searchItem+"', was found at index: "+mid+"\n")+
                      String.format(arr[mid].toString());
        JOptionPane.showMessageDialog(null, str, "Binary Search", JOptionPane.INFORMATION_MESSAGE);
      }

      // search left
      else if(result < 0){
        binarySearch( arr, l, mid-1, searchItem);
      }

      // search right
      else{
        binarySearch( arr, mid+1, r, searchItem);
      }
      
    }
    
    else{
    JOptionPane.showMessageDialog(null, "Search item: "+searchItem+" NOT found",
                                    "Binary Search", JOptionPane.INFORMATION_MESSAGE);
    }
  } // end of binary search
  
}