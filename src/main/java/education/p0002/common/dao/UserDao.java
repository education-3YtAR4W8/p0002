package education.p0002.common.dao;

import education.p0002.common.entity.User;
import org.seasar.doma.Select;
import org.seasar.doma.Dao;
import org.seasar.doma.boot.ConfigAutowireable;

import java.util.List;

@Dao
@ConfigAutowireable
public interface UserDao {
    @Select
    List<String> selectNameByGroupId(String groupId);
}
