import com.alibaba.fastjson.JSON;
import com.beta.web.BetaWebApplication;
import com.mapper.PermissionMapper;
import com.mapper.RoleMapper;
import com.mapper.UserMapper;
import com.stock.models.Permission;
import com.stock.models.Role;
import com.stock.models.User;
import com.stock.service.PermissionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@SpringBootTest(classes = BetaWebApplication.class)
@RunWith(SpringRunner.class)
public class test1 {

    @Autowired
    PermissionMapper permissionMapper;

    @Autowired
    RoleMapper roleMapper;
    @Autowired
    UserMapper userMapper;
    @Test
    public void test2(){
        ArrayList<Integer> lists = new ArrayList<Integer>();
        ArrayList<Map<String ,Object>> permissions = permissionMapper.findIds(13);
        findChild(permissions, lists);
        System.out.println(lists);
    }
//[{children=[{children=[{children=[{children=[], id=31}], id=19}], id=18}, {children=[], id=30}], id=13}]
    public void findChild(ArrayList<Map<String ,Object>> permissions, ArrayList<Integer> lists){
        for(Map<String,Object> map : permissions){
                 lists.add((Integer) map.get("id"));
            ArrayList<Map<String ,Object>> pms = (ArrayList<Map<String ,Object>>) map.get("children");
            findChild(pms,lists);
        }
    }

    @Test
    public void test3()
    {
        HashMap<String, Object> map = new HashMap<>();
        map.put("role_name","大飒飒的");
        ArrayList<Role> list = roleMapper.findRole(map);
        System.out.println(JSON.toJSONString(list));;
    }
    @Test
    public void test4()
    {
        User user = userMapper.findUserInfo(60);
        System.out.println(JSON.toJSONString(user));;
    }
}
