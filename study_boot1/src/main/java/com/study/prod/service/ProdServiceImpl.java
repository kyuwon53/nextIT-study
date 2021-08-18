package com.study.prod.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.prod.dao.IProdDao;
import com.study.prod.vo.ProdVO;

@Service
public class ProdServiceImpl {
	@Autowired
	private IProdDao prodDao;
	
	public List<ProdVO> getProdList() {
		return prodDao.getProdList();
	}
	
	public ProdVO getProd(String prodId) {
		return prodDao.getProd(prodId);
	}
}//class
