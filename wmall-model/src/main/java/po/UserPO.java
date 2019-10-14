package po;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.PrimitiveIterator;

/**
 * @author : ActStrady@tom.com
 * @date : 2019/10/11 20:03
 * @fileName : UserPO.java
 * @gitHub : https://github.com/ActStrady/wmall
 */
@Data
@AllArgsConstructor
public class UserPO {
    private String username;
    private String password;
    private String name;
    private String email;
}
