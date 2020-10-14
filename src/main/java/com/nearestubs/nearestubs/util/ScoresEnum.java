package com.nearestubs.nearestubs.util;

public enum ScoresEnum {
    A("Desempenho mediano ou  um pouco abaixo da média"),
    B("Desempenho acima da média"),
    C("Desempenho muito acima da média");
	 
    public String score;
	ScoresEnum(String valor) {
		score = valor;
    }
	
	public int getScore() {
		return ScoresEnum.valueOf(score).ordinal();
	}
        
}
