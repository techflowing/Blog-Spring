package cn.techflowing.one;

import cn.techflowing.one.backup.MySqlBackUp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 数据备份测试
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2021/10/7 10:27 下午
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DbBackupTest {

    @Autowired
    MySqlBackUp backUp;

    @Test
    public void testDbBackup() {
        backUp.runBackup();
    }
}
