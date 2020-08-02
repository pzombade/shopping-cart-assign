package com.pzombade.shoppingcart;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CalculatorUtil{

    private static Map<String,ArrayList<DiscountSlab>> discountMap = new HashMap<>();
    public static long getTotalDiscount(String type, long billAmount) {
        //type = "@"+type;
        if(!type.startsWith("@")){
            type = "@" + type;
        }

        long tempAmount = billAmount;
        ArrayList<DiscountSlab> list = discountMap.get(type);
        long dis =0;

        if(!isApplicableForDiscount(billAmount,list.get(0))){
            return 0;
        }

        boolean terminateLoop = false;

        for (int i = 0; i < list.size() && !terminateLoop; i++) {
            DiscountSlab range = list.get(i);
            long discountPercent = range.getDiscountPercentage();
            boolean isBelowMax = billAmount > range.getMin() && billAmount <= range.getMax();

            if (range.isLastSlab() || isBelowMax) {
                dis += (billAmount - range.getMin()) * discountPercent / 100;
                terminateLoop = true;
            } else {
                dis += (range.getMax() - range.getMin()) * discountPercent / 100;
            }
        }
        return  dis;
    }

    public static boolean isValidUserType(String type){
        return discountMap.containsKey(type);
    }

    public static Set<String> getUserTypes(){
        return discountMap.keySet();
    }

    public static void loadDiscounts(String discountFilePath) throws IOException {
        String customerType = "";
        String line = "";

        try(BufferedReader br = new BufferedReader(new FileReader(discountFilePath))) {
            while ((line = br.readLine()) != null){
                line = line.trim();
                if(line.length() > 0){
                 //   System.out.println(line);
                    if(line.startsWith("@")){
                        customerType = line;
                        discountMap.put(customerType, new ArrayList<DiscountSlab>());
                    }else{
                        discountMap.get(customerType).add(parseRow(line));
                    }
                }
            }
        }finally{

        }
      //  System.out.println("discountMap :: " + discountMap);
    }

    public static DiscountSlab parseRow(String line) {
        String[] arr = line.split(":");
        int min = Integer.parseInt(arr[0]);
        int max = Integer.parseInt(arr[1]);

        DiscountSlab range = range = new DiscountSlab();
        range.setMin(min);

        if(arr.length==2){
            int discount = Integer.parseInt(arr[1]);
            range.setDiscountPercentage(discount);
            range.setLastSlab(true);
        }else{
            int discount = Integer.parseInt(arr[2]);
            range.setDiscountPercentage(discount);
            range.setMax(max);
        }
        return range;
    }

    public static long calculateTotalAmount(String type, long billAmount) {
        System.out.println(type+" + Bill Amount " + billAmount);
        long discount = getTotalDiscount(type,billAmount);
        System.out.println("Discount " + discount);
        long tobePaid = billAmount - discount;
        System.out.println("Discounted Bill amount " + tobePaid + "\n\n");
        return tobePaid;
    }

    private static boolean isApplicableForDiscount(long billAmount, DiscountSlab discountSlab) {
        boolean applicable =  billAmount >= discountSlab.getMax();
        // System.out.println("Is applicable for discount " + applicable);
        return applicable;
    }
}