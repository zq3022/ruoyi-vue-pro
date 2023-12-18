package cn.iocoder.yudao;

import cn.iocoder.yudao.server.YudaoServerApplication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


/**
 * 单元测试类
 *
 * @author 芋道源码
 */
@ActiveProfiles("local")
@SpringBootTest(classes = YudaoServerApplication.class)
@Slf4j
public class YudaoServerTest {

}
