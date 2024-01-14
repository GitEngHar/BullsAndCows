package com.example.demo;

import lombok.Getter;
@Getter //getクラスをそれぞれ作成してhtmlから値を取得できるようにする
public class History {
	private int seq;
	private String yourAnswer;
	private int bulls;
	private int cows;
	private String result;
	
    public History(int seq,String yourAnswer,int bulls,int cows,String result){
		this.seq = seq;
		this.yourAnswer = yourAnswer;
		this.bulls = bulls;
		this.cows = cows;
		this.result = result;
	}
    
}
