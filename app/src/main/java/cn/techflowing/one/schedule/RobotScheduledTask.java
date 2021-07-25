package cn.techflowing.one.schedule;

import cn.techflowing.one.backup.MySqlBackUp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RobotScheduledTask {

    @Autowired
    MySqlBackUp dbBackup;

    /**
     * 每天凌晨3点备份数据库
     */
    @Scheduled(cron = "0 0 3 * * ?")
    public void dbBackup() {
        dbBackup.runBackup();
    }
}
