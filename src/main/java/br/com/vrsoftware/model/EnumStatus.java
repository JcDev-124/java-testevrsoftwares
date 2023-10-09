/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package br.com.vrsoftware.model;

/**
 *
 * @author Julio
 */
public enum EnumStatus {
	FINALIZADO(1),
	DIGITANDO(2);

	
	private int code;
	
	private EnumStatus(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
	public static EnumStatus valueOf(int code) {
		for(EnumStatus value: EnumStatus.values()) {
			if(value.getCode() == code)
				return value;
		}
		
		throw new IllegalArgumentException("Invalid OrderStatus code");
	}
}
