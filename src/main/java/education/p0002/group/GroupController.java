package education.p0002.group;

import education.p0002.common.dao.GroupDao;
import education.p0002.common.dao.GroupUserDao;
import education.p0002.common.dao.UserDao;
import education.p0002.common.entity.Group;
import education.p0002.common.entity.GroupUser;
import education.p0002.common.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;
import java.util.stream.Collectors;

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
        Map<String, String> groupMap = groupDao.selectAll().stream().collect(Collectors.toMap(Group::getId, Group::getName));
        Map<String, String> userMap = userDao.selectAll().stream().collect(Collectors.toMap(User::getId,User::getName));
        List<GroupUser> groupUserList = groupUserDao.selectAll();

        for(GroupUser groupUser : groupUserList) {
            if (groupMap.containsKey(groupUser.getGroupId())) {
                String groupName = groupMap.get(groupUser.getGroupId());
                List<String> displayUserList;
                if (page.groups.containsKey(groupName)) {
                    displayUserList = page.groups.get(groupName);
                } else {
                    displayUserList = new ArrayList<>();
                }
                if (userMap.containsKey(groupUser.getUserId())) {
                    displayUserList.add(userMap.get(groupUser.getUserId()));
                    Collections.sort(displayUserList);
                    page.groups.put(groupName,displayUserList);
                }
            }
        }
        model.addAttribute("page", page);
        return "group";
    }

    @Getter
    @Setter
    static public class Page {
        private Map<String, List<String>> groups = new TreeMap<>();
    }
}
