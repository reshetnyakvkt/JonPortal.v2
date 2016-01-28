package ua.com.jon.auth.domain;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 3/30/13
 */
@XStreamAlias("space")
public class AssemblaSpace {
    private String id;

    private String name;

    private String description;

    @XStreamAlias("wiki-name")
    private String wikiName;

    @XStreamAlias("public-permissions")
    private Integer publicPermissions;


    @XStreamAlias("team-permissions")
    private Integer teamPermissions;

    @XStreamAlias("watcher-permissions")
    private Integer watcherPermissions;

    @XStreamAlias("share-permissions")
    private boolean sharePermissions;

    @XStreamAlias("team-tab-role")
    private Integer teamTabRole;

    @XStreamAlias("created-at")
    private String createdAt;

    @XStreamAlias("updated-at")
    private String updatedAt;

    @XStreamAlias("default-showpage")
    private String defaultShowpage;

    @XStreamAlias("tabs-order")
    private String tabsOrder;

    @XStreamAlias("parent-id")
    private String parentId;

    //    @XStreamAlias("parent-id")
    private boolean restricted;

    @XStreamAlias("restricted-date")
    private String restrictedDate;

    @XStreamAlias("commercial-from")
    private String commercialFrom;

    //    @XStreamAlias("banner")
    private String banner;

    @XStreamAlias("banner-height")
    private String bannerHeight;

    @XStreamAlias("banner-text")
    private String bannerText;

    @XStreamAlias("banner-link")
    private String bannerLink;

    //    @XStreamAlias("style")
    private String style;

    //    @XStreamAlias("status")
    private Integer status;

    //    @XStreamAlias("approved")
    private boolean approved;

    @XStreamAlias("is-manager")
    private boolean isManager;

    @XStreamAlias("is-volunteer")
    private boolean isVolunteer;

    @XStreamAlias("is-commercial")
    private boolean isCommercial;

    @XStreamAlias("can-join")
    private boolean canJoin;

    @XStreamAlias("can-apply")
    private boolean canApply;

    @XStreamAlias("last-payer-changed-at")
    private String lastPayerChangedAt;

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Space{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }}
