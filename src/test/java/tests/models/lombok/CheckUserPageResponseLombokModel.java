package tests.models.lombok;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CheckUserPageResponseLombokModel {
    @JsonProperty("total_pages")
    private Integer totalPages;
}
