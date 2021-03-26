package com.aminalipour.code;

import org.pitest.bytecode.analysis.ClassTree;
import org.pitest.mutationtest.build.InterceptorType;
import org.pitest.mutationtest.build.MutationInterceptor;
import org.pitest.mutationtest.engine.Mutater;
import org.pitest.mutationtest.engine.MutationDetails;
import org.pitest.coverage.ClassLine;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.Stream;


import static java.lang.Integer.parseInt;
import static java.lang.String.format;
import static org.pitest.mutationtest.build.InterceptorType.FILTER;

/**
 * Hello world!
 */
public  class OnePerLine implements MutationInterceptor{
 
   
    @Override
    public void begin(ClassTree classTree) {
        System.out.println("===============================================");        
        System.out.println("============ONE PER LINE FILTER STARTED========");
        System.out.println("===============================================");       
        
    }

    @Override
    public void end() {
        public void begin(ClassTree classTree) {
            System.out.println("===============================================");        
            System.out.println("============ONE PER LINE FILTER ENDED==========");
            System.out.println("===============================================");        
            
        }
            
    }



    @Override
    public Collection<MutationDetails> intercept(Collection<MutationDetails> mutationDetails, Mutater mutater) {
        // Returns one mutation per 
        System.out.println("************************************************************");
        System.out.println("Initial Number of Mutants:"  + mutationDetails.size());
        System.out.println("************************************************************");
        
        ArrayList<MutationDetails> result = new ArrayList<>();



        ArrayList<MutationDetails> aList = new ArrayList<>(mutationDetails);
        HashMap<ClassLine, ArrayList<MutationDetails>> hmap = new HashMap<ClassLine, ArrayList<MutationDetails>>();
        for (MutationDetails m: aList){
            if(hmap.get(m.getClassLine()) != null){
                hmap.put(m.getClassLine(),new ArrayList<MutationDetails>());
            }
            hmap.get(m.getClassLine()).add(m);
        }
        Random rndm = new Random();

        for (ClassLine cLine: hmap.keySet()){
            ArrayList<MutationDetails> al = hmap.get(cLine);
            int size = al.size();
            MutationDetails chosen  = al.get(rndm.nextInt(size));
            result.add(chosen);
        }
        System.out.println("************************************************************");
        System.out.println("FINAL Number of Mutants:"  + result.size());
        System.out.println("************************************************************");
     
        return result;
    }

    @Override
    public InterceptorType type() {
            return InterceptorType.FILTER;
    }
}
