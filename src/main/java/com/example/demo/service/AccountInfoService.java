package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.AccountInfoMapper;
import com.example.demo.dto.AccountSearchRequest;
import com.example.demo.entity.AccountInfo;
import com.example.demo.repository.AccountRepository;

/**
 * ユーザー情報 Service
 */
@Service
@Transactional
public class AccountInfoService {

	
	/**
     * ユーザー情報 Mapper
     */
    @Autowired
    private AccountInfoMapper accountInfoMapper;
    
    @Autowired
    AccountRepository accountRepository;
    
    /**
     * ユーザー情報検索
     * @param userSearchRequest リクエストデータ
     * @return 検索結果
     */
    public List<AccountInfo> search(AccountSearchRequest accountSearchRequest) {
        return accountInfoMapper.search(accountSearchRequest);
    }
    
}
