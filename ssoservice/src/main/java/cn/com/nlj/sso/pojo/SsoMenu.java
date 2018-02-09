package cn.com.nlj.sso.pojo;

public class SsoMenu {
    private Integer id;

    private String name;

    private String url;

    private String type;

    private Integer parentId;

    private String buf;

    private Integer soft;

    private String icon;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getBuf() {
        return buf;
    }

    public void setBuf(String buf) {
        this.buf = buf == null ? null : buf.trim();
    }

    public Integer getSoft() {
        return soft;
    }

    public void setSoft(Integer soft) {
        this.soft = soft;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }
}