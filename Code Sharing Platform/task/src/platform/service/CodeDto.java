package platform.service;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@RequiredArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@ToString
public class CodeDto {

    @NonNull
    @NotBlank
    @NotEmpty
    private String code;

    private long time;

    private int views;

    public String getCode(){
        return this.code;
    }

    public long getTime(){
        return this.time;
    }

    public int getViews(){
        return this.views;
    }


}
