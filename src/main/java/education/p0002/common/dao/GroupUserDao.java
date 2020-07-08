package education.p0002.common.dao;

import org.seasar.doma.Dao;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.Select;
import education.p0002.common.entity.GroupUser;

import java.util.List;


@Dao
@ConfigAutowireable
public interface GroupUserDao {
    @Select
    List<GroupUser> selectGroupUser();
}
