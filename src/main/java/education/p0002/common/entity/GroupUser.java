package education.p0002.common.entity;

import lombok.Data;
import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Table;

import java.util.UUID;

@Entity
@Table(name = "group_user_tbl")
@Data
public class GroupUser {
    @Id
    @Column(name = "group_id")
    String groupId;

    @Column(name = "user_id")
    String userId;
}
