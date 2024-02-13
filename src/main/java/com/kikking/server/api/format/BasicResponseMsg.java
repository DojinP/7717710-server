package com.kikking.server.api.format;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BasicResponseMsg {

    private Integer code;
    private HttpStatus httpStatus;
    private String message;
    private Integer count;  // data's count to be returned
    private List<Object> result;

}
