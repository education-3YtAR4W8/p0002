package education.p0002.common.entity;

import lombok.Data;
import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Table;

@Entity
@Table(name = "group_tbl")
@Data
public class Group {
    @Id
    @Column(name = "id")
    String id;

    @Column(name = "name")
    String name;
}
