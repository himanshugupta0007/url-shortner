/**
 *
 */
package com.mysocialpal.urlshortner.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * Entity class for URL Shortner Table
 *
 * @author Himanshu.Gupta
 */
@Entity
@Table(name = "URL_SHORTNER_DATA")
@Data
@NoArgsConstructor
public class URLShortner extends URLAuditModel implements Serializable {

    private static final long serialVersionUID = -3922525768106045968L;

    @Id
    @NotNull
    @Column(name = "URL_ID", unique = true)
    @GenericGenerator(name = "url_id_generator", parameters = {
            @Parameter(name = "sequence_name", value = "url_sequence"), @Parameter(name = "initial_value", value = "1"),
            @Parameter(name = "increment_size", value = "2")}, strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator")
    @GeneratedValue(generator = "url_id_generator", strategy = GenerationType.SEQUENCE)
    private Long url_id;
    @Column(name = "ORG_URL")
    private String org_url;
    @Column(name = "URL_HSH")
    private String url_hash;
    @Column(name = "URL_SLT")
    private String url_slt;
    @Column(name = "URL_ALIAS")
    private String url_alias;
    @Column(name = "EXPIRES_ON")
    private LocalDate expires_on;
    @Column(name = "IS_ACTIVE")
    private boolean is_active;
    @Column(name = "IS_DELETED")
    private boolean is_deleted;
}
