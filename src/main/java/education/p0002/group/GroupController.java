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

import java.util.*;
import java.util.Map;
import java.util.Map.Entry;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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

        Map<String, Group> groupMap = new HashMap<>();
        for (Group group : groupDao.selectAll()) {
            groupMap.put(group.getId(), group);
        }

        Map<String, User> userMap = new TreeMap<>();
        for (User user : userDao.selectAll()) {
            userMap.put(user.getId(), user);
        }

        Map<String, List<GroupUser>> mappedGroupUserListByItemId = new HashMap<>();
        for (GroupUser groupUser : groupUserDao.selectAll()) {
           if(!mappedGroupUserListByItemId.containsKey(groupUser.getGroupId())) {
               mappedGroupUserListByItemId.put(groupUser.getGroupId(), new ArrayList<>());
           }
            mappedGroupUserListByItemId.get(groupUser.getGroupId()).add(groupUser);
        }

        //ソート処理
        List<Entry<String, User>> usermapEntryList = new ArrayList<Entry<String,User>>(userMap.entrySet());
        Collections.sort(usermapEntryList, new Comparator<Entry<String, User>>() {
            @Override

            public int compare(Entry<String, User> o1, Entry<String, User> o2) {
                User o1List = o1.getValue();
                User o2List = o2.getValue();

                Comparable o1ListFirsr = (Comparable)o1List.getName();
                Comparable o2ListFirsr = (Comparable)o2List.getName();

                return o1ListFirsr.compareTo(o2ListFirsr);
            }
        });
        Map<String, User> sortUser = new LinkedHashMap<>();
        for (Entry<String, User> entry : usermapEntryList) {
            sortUser.put(entry.getKey(), entry.getValue());
        }

        List<AscendedItem> AscendedItemList = new ArrayList<>();
        String groupName = "";
        String userName = "";
        for (GroupUser groupUser : groupUserDao.selectAll()) {
            if (groupMap.containsKey(groupUser.getGroupId())){
                groupName = groupMap.get(groupUser.getGroupId()).getName();
            }
            if (sortUser.containsKey(groupUser.getUserId())) {

                userName = sortUser.get(groupUser.getUserId()).getName();

            }
            AscendedItemList.add(
                    new AscendedItem(
                            groupName,
                            userName
                )
             );
        }
        page.items = AscendedItemList;

        model.addAttribute("page", page);
        return "group";
    }

    @Getter
    @Setter
    static public class Page {
       private List<AscendedItem> items = new ArrayList<>();
    }
    @AllArgsConstructor
    @Getter
    static public class AscendedItem {
        private String groupName;
        private String userName;

    }
}
