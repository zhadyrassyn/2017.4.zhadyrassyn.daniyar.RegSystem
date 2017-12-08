package kz.zhadyrassyn.regsystem.stand.register_stand_impl.model;

import kz.zhadyrassyn.regsystem.stand.register_stand_impl.model.enums.RoleTitleEnum;

public class RoleDto {
    public Long id;
    public RoleTitleEnum title;

    public RoleDto(Long id, RoleTitleEnum title) {
        this.id = id;
        this.title = title;
    }

    @Override
    public String toString() {
        return "RoleDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
