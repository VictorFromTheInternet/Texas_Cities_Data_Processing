
class TexasCity
{
  // instance variables
  String name;
  int pop2010;
  int pop2020;
  double growth;

  TexasCity(){
    name = "Empty";
    pop2010 = 0;
    pop2020 = 0;
    growth = 0;
  }
  TexasCity(String n, int num1, int num2, double rate){
    name = n;
    pop2010 = num1;
    pop2020 = num2;
    growth = rate;
  }

  // setters & getters
  void setName(String n){ name = n;}
  String getName(){ return name;}

  void setPop2010(int num){ pop2010 = num;}
  int getPop2010(){return pop2010;}

  void setPop2020(int num){ pop2020 = num;}
  int getPop2020(){ return pop2020;}

  void setGrowth(double rate){ growth = rate;}
  double getGrowth(){return growth;}

  @Override
  public String toString(){  
      
    String str = String.format("%1$-12s : %2$-25s\n","Name",name) +
                String.format("%1$-12s : %2$-25d\n","2010 pop",pop2010)+
                String.format("%1$-12s : %2$-25d\n","2020 pop",pop2020);
    
    if(pop2010==0 || pop2020==0){
        growth =0; 
        str += String.format("%1$-12s : %2$-5s\n","Growth","N/A");
    }
    else{
        growth = (((double)pop2020/(double)pop2010)-1)*100;
        str += String.format("%1$-12s : %2$-5.3f percent\n","Growth",growth);
    }
        
    return(str);
  }
  
  
}