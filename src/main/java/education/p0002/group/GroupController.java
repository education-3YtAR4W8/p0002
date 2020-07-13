package education.p0002.group;

import education.p0002.common.dao.GroupDao;
import education.p0002.common.dao.GroupUserDao;
import education.p0002.common.dao.UserDao;
import education.p0002.common.entity.Group;
import education.p0002.common.entity.GroupUser;
import education.p0002.common.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.tuple.Pair;
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

        Map<String, Group> groupMap = groupDao.selectAll().stream().collect(Collectors.toMap(it -> it.getId(), it -> it));
        Map<String, User> userMap = userDao.selectAll().stream().collect(Collectors.toMap(it -> it.getId(), it -> it));

        page.groupAndUsersPairs = groupUserDao.selectAll()
                .stream()
                .collect(Collectors.groupingBy(GroupUser::getGroupId))
                .entrySet()
                .stream()
                .map(groupIdAndUserIdsPair -> Pair.of(
                        Optional.ofNullable(groupMap.get(groupIdAndUserIdsPair.getKey())).orElseThrow(() -> {
                            throw new RuntimeException(String.format("データ不整合です。group_user_tblに存在するgroup_id:%sに紐づくgroupがgroup_tblに存在しません。", groupIdAndUserIdsPair.getKey()));
                        }),
                        groupIdAndUserIdsPair.getValue()
                                .stream()
                                .map(groupUser -> Optional.ofNullable(userMap.get(groupUser.getUserId())).orElseThrow(() -> {
                                    throw new RuntimeException(String.format("データ不整合です。group_user_tblに存在するuser_id:%sに紐づくuserがuser_tblに存在しません。", groupUser.getUserId()));
                                }))
                                .sorted(Comparator.comparing(User::getName))
                                .collect(Collectors.toList())
                ))
                .sorted(Comparator.comparing(groupAndUsersPair -> groupAndUsersPair.getKey().getName()))
                .collect(Collectors.toList());

        model.addAttribute("page", page);
        return "group";
    }

    @Getter
    @Setter
    static public class Page {
        List<Pair<Group, List<User>>> groupAndUsersPairs;
    }
}
