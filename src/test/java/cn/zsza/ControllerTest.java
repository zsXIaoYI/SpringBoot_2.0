package cn.zsza;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
/**
 * @Author: ZhangSong
 * @Date: Created In 10:49 2018/4/28
 * @Company: Bank Of HengShui
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {

    @Autowired
    private MockMvc mvc;
    @Test
    public void getHello() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/user/get").accept(MediaType.APPLICATION_JSON)
                .param("name","小A ").param("age","18"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("hello")));
    }
}
