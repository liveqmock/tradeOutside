package com.github.cruiser.tradeoutside.input;
import java.io.*;
import java.lang.reflect.*;
import java.util.List;
import java.util.ArrayList;
import java.math.*;

import com.github.cruiser.tradeoutside.model.TradeDcc;
import com.github.cruiser.tradeoutside.trade.Trade;

public class DccInputFile extends InputFile {

	private List<Trade> trades = new ArrayList<Trade>();
	
	@Override
	public List<Trade> getTradeList(String fileName) throws Exception{

		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String oneline = null;

		try{
			while(null!=(oneline=br.readLine())){
				//trades.add(DccInputFile.fillerByFixedLength(oneline));
			}
			return trades;
		}finally{
				if(null!=br){
					br.close();
				}
		}
	}
	
	public static TradeDcc fillerByFixedLength(String oneline) throws Exception{

		int fillerLength = 1;//分隔长度

		String[] fieldName = {"TxnTim", "TxnCod", "SeqNum", "TermId",
				"AcpAdr", "ActNo", "ValDat", "TxnAmt",
				"Tips", "aRspCd", "TxnDat", "BusiNo",
				"CrdTyp", "ActDat"};//各字段set方法名称

		int[] fieldFixedLength = {6,4,12,8,
				40,19,4,12,
				12,6,8,15,
				1,8};//各字段长度

		Class<?>[][] fieldType = {{String.class}, {String.class}, {String.class}, {String.class},
				{String.class}, {String.class}, {String.class}, {BigDecimal.class},
				{String.class}, {String.class}, {String.class}, {String.class},
				{String.class}, {String.class}
				};//set方法对应的参数类型

		Class<?> _reflectClass = Class.forName("com.github.cruiser.tradeoutside.trade.TradeDcc");
		TradeDcc reflectClass = (TradeDcc) _reflectClass.newInstance();

		int fieldStartPosition = 0;//用于标识各字段开头位置

		/*
		 * 使用反射对各字段进行赋值
		 */
		for(int indexOfField=0; indexOfField< fieldFixedLength.length; indexOfField++){

			StringBuffer methodName = new StringBuffer();
			methodName.append("set").append( fieldName[indexOfField] );

			Method _reflectMethod = _reflectClass.getMethod(methodName.toString(),
					fieldType[indexOfField]);

			StringBuffer fieldValue = new StringBuffer();
			fieldValue.append(oneline.substring(fieldStartPosition, fieldStartPosition+fieldFixedLength[indexOfField]));

			fieldStartPosition+=fieldFixedLength[indexOfField]+fillerLength;
			Object[] parmValue = InputFile.getFieldArray(fieldValue.toString(), fieldType[indexOfField][0]);//= {fieldValue.toString()};

			_reflectMethod.invoke(reflectClass, parmValue);

		}
		
		return reflectClass;
	}

}
