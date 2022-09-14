package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountSearchRequest {

	
	 /**
     * ユーザー名
     */
    private String name;
    /**
     * パスワード
     */
    private String password;
}
	

