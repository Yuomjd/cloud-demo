package cn.itcast.order.service;

import cn.itcast.order.fegin.UserClient;
import cn.itcast.order.mapper.OrderMapper;
import cn.itcast.order.pojo.Order;
import cn.itcast.order.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserClient userClient;

    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);
//         2.利用RestTemplate发送url请求
//        String url = "http://userService/user/"+order.getUserId();
//        User user = restTemplate.getForObject(url, User.class);
//        利用Feign发起http请求，查询用户
        User user = userClient.findById(order.getUserId());
//         3.封装User进去
        order.setUser(user);
        // 4.返回
        return order;
    }
}
