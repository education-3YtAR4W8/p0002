package education.p0002.group;

import education.p0002.common.dao.GroupDao;
import education.p0002.common.dao.GroupUserDao;
import education.p0002.common.dao.UserDao;
import education.p0002.common.entity.Group;
import education.p0002.common.entity.GroupUser;
import education.p0002.common.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class GroupController {
    @Autowired
    GroupDao groupDao;
    @Autowired
    GroupUserDao groupUserDao;
    @Autowired
    UserDao userDao;


    @GetMapping(path = "group")
    String group(Model model) {
        Page page = new Page();
        page.groupuserName = getGroupItems();
        return "group";
    }
    List<GroupItem> getGroupItems(){
        List<GroupItem> groupItems = new ArrayList();
        Map<String,String> groupMap =new HashMap<>();
        Map<String,List<String>> groupUserMap =new HashMap<>();
        Map<String,String> userMap =new HashMap<>();

        for (Group group : groupDao.selectGroup()) {
            groupMap.put(group.getId(), group.getName());
        }
        for (GroupUser groupuser : groupUserDao.selectGroupUser()) {
            if(!groupUserMap.containsKey(groupuser.getGroupId())){
                groupUserMap.put(groupuser.getGroupId(),new ArrayList<>());
            }
            groupUserMap.get(groupuser.getGroupId()).add(groupuser.getUserId());
        }
        for (User user : userDao.selectUser()) {
            userMap.put(user.getId(), user.getName());
        }
        Map<String,String> group = new HashMap<>();
        for (String g : groupMap.keySet()){
            String groupName = groupMap.get(g);
            List<String> userIdList=groupUserMap.get(g);
            List<String> userNameList=new ArrayList<>();
            for (String userId : userIdList){
                userNameList.add(userMap.get(userId));
            }
            groupItems.add(new GroupItem(groupName,userNameList));

        }
        return groupItems;
    }

    @Getter
    @Setter
    static public class Page {
        private String groupName;
        private List<GroupItem> groupuserName = new ArrayList<>();
    }

    @AllArgsConstructor
    @Getter
    static public class GroupItem{
        private String groupName;
        private List<String> name;
    }
}


