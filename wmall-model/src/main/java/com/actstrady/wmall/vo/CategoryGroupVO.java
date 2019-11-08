package com.actstrady.wmall.vo;

import lombok.Data;

import java.util.List;

@Data
public class CategoryGroupVO {
    private String groupName;
    private List<ChildCategoryVO> categories;

    // /**
    //  * 重写equals函数
    //  *
    //  * @param other 另一个
    //  * @return 真假
    //  */
    // @Override
    // public boolean equals(Object other) {
    //     if (this == other) {
    //         return true;
    //     }
    //     if (other == null) {
    //         return false;
    //     }
    //     if (getClass() != other.getClass()) {
    //         return false;
    //     }
    //     CategoryGroupVO otherGroup = (CategoryGroupVO) other;
    //     return otherGroup.getGroupName().equals(this.getGroupName());
    // }
    //
    // @Override
    // public int hashCode() {
    //     int result = 17;
    //     result += 31 * groupName.hashCode();
    //     result += 31 * categories.hashCode();
    //     return result;
    // }
}
