package com.blog.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.blog.pojo.JwtResponse;
import com.blog.pojo.JwtUser;
import com.blog.pojo.User;
import com.blog.service.LoginServcie;
import com.blog.service.UserService;
import com.blog.utils.JwtTokenUtil;
import com.blog.utils.R;
import com.blog.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;

/**
 * @author a1002
 */
@Service
public class LoginServiceImpl implements LoginServcie {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtUserDetailsService userDetailsService;
    @Value("${jwt.header}")
    private String tokenHeader;

    Random random = new Random();


    @Override
    public JwtUser isLogin(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader).substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        //        JwtUser jwtUser = (JwtUser) userDetailsService.loadUserByUsername(username);
        DecodedJWT decode = JWT.decode(token);
        Claim id = decode.getClaim("id");
        Claim account = decode.getClaim("username");
        System.out.println(account);
        Claim password = decode.getClaim("password");
        Claim auth = decode.getClaim("authorities");
        return new JwtUser(id.asInt(), account.asString(), password.asString(), auth.asMap().get("authority").toString(), true);
    }

    @Override
    public R login(String admin, String password) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, admin);
        queryWrapper.eq(User::getPassword, password);
        User user = userService.getOne(queryWrapper);
        if (user == null) {
            return R.error("密码错误");
        }
        String key = "userLimit";
        String userKey = "user@" + user.getId();
        if (redisUtil.hasKey(key) && redisUtil.sHasKey(key, "\"" + userKey + "\"")) {
            return R.error("该用户已经被封禁");
        }
        try {
            authenticate(user.getUsername(), user.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(user.getUsername());
        System.out.println(userDetails);
        final String token = jwtTokenUtil.generateToken(userDetails);
        return R.success(ResponseEntity.ok(new JwtResponse(token)));
    }

    void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    @Override
    @Transactional
    public R register(String admin, String password) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, admin);
        if (userService.getOne(queryWrapper) != null) {
            return R.error("该账号已经存在");
        }
        User user = new User(admin, password);
        user.setStatus(1);
        userService.save(user);
        return R.success("账号添加成功");
    }

    @Override
    public void quit(HttpServletRequest request) {
        String token = request.getHeader("token");
        String userLimitKey = "user@Limit:Token";
        String tokenKey = "token@Limit" + token;
        redisUtil.sSetAndTime(userLimitKey, 5 * 60 * 60, tokenKey);
    }
}
