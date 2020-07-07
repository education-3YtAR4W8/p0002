package education.p0002.group;

import education.p0002.common.dao.GroupDao;
import education.p0002.common.dao.GroupUserDao;
import education.p0002.common.dao.UserDao;
import education.p0002.common.entity.Group;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

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

        page.items = getSearchedItems();

        model.addAttribute("page", page);
        return "group";
    }

    List<SearchedItem> getSearchedItems() {
        List<Group> groupList = groupDao.selectAll();
        List<SearchedItem> resultList = new ArrayList<>();

        List<String> userNameList = null;
        for(Group group : groupList){
            userNameList = userDao.selectName(group.getId());


            resultList.add(
                    new SearchedItem(
                            group.getName(),
                            userNameList
                    )
            );

        }

        return resultList;
    }

    @Getter
    @Setter
    static public class Page {
        private List<SearchedItem> items = new ArrayList<>();
    }
    @AllArgsConstructor
    @Getter
    static public class SearchedItem {
        private String groupName;
        private List<String> userNames;
    }
}
