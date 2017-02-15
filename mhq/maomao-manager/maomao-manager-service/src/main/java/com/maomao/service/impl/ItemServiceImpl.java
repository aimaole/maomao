package com.maomao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maomao.mapper.TbItemMapper;
import com.maomao.pojo.TbItem;
import com.maomao.pojo.TbItemExample;
import com.maomao.pojo.TbItemExample.Criteria;
import com.maomao.service.ItemService;
@Service("itemService")
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;
	
	@Override
	public TbItem getItemById(long itemId) {
		
		//TbItem item = itemMapper.selectByPrimaryKey(itemId);
		//添加查询条件
		TbItemExample example = new TbItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(itemId);		
		List<TbItem> list = itemMapper.selectByExample(example);
		if(list != null && list.size() > 0){
			TbItem item = list.get(0);
			return item;			
		}
		
		return null;
	}

}
