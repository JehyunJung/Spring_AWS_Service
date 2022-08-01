package web_service.springawsserver.web.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HelloResponseDtoTest {
    @Test
    @DisplayName("롬복 기능 테스트")
    public void lombok_test(){
        //given
        String name = "test";
        int amount = 1000;

        //when
        HelloResponseDto response = new HelloResponseDto(name, amount);

        //then
        assertThat(response.getName()).isEqualTo(name);
        assertThat(response.getAmount()).isEqualTo(amount);
    }

}