package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;



@Controller
public class BullsAndCows {
	//@Autowired //これを使えばコンストラクタを省略できる
	//HttpSession session; // セッション展開
	private HttpSession session; //sessionの定義と領域の作成
    public BullsAndCows(HttpSession session){ //コンストラクタ
        this.session = session; //クラス内でセッションを参照できるように、sessionフィールドを初期化している。
    }
    @GetMapping("/")
	public String init() {
		session.invalidate(); //セッション初期化
		Random rnd = new Random(); //答えを作成
		List answer_material = new ArrayList<String>();
		for(int i = 0; i<10;i++) {//0~9の数値を代入
			answer_material.add(String.valueOf(i)); 
		}
		String answer_number = "";
		for (int i = 9; 5<i;i--) {
			int get_array_number = rnd.nextInt(i+1); //乱数の値が要素数
			answer_number += answer_material.get(get_array_number); //整数を生成
			answer_material.subList(get_array_number, get_array_number+1).clear();//値が重複しないように配列をスライスする
		}
		System.out.println(answer_number);
		session.setAttribute("answer",answer_number);//答えをセッションに格納
		return "game";
	}
    @GetMapping("/answer")
    public ModelAndView answer (ModelAndView mv) {
    	String answer_number = "";
    	answer_number = (String)session.getAttribute("answer");
    	List<History>histories = (List<History>)session.getAttribute("histories");
		mv.setViewName("giveup");
		mv.addObject("answer",answer_number);
		mv.addObject("histories",histories);
		
		session.invalidate(); //セッション初期化
    	return mv;
    }
	@PostMapping("/challenge")
	public ModelAndView challenge(@RequestParam("number")String number,ModelAndView mv) {
		String answer = (String)session.getAttribute("answer"); // 答えを取得
		@SuppressWarnings("unchecked")
		List<History>histories = (List<History>)session.getAttribute("histories");
		int bulls = 0;
		int cows = 0;
		for (int i = 0;i<4;i++) {
			char answer_char = answer.charAt(i);
			for (int j = 0;j<4;j++) {
				char your_char = number.charAt(j);
				if((answer_char == your_char)&&(i==j)) { //bulls
					bulls += 1;
				}else if(answer_char == your_char) { //cows
					cows += 1;
				}
			}
		}
		if (histories == null) {
			histories = new ArrayList<>(); //結果を履歴へ格納
			session.setAttribute("histories", histories);			
		}
		if (bulls == 4){
			histories.add(new History(histories.size()+1,number,bulls,cows,"正解"));
			mv.setViewName("clear");
		}else {
			histories.add(new History(histories.size()+1,number,bulls,cows,"不正解"));
			mv.setViewName("game");
		}
		mv.addObject("histories",histories);
		return mv;
	}
}
