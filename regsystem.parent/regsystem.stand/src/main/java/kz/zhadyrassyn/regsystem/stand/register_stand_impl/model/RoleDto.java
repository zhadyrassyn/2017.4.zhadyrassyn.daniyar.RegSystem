package kz.zhadyrassyn.regsystem.stand.register_stand_impl.model;

public class RoleDto {
    public Long id;
    public String title;

    public RoleDto(Long id, String title) {
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
