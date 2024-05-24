package com.air.health.user.servcie.impl;

import com.air.health.common.model.AirException;
import com.air.health.common.model.PageModel;
import com.air.health.common.model.ProvinceCountModel;
import com.air.health.common.util.PageUtil;
import com.air.health.user.dao.OrderDao;
import com.air.health.user.entity.OrderEntity;
import com.air.health.user.entity.UserEntity;
import com.air.health.user.dao.UserDao;
import com.air.health.user.model.OrderStatus;
import com.air.health.user.model.OrderType;
import com.air.health.user.servcie.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Title: UserServiceImpl
 * @Author Aircreach
 * @Version 1.0.0
 * @Package com.air.health.member.servcie.impl
 * @Date 2024/4/4 15:50
 * @description:
 */
@Slf4j
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    OrderDao orderDao;

    /**
     * 分页查询
     * @param params
     * @return
     */
    @Override
    public PageModel queryPage(Map<String, Object> params) {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<UserEntity>();
        if (params.get("extra") != null) {
            ArrayList<Map> temp = (ArrayList<Map>) params.get("extra");
            Class<UserEntity> entityClass = UserEntity.class;
            queryWrapper = PageUtil.getQueryWrapper(temp, entityClass);
        }
        IPage<UserEntity> page = this.page(
                PageUtil.getPage(params),
                queryWrapper
        );

        return new PageModel(page);
    }


    @Transactional
    @Override
    public Long purchase(OrderEntity order) {
        UserEntity user = userDao.selectById(order.getUserId());
        order.setStatus(OrderStatus.PAYED);
        orderDao.insert(order);
        Long balance = user.getBalance() - order.getBalance();
        if (balance >= 0) {
            user.setBalance(balance);
            if (order.getType().equals(OrderType.OCCUPANCY)) {
                LambdaUpdateWrapper<UserEntity> updateWrapper = new LambdaUpdateWrapper<>();
                updateWrapper.eq(UserEntity::getUserId, user.getUserId())
                        .set(UserEntity::getInsId, order.getServiceId());
                update(null, updateWrapper);
            }
            order.setPayedDate(LocalDateTime.now(ZoneOffset.UTC));
            order.setStatus(OrderStatus.PENDING);
            orderDao.updateById(order);
            return balance;
        } else {
            throw new AirException("账户余额不足");
        }
    }

    @Override
    public List<ProvinceCountModel> provinceCount() {
        return userDao.getCountByProvince();
    }

    @Override
    public Integer dateCount(LocalDateTime startDate, LocalDateTime endDate) {
        startDate = startDate.withHour(0).withMinute(0).withSecond(0).withNano(0);
        endDate = endDate.withHour(0).withMinute(0).withSecond(0).withNano(0);
        return userDao.getCountsByDate(startDate, endDate);
    }

    @Override
    public UserEntity loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<UserEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserEntity::getUsername, username);
        UserEntity user = userDao.selectOne(queryWrapper);
        //查询不到该用户信息抛异常
        if(user == null){
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        user.setLoginTime(LocalDateTime.now(ZoneOffset.UTC));
        LambdaUpdateWrapper<UserEntity> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(UserEntity::getUserId, user.getUserId())
                .set(UserEntity::getLoginTime, user.getLoginTime());

        update(null, updateWrapper);
        //封装成userEntity返回
        return user;
    }
}