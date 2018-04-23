package com.yhxy.utils;
import java.util.ArrayList;
import java.util.List;


public class ChartUtil {
	public static void main(String[] args) {		
		/*System.out.println(getScoSection(45));		*/
		Integer maxRS=39;
		String max=maxRS.toString();
		max=max.substring(0,1);
		Integer a=Integer.valueOf(max);
		Integer b=Integer.valueOf(a+1);
		//String c=b.toString()+"0";
		
		List<Integer> l=new ArrayList<Integer>();
		for(int i=0;i<b+1;i++) {
			l.add(i*10);
		}
		System.out.println(l);
	}
		
	/**
	 * 分数区间
	 * @param sumSco
	 * @return
	 */
	public static List<String> getScoSection(Integer sumSco){
		java.text.DecimalFormat df=new java.text.DecimalFormat("#.00");  
		df.format(sumSco*0.6);
		List<String> l=new ArrayList<String>();
		l.add(df.format(sumSco*0.6)+"分以下");
		l.add(df.format(sumSco*0.6)+"~"+df.format(sumSco*0.69));
		l.add(df.format(sumSco*0.70)+"~"+df.format(sumSco*0.79));
		l.add(df.format(sumSco*0.80)+"~"+df.format(sumSco*0.89));
		l.add(df.format(sumSco*0.90)+"~"+df.format(sumSco*0.99));
		l.add(sumSco.toString());
		return l;
	}
	
	/**
	 * 排除总分跟及格分
	 * @param sumSco
	 * @return
	 */
	public static List<String> getScoSec(Integer sumSco){
		java.text.DecimalFormat df=new java.text.DecimalFormat("#.00");  
//		Double sum=Double.parseDouble(sumSco.toString());//总分
//		Double passes=sumSco*0.6;//及格分
//		Double qj=sumSco*0.1;		
//		//区间
//		double section=qj-sum*0.01;
//		List<Double> scolist=new ArrayList<Double>();
//		int j=1;
//		while(sum>passes) {
//			j=j+1;
//			if((j&1) != 1) {
//				sum=Math.floor(sum-sum*0.01);
//				scolist.add(sum);
//			}else {
//				sum=Math.floor(sum-section);
//				scolist.add(sum);
//			}
//		}
//		
		List<String> l=new ArrayList<String>();
//		
//		for(int i=1;i<=scolist.size()-1;i++) {
//			BigDecimal b1 = new BigDecimal(Double.toString(scolist.get(scolist.size()-i-1)));
//			BigDecimal b2 = new BigDecimal(Double.toString(scolist.get(scolist.size()-i)));
//			if(b1.subtract(b2).doubleValue() != 1) {
//			l.add(scolist.get(scolist.size()-i)+"~"+scolist.get(scolist.size()-i-1));
//			}
//		}
		l.add(df.format(sumSco*0.6)+"~"+df.format(sumSco*0.69));
		l.add(df.format(sumSco*0.70)+"~"+df.format(sumSco*0.79));
		l.add(df.format(sumSco*0.80)+"~"+df.format(sumSco*0.89));
		l.add(df.format(sumSco*0.90)+"~"+df.format(sumSco*0.99));
		return l;
	}
	
	/**
	 * 人数区间
	 * @param sumSco
	 * @return
	 */
	public static List<Integer> getRSQJ(Integer maxRS){
		List<Integer> rslist=new ArrayList<Integer>();
		if(maxRS<=5) {
			for(int i=0;i<=5;i++) {
				rslist.add(i);
			}
		}else if(maxRS>5 && maxRS<10) {
			for(int i=0;i<=10;i++) {
				rslist.add(i);
			}
		}else {
			String max=maxRS.toString();
			max=max.substring(0,1);
			Integer a=Integer.valueOf(max);
			Integer b=Integer.valueOf(a+1);
			//String c=b.toString()+"0";
			for(int i=0;i<b+1;i++) {
				rslist.add(i*10);
			}
		}
		return rslist;
	}
	
}
