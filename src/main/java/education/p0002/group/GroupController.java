package education.p0002.group;

import education.p0002.common.dao.GroupDao;
import education.p0002.common.dao.GroupUserDao;
import education.p0002.common.dao.UserDao;
import education.p0002.common.dao.ListUpDao;
import education.p0002.common.entity.GroupUser;
import education.p0002.common.entity.Group;
import education.p0002.common.entity.User;
import education.p0002.common.entity.ListUp;
import lombok.Getter;
import lombok.Setter;
//import org.apache.commons.lang3.tuple.Pair;
import org.apache.tomcat.util.digester.ArrayStack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;

@Controller
public class GroupController {
    @Autowired
    GroupDao groupDao;
    @Autowired
    GroupUserDao groupUserDao;
    @Autowired
    UserDao userDao;
    @Autowired
    ListUpDao listUpDao;
    @GetMapping(path = "group")
    String group(Model model) {
        Page page = new Page();

        // Group取得
        List<Group> groupList = groupDao.selectAll();
        // GroupUser取得
        List<GroupUser> groupUserList = groupUserDao.selectAll();
        // User取得
        List<User> userList = userDao.selectAll();
        // ListUp取得
        List<ListUp> listUpList = listUpDao.selectListUp();

        page.tyingMap = new TreeMap<>();
        List<String> workUser = new ArrayList<>();

        String workGroup = "";
        for (ListUp listUp : listUpList) {
            System.out.println("00 : " + listUp.getGroupName() + listUp.getUserName());
            if (workGroup == "") {
                workGroup = listUp.getGroupName();
                workUser.add(listUp.getUserName());
                System.out.println("01 : " + workGroup + listUp.getUserName());
            } else {
                if (workGroup == listUp.getGroupName()) {
                    workUser.add(listUp.getUserName());
                } else {
                    page.tyingMap.put(workGroup, workUser);

                    workUser = new ArrayList<>();
                    workGroup = listUp.getGroupName();
                    workUser.add(listUp.getUserName());
                }
                System.out.println("02 : " + listUp.getGroupName() + listUp.getUserName());
            }
        }
        page.tyingMap.put(workGroup, workUser);

        System.out.println("03 : " + listUpList.get(0));
        System.out.println("04 : " + page.tyingMap);

        model.addAttribute("page", page);
        return "group";
    }

    @Getter
    @Setter
    static public class Page {
        Map<String, List<String>> tyingMap;
    }
}
