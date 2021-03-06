package WordCount;

import mapreduce.*;
import java.io.*;

public class Main {

	/**
	 * WordCount
	 * 引数で与えられたファイル中に出てくる単語の数を数える
	 * 引数でファイルが与えられない場合はこのソースコード中の単語数をカウントする
	 * @param args 第一引数にファイルのパスを格納するための配列
	 */
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String filename;
		if(args.length > 1){
			filename = args[2];
		}
		else{
			filename = "";
		}
			
		MapReduce<Integer, String, String, Integer, String, Integer> wcMR = new MapReduce<Integer, String, String, Integer, String, Integer>(MapWC.class, ReduceWC.class, "MAP_REDUCE");
		wcMR.setParallelThreadNum(6);
		
		//初期値をMapReduceに渡す
		try{
			FileReader file = new FileReader(filename);
			BufferedReader buffer = new BufferedReader(file);
			String s;
			while((s = buffer.readLine())!=null){
				wcMR.addKeyValue(0 , s);
			}
		}catch(Exception e){
			System.err.println("ファイル読み込み失敗");
	    }
		
		wcMR.run();				
	}

}
