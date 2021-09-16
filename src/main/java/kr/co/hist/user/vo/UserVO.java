package kr.co.hist.user.vo;

import kr.co.hist.util.UserRole;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="USER")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserVO implements Serializable {
    @Id
    @Setter
    @Column(nullable = false, unique = true, length=80)
    private String id;

    @Setter
    @Column(nullable = false)
    private String passwd;

    @Setter
    @Column(nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Setter
    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT true")
    private boolean isEnable;
}
