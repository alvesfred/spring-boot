package br.alvesfred.spring.boot.ignite.entry;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.apache.ignite.cache.query.annotations.QuerySqlField;

import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Items Config Model
 *
 * @author alvesfred
 *
 */
@EqualsAndHashCode
@Getter
@Setter
@ToString
public class ItemsConfigEntry implements Serializable {

	/**
	 * serial
	 */
	private static final long serialVersionUID = -8214813132842468586L;

	@ApiModelProperty(notes = "service code required by user into Rest API ", required = true)
    @QuerySqlField(index = true)
    @NotNull
    String serviceCode;

    @ApiModelProperty(notes = "error code required by user into REST API ", required = true)
    @QuerySqlField(index = true)
    String errorCode;
}
