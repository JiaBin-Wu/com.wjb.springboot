package com.wjb.springboot.service;

import com.wjb.springboot.service.ex.AddressNotFoundException;
import com.wjb.springboot.service.ex.CartNotFoundException;
import com.wjb.springboot.service.ex.InsertException;

public interface IOrderService {
	void createOrder(Integer aid,Integer[] cids, Integer uid,String username) throws InsertException,CartNotFoundException,AddressNotFoundException ;
}
