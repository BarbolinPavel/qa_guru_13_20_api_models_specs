package tests.models.pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CheckUserPageResponsePojoModel {
    @JsonProperty("total_pages")
    private String totalPages;

    public String getTotalPages() {

        return totalPages;
    }

    public void setTotalPages(String totalPages) {

        this.totalPages = totalPages;
    }
}
