package com.nps.transformer;

import org.apache.log4j.Logger;
import org.finra.datagenerator.consumer.DataPipe;
import org.finra.datagenerator.consumer.DataTransformer;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

public class TempTransformer implements DataTransformer {
	private static final Logger log = Logger.getLogger(TempTransformer.class);
	//private final Random rand = new Random(System.currentTimeMillis());
	public static int count = 0;
	@Override
	public void transform(DataPipe cr) {
		/*
		for (Map.Entry<String, String> entry : cr.getDataMap().entrySet()) {
			String value = entry.getValue();
			if(value.equals("#{size1}")){//1<sizeOFBox.small<10
				entry.setValue(String.valueOf((int)(Math.random()*9) + 1));
			}else if(value.equals("#{size2}")){//10<sizeOFBox.medium<100
				entry.setValue(String.valueOf((int)(Math.random()*90) + 10));
			} else if(value.equals("#{size3}")){//100<sizeOFBox.large<1000
				entry.setValue(String.valueOf((int)(Math.random()*900) + 100));
			}
			count++;
			//System.out.println("--------"+count+"-------------");
			System.out.println("--------"+entry.getKey()+"------"+entry.getValue()+"-------");
		}
	 */
		Iterator<Entry<String, String>> it = cr.getDataMap().entrySet().iterator();
		int rand1 = (int)(Math.random()*9) + 1 ;;
		int rand2 = (int)(Math.random()*90) + 10 ;;
		int rand3 = (int)(Math.random()*900) + 100 ;
		while( it.hasNext()){
			
			Entry<String, String> entry = (Entry<String, String>) it.next();
			StringBuilder sb  = new StringBuilder();
			sb.append(entry.getValue());
			if(sb.toString().equals("#{size1}")){//1<sizeOFBox.small<10
				entry.setValue(String.valueOf(rand1));
			}else
			if(sb.toString().equals("#{size2}")){//10<sizeOFBox.medium<100
				entry.setValue(String.valueOf(rand2));
			}else
			if(sb.toString().equals("#{size3}")){//100<sizeOFBox.large<1000
				entry.setValue(String.valueOf(rand3));
			}
			count++;
		} 
		
	}
	public int count(){
		return count;
	}
}
