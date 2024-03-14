package gm.utility.ssh;

import gm.utility.ssh.domain.SSHModel;
import gm.utility.ssh.service.SSHUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SSHUtil.class})
class SSHUtilTest {
    @Autowired
    SSHUtil sshUtil;

    @Test
    @DisplayName("연결요청후_데이터받기")
    void connect_test() {
        SSHModel model = new SSHModel("test1", "127.0.0.1", 16677, "akaj124!");

        String result = sshUtil.getSSHResponse(model, "top;netstat -tnlp", SSHType.EXEC);
        System.out.println(result);
        assertThat(result).isNotEmpty();
    }

    @Test
    @DisplayName("여러 요청 한번에 하기")
    void thread_test() {
        Thread th1 = new Thread(() -> {
            SSHModel model = new SSHModel("test1", "127.0.0.1", 16677, "123123!");
            final String ret1 = sshUtil.getSSHResponse(model, "ls -al;sleep 0.3;ls -al;", SSHType.EXEC);
            assertThat(ret1).isNotEmpty();
        });

        Thread th2 = new Thread(() -> {
            SSHModel model = new SSHModel("test2", "127.0.0.1", 16677, "123123@");
            final String ret2 = sshUtil.getSSHResponse(model, "top;sleep 2;cat .bashrc;", SSHType.EXEC);
            assertThat(ret2).isNotEmpty();
        });
        th1.start();
        th2.start();

        try {
            th1.join();
            th2.join();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}