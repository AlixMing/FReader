package com.reader.util;

public class Data2Sqlformat {
	
	public String data2sql(String s){
		s.replace("\n\r", "<br>  ");
		s.replace("\r\n", "<br>  ");
		s.replace("\t", "    ");
		s.replace(" ", " ");
		s.replace("\"", "\\" + "\"");
		return s;
	}
	
	public String sql2data(String s){
		s.replace("<br>  ", "\n\r");
		s.replace("<br>  ", "\r\n");
		s.replace("    ", "\t");
		s.replace("\\" + "\"", "\"");
		return s;
	}
}
