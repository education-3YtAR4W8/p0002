package education.p0002.common.entity;

import lombok.Data;
import org.seasar.doma.*;

import java.util.UUID;

@Entity
@Table(name = "user_tbl")
@Data
public class User {
    @Id
    @Column(name = "id")
    String id;

    @Column(name = "name")
    String name;
}
