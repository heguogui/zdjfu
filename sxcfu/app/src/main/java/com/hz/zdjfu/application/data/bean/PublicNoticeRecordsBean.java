package com.hz.zdjfu.application.data.bean;

/**
 * [类功能说明]
 *首页公告基类
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/8/28 0028
 */
public class PublicNoticeRecordsBean {


    /**
     * id : 1
     * title : 1
     * keywords : 11
     * content : 1
     * type : 1
     * create_time : 1509345346000
     * update_time : 1509345350000
     * link : 1
     * is_show : 1
     * order_number : 1
     * web_desc : 1
     * description : 1
     * source : 1
     * image_url : 1
     * view_count : 1
     * web_site : 1
     * view_initial : 0
     */

    private int id;
    private String title;
    private String keywords;
    private String content;
    private int type;
    private long create_time;
    private long update_time;
    private String link;
    private int is_show;
    private int order_number;
    private String web_desc;
    private String description;
    private String source;
    private String image_url;
    private int view_count;
    private int web_site;
    private int view_initial;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(long create_time) {
        this.create_time = create_time;
    }

    public long getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(long update_time) {
        this.update_time = update_time;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getIs_show() {
        return is_show;
    }

    public void setIs_show(int is_show) {
        this.is_show = is_show;
    }

    public int getOrder_number() {
        return order_number;
    }

    public void setOrder_number(int order_number) {
        this.order_number = order_number;
    }

    public String getWeb_desc() {
        return web_desc;
    }

    public void setWeb_desc(String web_desc) {
        this.web_desc = web_desc;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public int getView_count() {
        return view_count;
    }

    public void setView_count(int view_count) {
        this.view_count = view_count;
    }

    public int getWeb_site() {
        return web_site;
    }

    public void setWeb_site(int web_site) {
        this.web_site = web_site;
    }

    public int getView_initial() {
        return view_initial;
    }

    public void setView_initial(int view_initial) {
        this.view_initial = view_initial;
    }

    @Override
    public String toString() {
        return "PublicNoticeRecordsBean{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", keywords='" + keywords + '\'' +
                ", content='" + content + '\'' +
                ", type=" + type +
                ", create_time=" + create_time +
                ", update_time=" + update_time +
                ", link='" + link + '\'' +
                ", is_show=" + is_show +
                ", order_number=" + order_number +
                ", web_desc='" + web_desc + '\'' +
                ", description='" + description + '\'' +
                ", source='" + source + '\'' +
                ", image_url='" + image_url + '\'' +
                ", view_count=" + view_count +
                ", web_site=" + web_site +
                ", view_initial=" + view_initial +
                '}';
    }
}
