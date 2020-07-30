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

//        String workGroup = "";
//        List<String> workUserList = new ArrayList<>();
//        for(ListUp listUp : listUpList) {
//            if (workGroup != "" && workGroup != listUp.groupName) {
//                workGroup = listUp.groupName;
//                page.tyingList = new tyingList(workGroup, workUserList);
//            } else {
//                workUserList.add = listUp.getUserName();
//            }
//            page.tyingList = listUp;
//        }

        Map<String, List<String>> tyingMap = new HashMap<>();
        List<String> workUser = new ArrayList<>();

        String workGroup = "";
        for (ListUp listUp : page.tyingList = listUpList) {
            System.out.println(listUp.getGroupName() + listUp.getUserName());
            if (workGroup == "") {
                workGroup = listUp.getGroupName();
                workUser.add(listUp.getUserName());
                System.out.println(workGroup + listUp.getUserName());
            } else {
                if (workGroup == listUp.getGroupName()) {
                    workUser.add(listUp.getUserName());
                } else {
                    tyingMap.put(workGroup, workUser);

                    workUser.clear();
                    workGroup = listUp.getGroupName();
                }
                System.out.println(listUp.getGroupName() + listUp.getUserName());
            }

        }

        System.out.println("listUpList.get(0) = " + listUpList.get(0));
        System.out.println(page.tyingList);


        model.addAttribute("page", page);
        return "group";
    }

    @Getter
    @Setter
    static public class Page {
        List<ListUp> tyingList;
    }
}
