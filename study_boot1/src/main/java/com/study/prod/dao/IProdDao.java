package com.study.prod.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.study.prod.vo.ProdVO;

@Mapper
public interface IProdDao {
	
	List<ProdVO> getProdList();
	ProdVO getProd(String prodId);
	
}
