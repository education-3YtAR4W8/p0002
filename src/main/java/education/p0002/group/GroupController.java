package education.p0002.group;

import education.p0002.common.dao.GroupDao;
import education.p0002.common.dao.UserDao;
import education.p0002.common.entity.Group;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Collections;

@Controller
public class GroupController {
    @Autowired
    GroupDao groupDao;
    @Autowired
    UserDao userDao;

    @GetMapping(path = "group")
    String group(Model model) {
        Page page = new Page();

        // グループを全件取得
        List<Group> groupList = groupDao.selectAll();

        // キーで並べ替えたいのでTreeMapで宣言
        Map<String, List<String>> filteredUserMap = new TreeMap<>();
        for (Group group : groupList) {
            // グループIDをもとにユーザ名のリストを取得
            List<String> userNameList = userDao.selectNameByGroupId(group.getId());
            // ユーザ名の昇順にソート
            Collections.sort(userNameList);
            filteredUserMap.put(group.getName(), userNameList);
        }

        page.items = filteredUserMap;
        model.addAttribute("page", page);
        return "group";
    }

    @Getter
    @Setter
    static public class Page {
        private Map<String, List<String>> items;
    }
}
