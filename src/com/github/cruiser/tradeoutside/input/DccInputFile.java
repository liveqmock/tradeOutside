package com.github.cruiser.tradeoutside.input;
import java.io.*;
import java.lang.reflect.*;
import java.util.List;
import java.util.ArrayList;

import com.github.cruiser.tradeoutside.trade.Trade;
import com.github.cruiser.tradeoutside.trade.TradeDcc;

public class DccInputFile implements InputFile {

	private int fillerLength = 1;
	private String[] fieldName = {"TxnTim", "TxnCod", "SeqNum", "TermId",
			"AcpAdr", "ActNo", "ValDat", "TxnAmt",
			"Tips", "aRspCd", "TxnDat", "BusiNo",
			"CrdTyp", "ActDat"};//
	private int[] fieldFixedLength = {6,4,12,8,40,19,4,12,12,6,8,15,1,8};//各字段长度
	private Class<?>[][] fieldType = {{String.class}, {String.class}, {String.class}, {String.class},
			{String.class}, {String.class}, {String.class}, {String.class},
			{String.class}, {String.class}, {String.class}, {String.class},
			{String.class}, {String.class}
			};
	private List<Trade> trades = new ArrayList<Trade>();
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
//		File inputFile = new File(".");
//		System.out.println(inputFile.getCanonicalPath());
		BufferedReader br = new BufferedReader(new FileReader("tmp/BOCOM_PIF_20120524.txt"));
		String oneline = null;
		try{
			while(null!=(oneline=br.readLine())){
				System.out.println(oneline);
				new DccInputFile().fillerByFixedLength(oneline);
			}
			
		}finally{
			if(null!=br){
				br.close();
			}
		}
		//new InputFile().fillerByFixedLength(br.readLine());
	}

	/*public TradeDcc _fillerByFixedLength(String oneline) throws Exception{
		//Map<String, Integer> fields_begin = new HashMap<String, Integer>();
		//Map<String, Integer> fields_end = new HashMap<String, Integer>();
		//TradeDcc _tmp = new TradeDcc();
		Class _tmp = Class.forName("test.TradeDcc");
		TradeDcc tmp = (TradeDcc)_tmp.newInstance();
		Class[] parmType = {String.class};
		Method _tmp_method = _tmp.getMethod("set"+fieldName[0].substring(0,1).toUpperCase()+fieldName[0].substring(1), parmType);
		Object[] parmValue = {"xxxx"};
		_tmp_method.invoke(tmp, parmValue);
		System.out.println(tmp);
		//TradeDcc tmp = (Class<TradeDcc>)_tmp;
		return tmp;
	}*/
	
	public TradeDcc fillerByFixedLength(String oneline) throws Exception{
		Class<?> _tmp = Class.forName("test.TradeDcc");
		TradeDcc tmp = (TradeDcc)_tmp.newInstance();
		//Class<?>[] parmType = {String.class};

		int index = 0;
		for(int i=0; i< fieldFixedLength.length; i++){

			StringBuffer methodName = new StringBuffer();
			methodName.append("set").append(fieldName[i]);
			System.out.println("methodName: "+methodName.toString());
			Method _tmp_method = _tmp.getMethod(methodName.toString(), fieldType[i]);
			StringBuffer methodValue = new StringBuffer();
			methodValue.append(oneline.substring(index, index+fieldFixedLength[i]));
			System.out.println("methodValue: "+methodValue.toString());
			index+=fieldFixedLength[i]+fillerLength;
			Object[] parmValue = {methodValue.toString()};
			_tmp_method.invoke(tmp, parmValue);
			System.out.println(tmp);
			System.out.println(tmp.getTxnAmtInDecimal());
			
		}
		
		return new TradeDcc();
	}

	@Override
	public List<Trade> getTradeList() {
		return null;
	}
}
