package com.atguigu;


import com.atguigu.entity.User;
import com.atguigu.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisPlusAppTest {



    @Resource
    private UserMapper userMapper;

    @Test
    public void contextLoads() {
        List<User> users = userMapper.selectList(null);
        for (User user : users) {
            System.out.println(user);
        }
    }







    @Test
    public void testAutoId(){
//        User user = new User();
//        user.setId(null);
//        user.setAge(12);
//        user.setName("scq");
//        user.setEmail("123456@163.com");
//        int insert = userMapper.insert(user);

        //userMapper.insert(new User(null,"徐海洋",24,"123123@qq.com",null,null,null));
    }




    @Test
    public void testUpdate(){
//        User user = userMapper.selectById(6);
//
//        user.setAge(23);
//        System.out.println(user);
//        int i = userMapper.updateById(user);

        User user = userMapper.selectById(9);
        user.setName("徐海洋1");
        int i = userMapper.updateById(user);
    }



    // 测试乐观锁失败
    public void testOptimisticLockerFail(){
        User user = userMapper.selectById(9);
        // 修改数据
        user.setAge(15);
        user.setVersion(user.getVersion()-1);
        userMapper.updateById(user);
    }




    @Test
    public void TestSelectBymap(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("name","wangxin");
        map.put("age",23);
        List<User> users = userMapper.selectByMap(map);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testSelectPage(){
        Page<User> page = new Page<>(1, 3);
        userMapper.selectPage(page,null);
        List<User> records = page.getRecords();
        for (User record : records) {
            System.out.println(record);
        }
        System.out.println(page.getCurrent());
        System.out.println(page.getPages());
        System.out.println(page.getSize());
        System.out.println(page.getTotal());
        System.out.println(page.hasNext());
        System.out.println(page.hasPrevious());
    }




    @Test
    public void testDeleteById(){
        int i = userMapper.deleteById(1L);
        System.out.println(i);
    }

    // 批量删除
    @Test
    public void deleteBatchIds(){
        int i = userMapper.deleteBatchIds(Arrays.asList(6, 7, 9));
        System.out.println(i);
    }


    @Test
    public void testLogicDelete(){
//        User user = new User(null, "令狐冲", 22, "123123321@qq.com", null, null, null, null);
//        userMapper.insert(user);
        int result = userMapper.deleteById(10L);
        System.out.println(result);
    }




    @Test
    public void testPerformance(){
        User user = new User(null, "我是java8", 27, null, null, null, null, null);
        userMapper.insert(user);
    }

    @Test
    public void testDelete(){
//        User user = new User(null, null, 27, "212121@163.com", null, null, null, null);
//        userMapper.insert(user);

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("name").ge("age",12).isNotNull("email");
        int result = userMapper.delete(queryWrapper);
        System.out.println(result);
    }


    @Test
    public void testSelectOne(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name","我是java8");
        User user = userMapper.selectOne(queryWrapper);
        System.out.println(user);
    }


    @Test
    public void testSelectList(){
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("id",10);
//        map.put("name","令狐冲");
//        map.put("age",22);
//        queryWrapper.allEq(map);
//        List<User> users = userMapper.selectList(queryWrapper);
//        System.out.println(users);


    }


}
