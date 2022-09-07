package com.lpyu.effective;

import java.util.Objects;

public class CaseInsensitiveString {
    private final String  s;
    public CaseInsensitiveString (String s){
        this.s= Objects.requireNonNull(s);
    }
    @Override
    public boolean equals(Object o){
        if(o instanceof CaseInsensitiveString)
            return s.equalsIgnoreCase(((CaseInsensitiveString)o).s);

        if(o instanceof String)
           return s.equalsIgnoreCase((String)o);
        return false;
    }
 public static  void  main(String args[]){
       CaseInsensitiveString cis= new CaseInsensitiveString("Polish");
       String s="polish";
       boolean a=cis.equals(s);
       boolean b=s.equals(cis);
     System.out.println(b);
 }
}
