package com.cxytiandi.sharding.controller;

import org.apache.shardingsphere.api.hint.HintManager;
import org.apache.shardingsphere.core.strategy.route.hint.HintShardingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cxytiandi.sharding.po.User;
import com.cxytiandi.sharding.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/users")
	public Object list() {
	    //读，默认选择从库数据源
        //HintManager.getInstance().setMasterRouteOnly();  //核心，强制查询主库
		return userService.list();
	}
	
	@RequestMapping("/add")
	public Object add() {
		for (long i = 0; i < 100; i++) {
			User user = new User();
			user.setCity("南京");
			user.setName("sync");
			userService.add(user);
		}
		return "success";
	}
	
	@RequestMapping("/users/{id}")
	public Object get(@PathVariable Long id) {
		return userService.findById(id);
	}
	
	@RequestMapping("/users/query")
	public Object get(String name) {
		return userService.findByName(name);
	}
	
}
