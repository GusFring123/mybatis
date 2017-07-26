package mapper;

import com.mybatis.pojo.User;
import com.mybatis.pojo.UserQueryVo;

import java.util.List;

public interface UserMapper {
    public User findUserById(int id) throws Exception;
    public void addUser(User user) throws Exception;
    public void deleteUser(String userName) throws Exception;
    public void updateUser(User user) throws Exception;
    //复杂模糊查询
    public List<User> findUserList(UserQueryVo userQueryVo)throws Exception;
}
