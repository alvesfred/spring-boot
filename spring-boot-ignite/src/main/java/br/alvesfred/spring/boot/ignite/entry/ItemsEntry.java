package br.alvesfred.spring.boot.ignite.entry;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.apache.ignite.cache.query.annotations.QuerySqlField;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Map;

/**
 * Entry Model for Items
 *
 * @author alvesfred
 *
 */
@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ItemsEntry implements Serializable {

	/**
	 * serial
	 */
	private static final long serialVersionUID = -5594749048159014937L;

	private String itemId;

	@ApiModelProperty(notes = "the key value item content")
    private Map<String,String> itemContent;

    @ApiModelProperty(notes = "alert error code ", required = false)
    @QuerySqlField(index = false)
    private String errorCode;

    @ApiModelProperty(notes = "item service code requested for Rest API", required = true)
    @QuerySqlField(index = true)
    @NotNull
    private String serviceId;

    @QuerySqlField(index = true)
    private Long timestamp;
}
