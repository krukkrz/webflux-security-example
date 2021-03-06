package krukkrz.com.github.webfluxsecurityexample.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class SearchResult {
    @JsonProperty("Search")
    private List<Poster> search;
}
