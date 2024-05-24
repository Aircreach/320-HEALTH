package com.air.health.admin.controller;

import com.air.health.admin.entity.AdminEntity;
import com.air.health.admin.feign.InsFeign;
import com.air.health.admin.feign.UserFeign;
import com.air.health.admin.service.AdminService;
import com.air.health.common.encoder.AirPasswordEncoder;
import com.air.health.common.model.*;
import com.air.health.common.util.Constants;
import com.air.health.common.util.TokenProvider;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;



/**
 * 
 *
 * @author Aircreach
 * @email 3064568039@qq.com
 * @date 2024-04-23 11:01:02
 */
@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    InsFeign insFeign;

    @Autowired
    UserFeign userFeign;

    @PostMapping("/login")
    public Result login(@RequestBody JSONObject param) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(param.get("username"),
                        param.get("password")
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        AdminEntity user = (AdminEntity) authentication.getPrincipal();
        // 生成JWT令牌
        String token = tokenProvider.generate(Constants.TOKEN_ADMIN, user.getUsername());
        redisTemplate.opsForValue().set(
                String.format(Constants.REDIS_KEY_PREFIX_TOKEN_ADMIN, user.getUsername()),
                JSON.toJSONString(user),
                tokenProvider.getDefaultExpirationInMs(),
                TimeUnit.MILLISECONDS
        );
        return Result.success().put("token", new TokenModel(token)).put("admin", user);
    }

    /**
     * 列表
     */
    @PostMapping("/list")
//    @RequiresPermissions("member:member:list")
    public Result list(@RequestBody Map<String, Object> params){
        PageModel page = adminService.queryPage(params);
        return Result.success().put("page", page);
    }

    @PostMapping("/provinceCount")
    public Result provinceCount(){
        List<Map> data_user = (List<Map>) userFeign.provinceCount().get("data");
        List<Map> data_ins = (List<Map>) insFeign.provinceCount().get("data");
        Map<String, int[]> resultMap = new HashMap<>();

        // 将用户数据添加到结果映射中
        for (Map userCount : data_user) {
            String province = (String) userCount.get("name");
            if (province == null) {
                province = "未知属地";
            }

            resultMap.putIfAbsent(province, new int[2]);
            int[] values = resultMap.get(province);
            values[0] = (int) userCount.get("num");
        }

        // 将机构数据添加到结果映射中
        for (Map insCount : data_ins) {
            String province = (String) insCount.get("name");
            if (province == null) {
                province = "未知属地";
            }
            resultMap.putIfAbsent(province, new int[2]);
            int[] values = resultMap.get(province);
            values[1] = (int) insCount.get("num");
        }

        List<Map> data = new ArrayList<>();
        for (Map.Entry<String, int[]> entry : resultMap.entrySet()) {
            Map<String, Object> temp = new HashMap<>();
            temp.put("name", entry.getKey());
            temp.put("value", entry.getValue());
            data.add(temp);
        }

        return Result.success().put("data", data);
    }

    @PostMapping("/dateCount")
    public Result dateCount(@RequestBody LocalDateTime date){
        Map<String, List<Integer>> resultMap = new HashMap<>();

        LocalDateTime startDate = date.minusDays(30); // 前30天为开始日期
        // 分组每五天
        List<Map<String, LocalDateTime>> dateGroups = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            LocalDateTime groupStart = startDate.plusDays(i * 5);
            LocalDateTime groupEnd = startDate.plusDays((i + 1) * 5 - 1);

            // 将起始日期和结束日期存储在 Map 中
            Map<String, LocalDateTime> groupMap = new HashMap<>();
            groupMap.put("startDate", groupStart);
            groupMap.put("endDate", groupEnd);
            dateGroups.add(groupMap);
        }
        List<Integer> users = new ArrayList<>();
        List<Integer> inss = new ArrayList<>();
        for (Map<String, LocalDateTime> dateGroup : dateGroups) {
            Integer data_user = (Integer) userFeign.dateCount(dateGroup).get("data");
            users.add(data_user);
            Integer data_ins = (Integer) insFeign.dateCount(dateGroup).get("data");
            inss.add(data_ins);
        }
        resultMap.put("user", users);
        resultMap.put("ins", inss);
        return Result.success().put("data", resultMap);
    }

    @PostMapping("/listIns")
    public Result listIns(@RequestBody Map<String, Object> params){
        return insFeign.listIns(params);
    }

    @GetMapping("/infoIns/{instructionId}")
    public Result infoIns(@PathVariable("instructionId") String insId){
        return insFeign.infoIns(insId);
    }

    @RequestMapping("/updateIns")
//    @RequiresPermissions("member:member:update")
    public Result update(@RequestBody Object ins){
        insFeign.updateIns(ins);

        return Result.success();
    }

    @RequestMapping("/listUser")
    public Result listUser(@RequestBody Map<String, Object> map) {
        return userFeign.listUser(map);
    }

    @RequestMapping("/infoUser/{userId}")
    public Result infoUser(@PathVariable("userId") String userId) {
        return userFeign.infoUser(userId);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{adminId}")
//    @RequiresPermissions("member:member:info")
    public Result info(@PathVariable("adminId") Long memberId){
		AdminEntity admin = adminService.getById(memberId);
        return Result.success().put("member", admin);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
//    @RequiresPermissions("member:member:save")
    public Result save(@RequestBody AdminEntity admin){
		adminService.save(admin);

        return Result.success();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
//    @RequiresPermissions("member:member:update")
    public Result update(@RequestBody AdminEntity admin){
        admin.setPassword(null);
		adminService.updateById(admin);

        return Result.success();
    }

    /**
     * 修改密码
     */
    @RequestMapping("/changePwd")
//    @RequiresPermissions("generator:user:update")
    public Result changePwd(@RequestBody Map<String, Object> params){
        String currentPassword = (String) params.get("currentPassword");
        AdminEntity admin = adminService.getById((Serializable) params.get("id"));
        AirPasswordEncoder encoder = new AirPasswordEncoder();
        if (encoder.matches(currentPassword, admin.getPassword())) {
            LambdaUpdateWrapper<AdminEntity> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.eq(AdminEntity::getAdminId, admin.getAdminId())
                    .set(AdminEntity::getPassword, params.get("newPassword"));
            adminService.update(null, updateWrapper);
            logout(String.valueOf(admin.getAdminId()));
            return Result.success();
        } else {
            throw new AirException("密码错误");
        }
    }

    /**
     * 登出
     */
    @GetMapping("/logout/{id}")
    public Result logout(@PathVariable("id") String id){
        redisTemplate.delete("id");
        SecurityContextHolder.clearContext();
        return Result.success();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
//    @RequiresPermissions("member:member:delete")
    public Result delete(@RequestBody Long[] adminIds){
		adminService.removeByIds(Arrays.asList(adminIds));

        return Result.success();
    }

}
