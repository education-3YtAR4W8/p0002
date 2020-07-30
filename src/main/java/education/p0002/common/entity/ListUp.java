package education.p0002.common.entity;

import lombok.Data;
import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;
//import org.seasar.doma.Table;

//import java.util.UUID;

@Entity
@Data
public class ListUp {

    @Id
    @Column(name = "id")
    String id;

    @Column(name = "group_id")
    String groupId;

    @Column(name = "group_name")
    String groupName;

    @Column(name = "user_id")
    String userId;

    @Column(name = "user_name")
    String userName;

}
