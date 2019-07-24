package demo.mybatis.Test;

import demo.mybatis.entity.UserInfo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author yanh
 * @create 2019/7/23 18:15
 * @description
 */
public class TestMybatis {

    public static void main(String[] args) throws IOException {
        //读取配置文件内容
        String resource = "demo/mybatis/resources/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //创建SqlSessionFactory
        SqlSessionFactory sqlSF = new SqlSessionFactoryBuilder().build(inputStream);
        //创建SqlSession
        SqlSession sqlSession = sqlSF.openSession();

        try {
            //根据ID查询
            //查询user_id=2的记录
            List<UserInfo> list = sqlSession.selectList("getUserInfoById", 2);
            for (UserInfo user : list) {
                System.out.println("UserName:" + user.getUser_name() + ",Addr:" + user.getUser_addr());
            }

            //添加数据
            UserInfo addUser = new UserInfo();
            addUser.setUser_name("E");
            addUser.setUser_addr("BJ-DongCheng");

            sqlSession.selectList("addUserInfo",addUser);

            list=sqlSession.selectList("listUserInfo");
            for (UserInfo user :list){
                System.out.println("UserName:"+user.getUser_name()+",Addr:"+user.getUser_addr());
            }

            //删除
            sqlSession.selectList("delUserInfoById",12);

            list=sqlSession.selectList("listUserInfo");
            for (UserInfo user :list){
                System.out.println("UserName:"+user.getUser_name()+",Addr:"+user.getUser_addr());
            }
        } finally {
            sqlSession.close();
        }
    }

}
